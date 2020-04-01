package org.oagi.srt.gateway.http.api.bie_management.service.generate_expression;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.ImmutableMap;
import org.oagi.srt.data.*;
import org.oagi.srt.gateway.http.api.bie_management.data.expression.GenerateExpressionOption;
import org.oagi.srt.gateway.http.api.bie_management.data.expression.OpenAPIExpressionFormat;
import org.oagi.srt.gateway.http.helper.SrtGuid;
import org.oagi.srt.repository.TopLevelAbieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.oagi.srt.gateway.http.api.bie_management.data.expression.OpenAPIExpressionFormat.YAML;
import static org.oagi.srt.gateway.http.api.bie_management.service.generate_expression.Helper.*;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class BieOpenAPIGenerateExpression implements BieGenerateExpression, InitializingBean {

    private static final String OPEN_API_VERSION = "3.0.2";

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ObjectMapper mapper;
    private ObjectMapper expressionMapper;
    private Map<String, Object> root;
    private GenerateExpressionOption option;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TopLevelAbieRepository topLevelAbieRepository;

    private GenerationContext generationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void generate(TopLevelAbie topLevelAbie, GenerateExpressionOption option) {
        this.option = option;
        this.generationContext = applicationContext.getBean(GenerationContext.class, topLevelAbie);

        OpenAPIExpressionFormat openAPIExpressionFormat;
        if (StringUtils.isEmpty(this.option.getOpenAPIExpressionFormat())) {
            openAPIExpressionFormat = YAML;
        } else {
            openAPIExpressionFormat = OpenAPIExpressionFormat.valueOf(this.option.getOpenAPIExpressionFormat());
        }

        switch (openAPIExpressionFormat) {
            case YAML:
                expressionMapper = new ObjectMapper(new YAMLFactory());
                break;
            case JSON:
                expressionMapper = new ObjectMapper();
                break;
        }
        expressionMapper.enable(SerializationFeature.INDENT_OUTPUT);

        generateTopLevelAbie(topLevelAbie);
    }

    private void generateTopLevelAbie(TopLevelAbie topLevelAbie) {
        ABIE abie = generationContext.findAbie(topLevelAbie.getAbieId());

        ASBIEP asbiep = generationContext.receiveASBIEP(abie);
        ABIE typeAbie = generationContext.queryTargetABIE(asbiep);

        Map<String, Object> paths;
        Map<String, Object> schemas = new LinkedHashMap();
        if (root == null) {
            root = new LinkedHashMap();
            root.put("openapi", OPEN_API_VERSION);
            root.put("info", ImmutableMap.<String, Object>builder()
                    .put("title", "")
                    .put("description", "")
                    .put("contact", ImmutableMap.<String, Object>builder()
                            .put("name", "")
                            .put("url", "")
                            .put("email", "example@example.org")
                            .build())
                    .put("version", "")
                    .build());

            paths = new LinkedHashMap();
            root.put("paths", paths);
            root.put("components", ImmutableMap.<String, Object>builder()
                    .put("schemas", schemas)
                    .build()
            );
        } else {
            paths = (Map<String, Object>) root.get("paths");
            schemas = (Map<String, Object>) ((Map<String, Object>) root.get("components")).get("schemas");
        }

        Map<String, Object> path = new LinkedHashMap();
        ASCCP basedAsccp = generationContext.findASCCP(asbiep.getBasedAsccpId());
        String bieName = camelCase(basedAsccp.getPropertyTerm());
        String pathName = "/" + bieName;
        paths.put(pathName, path);

        path.put("summary", "");
        path.put("description", "");

        if (option.isOpenAPI30GetTemplate()) {
            String schemaName = "get-" + bieName + "-" + abie.getGuid();
            path.put("get", ImmutableMap.<String, Object>builder()
                    .put("summary", "")
                    .put("description", "")
                    .put("parameters", Arrays.asList(
                            ImmutableMap.<String, Object>builder()
                                    .put("name", "id")
                                    .put("in", "query")
                                    .put("description", "")
                                    .put("required", false)
                                    .put("schema", ImmutableMap.<String, Object>builder()
                                            .put("$ref", "#/components/schemas/integer")
                                            .build())
                                    .build()
                    ))
                    .put("responses", ImmutableMap.<String, Object>builder()
                            .put("200", ImmutableMap.<String, Object>builder()
                                    .put("description", "")
                                    .put("content", ImmutableMap.<String, Object>builder()
                                            .put("application/json", ImmutableMap.<String, Object>builder()
                                                    .put("schema", ImmutableMap.<String, Object>builder()
                                                            .put("$ref", "#/components/schemas/" + schemaName)
                                                            .build())
                                                    .build())
                                            .build())
                                    .build())
                            .build())
                    .build());

            if (!schemas.containsKey("integer")) {
                schemas.put("integer", ImmutableMap.<String, Object>builder()
                        .put("type", "integer")
                        .build());
            }

            Map<String, Object> properties = new LinkedHashMap();
            properties.put("required", new ArrayList());
            properties.put("additionalProperties", false);

            fillPropertiesForGetTemplate(properties, schemas, asbiep, typeAbie, generationContext);
            schemas.put(schemaName, properties);
        }

        if (option.isOpenAPI30PostTemplate()) {
            String schemaName = "post-" + bieName + "-" + abie.getGuid();
            path.put("post", ImmutableMap.<String, Object>builder()
                    .put("summary", "")
                    .put("description", "")
                    .put("requestBody", ImmutableMap.<String, Object>builder()
                            .put("description", "")
                            .put("content", ImmutableMap.<String, Object>builder()
                                    .put("application/json", ImmutableMap.<String, Object>builder()
                                            .put("schema", ImmutableMap.<String, Object>builder()
                                                    .put("$ref", "#/components/schemas/" + schemaName)
                                                    .build())
                                            .build())
                                    .build())
                            .build())
                    .put("responses", ImmutableMap.<String, Object>builder()
                            .put("200", ImmutableMap.<String, Object>builder()
                                    .put("description", "")
                                    .put("content", ImmutableMap.<String, Object>builder()
                                            .put("application/json", ImmutableMap.<String, Object>builder()
                                                    .build())
                                            .build())
                                    .build())
                            .build())
                    .build());

            Map<String, Object> properties = new LinkedHashMap();
            properties.put("required", new ArrayList());
            properties.put("additionalProperties", false);

            fillPropertiesForPostTemplate(properties, schemas, asbiep, typeAbie, generationContext);
            schemas.put(schemaName, properties);
        }
    }

    private void fillPropertiesForGetTemplate(Map<String, Object> parent,
                                              Map<String, Object> schemas,
                                              ASBIEP asbiep, ABIE abie,
                                              GenerationContext generationContext) {
        /*
         * Issue #587
         */
        if (option.isIncludeMetaHeaderForJsonForOpenAPI30GetTemplate()) {
            TopLevelAbie metaHeaderTopLevelAbie =
                    topLevelAbieRepository.findById(option.getMetaHeaderTopLevelAbieIdForOpenAPI30GetTemplate());
            GenerationContext metaHeaderGenerationContext =
                    applicationContext.getBean(GenerationContext.class, metaHeaderTopLevelAbie);
            fillProperties(parent, schemas, metaHeaderGenerationContext);
        }
        if (option.isIncludePaginationResponseForJsonForOpenAPI30GetTemplate()) {
            TopLevelAbie paginationResponseTopLevelAbie =
                    topLevelAbieRepository.findById(option.getPaginationResponseTopLevelAbieIdForOpenAPI30GetTemplate());
            GenerationContext paginationResponseGenerationContext =
                    applicationContext.getBean(GenerationContext.class, paginationResponseTopLevelAbie);
            fillProperties(parent, schemas, paginationResponseGenerationContext);
        }

        fillProperties(parent, schemas, asbiep, abie,
                option.isArrayForJsonExpressionForOpenAPI30GetTemplate(), generationContext);
    }

    private void fillPropertiesForPostTemplate(Map<String, Object> parent,
                                               Map<String, Object> schemas,
                                               ASBIEP asbiep, ABIE abie,
                                               GenerationContext generationContext) {
        /*
         * Issue #587
         */
        if (option.isIncludeMetaHeaderForJsonForOpenAPI30PostTemplate()) {
            TopLevelAbie metaHeaderTopLevelAbie =
                    topLevelAbieRepository.findById(option.getMetaHeaderTopLevelAbieIdForOpenAPI30PostTemplate());
            GenerationContext metaHeaderGenerationContext =
                    applicationContext.getBean(GenerationContext.class, metaHeaderTopLevelAbie);
            fillProperties(parent, schemas, metaHeaderGenerationContext);
        }

        fillProperties(parent, schemas, asbiep, abie,
                option.isArrayForJsonExpressionForOpenAPI30PostTemplate(), generationContext);
    }

    private String _camelCase(String term) {
        return Arrays.stream(term.split(" ")).filter(e -> !StringUtils.isEmpty(e))
                .map(e -> {
                    if (e.length() > 1) {
                        return Character.toUpperCase(e.charAt(0)) + e.substring(1).toLowerCase();
                    } else {
                        return e.toUpperCase();
                    }
                }).collect(Collectors.joining());
    }

    private String camelCase(String... terms) {
        String term = Arrays.stream(terms).collect(Collectors.joining());
        if (terms.length == 1) {
            term = _camelCase(terms[0]);
        } else if (term.contains(" ")) {
            term = Arrays.stream(terms).map(e -> _camelCase(e)).collect(Collectors.joining());
        }

        if (StringUtils.isEmpty(term)) {
            throw new IllegalArgumentException();
        }

        return Character.toLowerCase(term.charAt(0)) + term.substring(1);
    }

    private void fillProperties(Map<String, Object> parent, Map<String, Object> schemas,
                                GenerationContext generationContext) {

        TopLevelAbie topLevelAbie = generationContext.getTopLevelAbie();
        ABIE abie = generationContext.findAbie(topLevelAbie.getAbieId());
        ASBIEP asbiep = generationContext.receiveASBIEP(abie);
        ABIE typeAbie = generationContext.queryTargetABIE(asbiep);

        fillProperties(parent, schemas, asbiep, typeAbie, false, generationContext);
    }

    private void fillProperties(Map<String, Object> parent,
                                Map<String, Object> schemas,
                                ASBIE asbie,
                                GenerationContext generationContext) {
        ASBIEP asbiep = generationContext.queryAssocToASBIEP(asbie);
        ABIE typeAbie = generationContext.queryTargetABIE2(asbiep);

        ASCC ascc = generationContext.queryBasedASCC(asbie);

        int minVal = asbie.getCardinalityMin();
        int maxVal = asbie.getCardinalityMax();
        // Issue #562
        boolean isArray = (maxVal < 0 || maxVal > 1);
        boolean isNillable = asbie.isNillable();

        ASCCP asccp = generationContext.queryBasedASCCP(asbiep);
        String name = camelCase(asccp.getPropertyTerm());
        if (minVal > 0) {
            List<String> parentRequired = (List<String>) parent.get("required");
            if (parentRequired == null) {
                throw new IllegalStateException();
            }
            parentRequired.add(name);
        }

        Map<String, Object> properties = new LinkedHashMap();
        if (!parent.containsKey("properties")) {
            parent.put("properties", new LinkedHashMap<String, Object>());
        }

        if (option.isBieDefinition()) {
            String definition = asbie.getDefinition();
            if (!StringUtils.isEmpty(definition)) {
                properties.put("description", definition);
            }
        }

        properties.put("type", "object");
        properties.put("required", new ArrayList());
        properties.put("additionalProperties", false);
        properties.put("properties", new LinkedHashMap<String, Object>());

        fillProperties(properties, schemas, typeAbie, generationContext);

        if (((List) properties.get("required")).isEmpty()) {
            properties.remove("required");
        }

        properties = oneOf(allOf(properties), isNillable);

        if (isArray) {
            Map<String, Object> items = new LinkedHashMap();
            items.putAll(properties);

            properties = new LinkedHashMap();

            String description = (String) items.remove("description");
            if (!StringUtils.isEmpty(description)) {
                properties.put("description", description);
            }
            properties.put("type", "array");
            if (minVal > 0) {
                properties.put("minItems", minVal);
            }
            if (maxVal > 0) {
                properties.put("maxItems", maxVal);
            }
            properties.put("items", items);
        }

        ((Map<String, Object>) parent.get("properties")).put(name, properties);
    }

    private void fillProperties(Map<String, Object> parent,
                                Map<String, Object> schemas,
                                ASBIEP asbiep, ABIE abie,
                                boolean isArray,
                                GenerationContext generationContext) {

        ASCCP asccp = generationContext.queryBasedASCCP(asbiep);
        String name = camelCase(asccp.getPropertyTerm());

        List<String> parentRequired = (List<String>) parent.get("required");
        parentRequired.add(name);

        Map<String, Object> properties = new LinkedHashMap();
        if (!parent.containsKey("properties")) {
            parent.put("properties", new LinkedHashMap<String, Object>());
        }

        if (option.isBieDefinition()) {
            String definition = abie.getDefinition();
            if (!StringUtils.isEmpty(definition)) {
                properties.put("description", definition);
            }
        }

        /*
         * Issue #550
         */
        if (!isArray) {
            properties.put("type", "object");
        }
        properties.put("required", new ArrayList());
        properties.put("additionalProperties", false);

        fillProperties(properties, schemas, abie, generationContext);

        if (((List) properties.get("required")).isEmpty()) {
            properties.remove("required");
        }

        /*
         * Issue #575
         */
        if (isArray) {
            Map<String, Object> items = new LinkedHashMap(properties);
            properties = new LinkedHashMap();

            String description = (String) items.remove("description");
            if (!StringUtils.isEmpty(description)) {
                properties.put("description", description);
            }
            properties.put("type", "array");
            properties.put("items", items);
        }

        ((Map<String, Object>) parent.get("properties")).put(name, properties);
    }

    private Map<String, Object> toProperties(Xbt xbt) {
        String openapi30Map = xbt.getOpenapi30Map();
        try {
            return mapper.readValue(openapi30Map, LinkedHashMap.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String fillSchemas(Map<String, Object> schemas,
                                   Xbt xbt) {
        String builtInType = xbt.getBuiltinType();
        if (builtInType.startsWith("xsd:")) {
            builtInType = builtInType.substring(4);
        }
        if (!schemas.containsKey(builtInType)) {
            Map<String, Object> content = toProperties(xbt);
            schemas.put(builtInType, content);
        }

        return "#/components/schemas/" + builtInType;
    }

    private String fillSchemas(Map<String, Object> schemas,
                                   BBIE bbie, CodeList codeList) {
        DT bdt = generationContext.queryAssocBDT(bbie);
        BdtPriRestri bdtPriRestri =
                generationContext.findBdtPriRestriByBdtIdAndDefaultIsTrue(bdt.getDtId());

        Map<String, Object> properties;
        if (bdtPriRestri.getCodeListId() != null) {
            properties = new LinkedHashMap();
            properties.put("type", "string");
        } else {
            CdtAwdPriXpsTypeMap cdtAwdPriXpsTypeMap =
                    generationContext.findCdtAwdPriXpsTypeMap(bdtPriRestri.getCdtAwdPriXpsTypeMapId());
            Xbt xbt = generationContext.findXbt(cdtAwdPriXpsTypeMap.getXbtId());
            properties = toProperties(xbt);
        }

        return fillSchemas(properties, schemas, codeList);
    }

    private String fillSchemas(Map<String, Object> schemas,
                                   BBIESC bbieSc, CodeList codeList) {
        DTSC dtSc = generationContext.findDtSc(bbieSc.getDtScId());
        BdtScPriRestri bdtScPriRestri =
                generationContext.findBdtScPriRestriByBdtScIdAndDefaultIsTrue(dtSc.getDtScId());

        Map<String, Object> properties;
        if (bdtScPriRestri.getCodeListId() != null) {
            properties = new LinkedHashMap();
            properties.put("type", "string");
        } else {
            CdtScAwdPriXpsTypeMap cdtScAwdPriXpsTypeMap =
                    generationContext.findCdtScAwdPriXpsTypeMap(bdtScPriRestri.getCdtScAwdPriXpsTypeMapId());
            Xbt xbt = generationContext.findXbt(cdtScAwdPriXpsTypeMap.getXbtId());
            properties = toProperties(xbt);
        }

        return fillSchemas(properties, schemas, codeList);
    }

    private String fillSchemas(Map<String, Object> properties,
                                   Map<String, Object> schemas,
                                   CodeList codeList) {

        String codeListName = getCodeListTypeName(codeList);
        /*
         * Issue #589
         */
        codeListName = Stream.of(codeListName.split("_"))
                .map(e -> camelCase(e)).collect(Collectors.joining("_"));

        if (!schemas.containsKey(codeListName)) {
            List<CodeListValue> codeListValues = generationContext.getCodeListValues(codeList);
            List<String> enumerations = codeListValues.stream().map(e -> e.getValue()).collect(Collectors.toList());
            if (!enumerations.isEmpty()) {
                properties.put("enum", enumerations);
            }

            schemas.put(codeListName, properties);
        }

        return "#/components/schemas/" + codeListName;
    }

    private String fillSchemas(Map<String, Object> schemas,
                                   AgencyIdList agencyIdList) {
        AgencyIdListValue agencyIdListValue =
                generationContext.findAgencyIdListValue(agencyIdList.getAgencyIdListValueId());
        String agencyListTypeName = getAgencyListTypeName(agencyIdList, agencyIdListValue);
        /*
         * Issue #589
         */
        agencyListTypeName = Stream.of(agencyListTypeName.split("_"))
                .map(e -> camelCase(e)).collect(Collectors.joining("_"));
        if (!schemas.containsKey(agencyListTypeName)) {
            Map<String, Object> properties = new LinkedHashMap();
            properties.put("type", "string");

            List<AgencyIdListValue> agencyIdListValues =
                    generationContext.findAgencyIdListValueByOwnerListId(agencyIdList.getAgencyIdListId());
            List<String> enumerations = agencyIdListValues.stream().map(e -> e.getValue()).collect(Collectors.toList());
            if (!enumerations.isEmpty()) {
                properties.put("enum", enumerations);
            }

            schemas.put(agencyListTypeName, properties);
        }

        return "#/components/schemas/" + agencyListTypeName;
    }

    private void fillProperties(Map<String, Object> parent,
                                Map<String, Object> schemas,
                                ABIE abie,
                                GenerationContext generationContext) {

        List<BIE> children = generationContext.queryChildBIEs(abie);
        for (BIE bie : children) {
            if (bie instanceof BBIE) {
                BBIE bbie = (BBIE) bie;
                fillProperties(parent, schemas, bbie, generationContext);
            } else {
                ASBIE asbie = (ASBIE) bie;
                if (isAnyProperty(asbie, generationContext)) {
                    parent.put("additionalProperties", true);
                } else {
                    fillProperties(parent, schemas, asbie, generationContext);
                }
            }
        }
    }

    private Object readJsonValue(String textContent) {
        try {
            return mapper.readValue(textContent, Object.class);
        } catch (Exception e) {
            logger.warn("Can't read JSON value from given text: " + textContent, e);
        }
        return null;
    }

    private void fillProperties(Map<String, Object> parent,
                                Map<String, Object> schemas,
                                BBIE bbie,
                                GenerationContext generationContext) {
        BCC bcc = generationContext.queryBasedBCC(bbie);
        BCCP bccp = generationContext.queryToBCCP(bcc);
        DT bdt = generationContext.queryBDT(bccp);

        int minVal = bbie.getCardinalityMin();
        int maxVal = bbie.getCardinalityMax();
        // Issue #562
        boolean isArray = (maxVal < 0 || maxVal > 1);
        boolean isNillable = bbie.isNillable();

        String name = camelCase(bccp.getPropertyTerm());

        Map<String, Object> properties = new LinkedHashMap();
        if (!parent.containsKey("properties")) {
            parent.put("properties", new LinkedHashMap<String, Object>());
        }

        if (option.isBieDefinition()) {
            String definition = bbie.getDefinition();
            if (!StringUtils.isEmpty(definition)) {
                properties.put("description", definition);
            }
        }

        if (minVal > 0) {
            List<String> parentRequired = (List<String>) parent.get("required");
            if (parentRequired == null) {
                throw new IllegalStateException();
            }
            parentRequired.add(name);
        }

        // Issue #700
        if (!StringUtils.isEmpty(bbie.getFixedValue())) {
            properties.put("enum", Arrays.asList(bbie.getFixedValue()));
        } else if (!StringUtils.isEmpty(bbie.getDefaultValue())) {
            properties.put("default", bbie.getDefaultValue());
        }

        // Issue #692
        String exampleText = bbie.getExample();
        if (!StringUtils.isEmpty(exampleText)) {
            properties.put("example", exampleText);
        }

        // Issue #564
        String ref = getReference(schemas, bbie, bdt, generationContext);
        List<BBIESC> bbieScList = generationContext.queryBBIESCs(bbie)
                .stream().filter(e -> e.getCardinalityMax() != 0).collect(Collectors.toList());
        if (bbieScList.isEmpty()) {
            properties.put("$ref", ref);
            properties = oneOf(allOf(properties), isNillable);
        } else {
            properties.put("type", "object");
            properties.put("required", new ArrayList());
            properties.put("additionalProperties", false);
            properties.put("properties", new LinkedHashMap<String, Object>());

            Map<String, Object> contentProperties = new LinkedHashMap();
            contentProperties.put("$ref", ref);
            for (String key : Arrays.asList("enum", "default", "example")) {
                if (properties.containsKey(key)) {
                    contentProperties.put(key, properties.remove(key));
                }
            }

            ((List<String>) properties.get("required")).add("content");
            ((Map<String, Object>) properties.get("properties"))
                    .put("content", oneOf(allOf(contentProperties), isNillable));

            for (BBIESC bbieSc : bbieScList) {
                fillProperties(properties, schemas, bbieSc, generationContext);
            }
        }

        if (isArray) {
            String description = (String) properties.remove("description");
            Map<String, Object> items = new LinkedHashMap(properties);
            properties = new LinkedHashMap();
            if (!StringUtils.isEmpty(description)) {
                properties.put("description", description);
            }
            properties.put("type", "array");
            if (minVal > 0) {
                properties.put("minItems", minVal);
            }
            if (maxVal > 0) {
                properties.put("maxItems", maxVal);
            }
            properties.put("items", items);
        }

        ((Map<String, Object>) parent.get("properties")).put(name, properties);
    }

    private Map<String, Object> allOf(Map<String, Object> properties) {
        if (properties.containsKey("$ref") && properties.size() > 1) {
            Map<String, Object> prop = new LinkedHashMap();
            Map<String, Object> refMap = ImmutableMap.<String, Object>builder()
                    .put("$ref", properties.remove("$ref"))
                    .build();
            prop.put("allOf", (properties.isEmpty()) ? Arrays.asList(refMap) : Arrays.asList(refMap, properties));

            return prop;
        }

        return properties;
    }

    private Map<String, Object> oneOf(Map<String, Object> properties,
                                      boolean isNillable) {
        if (isNillable) {
            Map<String, Object> prop = new LinkedHashMap();
            prop.put("oneOf", Arrays.asList(
                    ImmutableMap.builder()
                            .put("nullable", true)
                            .build(),
                    properties
            ));

            return prop;
        }

        return properties;
    }

    private String getReference(Map<String, Object> schemas, BBIE bbie, DT bdt,
                                GenerationContext generationContext) {
        CodeList codeList = getCodeList(generationContext, bbie, bdt);
        String ref;
        if (codeList != null) {
            ref = fillSchemas(schemas, bbie, codeList);
        } else {
            AgencyIdList agencyIdList = generationContext.getAgencyIdList(bbie);
            if (agencyIdList != null) {
                ref = fillSchemas(schemas, agencyIdList);
            } else {
                Xbt xbt;
                if (bbie.getBdtPriRestriId() == null) {
                    BdtPriRestri bdtPriRestri =
                            generationContext.findBdtPriRestriByBdtIdAndDefaultIsTrue(bdt.getDtId());
                    xbt = getXbt(generationContext, bdtPriRestri);
                } else {
                    BdtPriRestri bdtPriRestri =
                            generationContext.findBdtPriRestri(bbie.getBdtPriRestriId());
                    xbt = getXbt(generationContext, bdtPriRestri);
                }

                ref = fillSchemas(schemas, xbt);
            }
        }

        return ref;
    }

    private void fillProperties(Map<String, Object> parent,
                                Map<String, Object> schemas,
                                BBIESC bbieSc,
                                GenerationContext generationContext) {
        int minVal = bbieSc.getCardinalityMin();
        int maxVal = bbieSc.getCardinalityMax();
        if (maxVal == 0) {
            return;
        }

        DTSC dtSc = generationContext.findDtSc(bbieSc.getDtScId());
        String name = toName(dtSc.getPropertyTerm(), dtSc.getRepresentationTerm(), rt -> {
            if ("Text".equals(rt)) {
                return "";
            }
            return rt;
        }, true);
        Map<String, Object> properties = new LinkedHashMap();

        if (option.isBieDefinition()) {
            String definition = bbieSc.getDefinition();
            if (!StringUtils.isEmpty(definition)) {
                properties.put("description", definition);
            }
        }

        if (minVal > 0) {
            ((List<String>) parent.get("required")).add(name);
        }

        // Issue #596
        if (!StringUtils.isEmpty(bbieSc.getFixedValue())) {
            properties.put("enum", Arrays.asList(bbieSc.getFixedValue()));
        } else if (!StringUtils.isEmpty(bbieSc.getDefaultValue())) {
            properties.put("default", bbieSc.getDefaultValue());
        }

        // Issue #692
        String exampleText = bbieSc.getExample();
        if (!StringUtils.isEmpty(exampleText)) {
            properties.put("example", exampleText);
        }

        CodeList codeList = generationContext.getCodeList(bbieSc);
        String ref;
        if (codeList != null) {
            ref = fillSchemas(schemas, bbieSc, codeList);
        } else {
            AgencyIdList agencyIdList = generationContext.getAgencyIdList(bbieSc);
            if (agencyIdList != null) {
                ref = fillSchemas(schemas, agencyIdList);
            } else {
                Xbt xbt;
                if (bbieSc.getDtScPriRestriId() == null) {
                    BdtScPriRestri bdtScPriRestri =
                            generationContext.findBdtScPriRestriByBdtScIdAndDefaultIsTrue(dtSc.getDtScId());
                    CdtScAwdPriXpsTypeMap cdtScAwdPriXpsTypeMap =
                            generationContext.findCdtScAwdPriXpsTypeMap(bdtScPriRestri.getCdtScAwdPriXpsTypeMapId());
                    xbt = generationContext.findXbt(cdtScAwdPriXpsTypeMap.getXbtId());
                } else {
                    BdtScPriRestri bdtScPriRestri =
                            generationContext.findBdtScPriRestri(bbieSc.getDtScPriRestriId());
                    CdtScAwdPriXpsTypeMap cdtScAwdPriXpsTypeMap =
                            generationContext.findCdtScAwdPriXpsTypeMap(bdtScPriRestri.getCdtScAwdPriXpsTypeMapId());
                    xbt = generationContext.findXbt(cdtScAwdPriXpsTypeMap.getXbtId());
                }

                ref = fillSchemas(schemas, xbt);
            }
        }

        properties.put("$ref", ref);
        properties = allOf(properties);

        ((Map<String, Object>) parent.get("properties")).put(name, properties);
    }

    private void ensureRoot() {
        if (root == null) {
            throw new IllegalStateException();
        }
    }

    @Override
    public File asFile(String filename) throws IOException {
        ensureRoot();

        File tempFile = File.createTempFile(SrtGuid.randomGuid(), null);
        String extension = (expressionMapper.getFactory() instanceof YAMLFactory) ? "yml" : "json";

        tempFile = new File(tempFile.getParentFile(), filename + "." + extension);

        expressionMapper.writeValue(tempFile, root);
        logger.info("Open API " + OPEN_API_VERSION + " Schema is generated: " + tempFile);

        return tempFile;
    }
}
