/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.OasDocTag.OasDocTagPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.OasExternalDoc.OasExternalDocPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.OasExternalDocDoc.OasExternalDocDocPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.OasResource.OasResourcePath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.OasServer.OasServerPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.OasTag.OasTagPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.OasDocRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasDoc extends TableImpl<OasDocRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.oas_doc</code>
     */
    public static final OasDoc OAS_DOC = new OasDoc();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OasDocRecord> getRecordType() {
        return OasDocRecord.class;
    }

    /**
     * The column <code>oagi.oas_doc.oas_doc_id</code>. The primary key of the
     * record.
     */
    public final TableField<OasDocRecord, ULong> OAS_DOC_ID = createField(DSL.name("oas_doc_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_doc.guid</code>. The GUID of the record.
     */
    public final TableField<OasDocRecord, String> GUID = createField(DSL.name("guid"), SQLDataType.VARCHAR(41).nullable(false), this, "The GUID of the record.");

    /**
     * The column <code>oagi.oas_doc.open_api_version</code>. REQUIRED. This
     * string MUST be the semantic version number of the OpenAPI Specification
     * version that the OpenAPI document uses. The openapi field SHOULD be used
     * by tooling specifications and clients to interpret the OpenAPI document.
     * This is not related to the API info.version string.
     */
    public final TableField<OasDocRecord, String> OPEN_API_VERSION = createField(DSL.name("open_api_version"), SQLDataType.VARCHAR(20).nullable(false), this, "REQUIRED. This string MUST be the semantic version number of the OpenAPI Specification version that the OpenAPI document uses. The openapi field SHOULD be used by tooling specifications and clients to interpret the OpenAPI document. This is not related to the API info.version string.");

    /**
     * The column <code>oagi.oas_doc.title</code>. The title of the API.
     */
    public final TableField<OasDocRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.CLOB.nullable(false), this, "The title of the API.");

    /**
     * The column <code>oagi.oas_doc.description</code>. A short description of
     * the API. CommonMark syntax MAY be used for rich text representation.
     */
    public final TableField<OasDocRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "A short description of the API. CommonMark syntax MAY be used for rich text representation.");

    /**
     * The column <code>oagi.oas_doc.terms_of_service</code>. A URL to the Terms
     * of Service for the API. MUST be in the format of a URL.
     */
    public final TableField<OasDocRecord, String> TERMS_OF_SERVICE = createField(DSL.name("terms_of_service"), SQLDataType.VARCHAR(250).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "A URL to the Terms of Service for the API. MUST be in the format of a URL.");

    /**
     * The column <code>oagi.oas_doc.version</code>. REQUIRED. The version of
     * the OpenAPI document (which is distinct from the OpenAPI Specification
     * version or the API implementation version).
     */
    public final TableField<OasDocRecord, String> VERSION = createField(DSL.name("version"), SQLDataType.VARCHAR(20).nullable(false), this, "REQUIRED. The version of the OpenAPI document (which is distinct from the OpenAPI Specification version or the API implementation version).");

    /**
     * The column <code>oagi.oas_doc.contact_name</code>. The identifying name
     * of the contact person/organization.
     */
    public final TableField<OasDocRecord, String> CONTACT_NAME = createField(DSL.name("contact_name"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "The identifying name of the contact person/organization.");

    /**
     * The column <code>oagi.oas_doc.contact_url</code>. The URL pointing to the
     * contact information. MUST be in the format of a URL.
     */
    public final TableField<OasDocRecord, String> CONTACT_URL = createField(DSL.name("contact_url"), SQLDataType.VARCHAR(250).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "The URL pointing to the contact information. MUST be in the format of a URL.");

    /**
     * The column <code>oagi.oas_doc.contact_email</code>. The email address of
     * the contact person/organization. MUST be in the format of an email
     * address.
     */
    public final TableField<OasDocRecord, String> CONTACT_EMAIL = createField(DSL.name("contact_email"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "The email address of the contact person/organization. MUST be in the format of an email address.");

    /**
     * The column <code>oagi.oas_doc.license_name</code>. REQUIRED if the
     * license used for the API. The license name used for the API.
     */
    public final TableField<OasDocRecord, String> LICENSE_NAME = createField(DSL.name("license_name"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "REQUIRED if the license used for the API. The license name used for the API.");

    /**
     * The column <code>oagi.oas_doc.license_url</code>. A URL to the license
     * used for the API. MUST be in the format of a URL.
     */
    public final TableField<OasDocRecord, String> LICENSE_URL = createField(DSL.name("license_url"), SQLDataType.VARCHAR(250).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "A URL to the license used for the API. MUST be in the format of a URL.");

    /**
     * The column <code>oagi.oas_doc.owner_user_id</code>. The user who owns the
     * record.
     */
    public final TableField<OasDocRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who owns the record.");

    /**
     * The column <code>oagi.oas_doc.created_by</code>. The user who creates the
     * record.
     */
    public final TableField<OasDocRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who creates the record.");

    /**
     * The column <code>oagi.oas_doc.last_updated_by</code>. The user who last
     * updates the record.
     */
    public final TableField<OasDocRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who last updates the record.");

    /**
     * The column <code>oagi.oas_doc.creation_timestamp</code>. The timestamp
     * when the record is created.
     */
    public final TableField<OasDocRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is created.");

    /**
     * The column <code>oagi.oas_doc.last_update_timestamp</code>. The timestamp
     * when the record is last updated.
     */
    public final TableField<OasDocRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is last updated.");

    private OasDoc(Name alias, Table<OasDocRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private OasDoc(Name alias, Table<OasDocRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.oas_doc</code> table reference
     */
    public OasDoc(String alias) {
        this(DSL.name(alias), OAS_DOC);
    }

    /**
     * Create an aliased <code>oagi.oas_doc</code> table reference
     */
    public OasDoc(Name alias) {
        this(alias, OAS_DOC);
    }

    /**
     * Create a <code>oagi.oas_doc</code> table reference
     */
    public OasDoc() {
        this(DSL.name("oas_doc"), null);
    }

    public <O extends Record> OasDoc(Table<O> path, ForeignKey<O, OasDocRecord> childPath, InverseForeignKey<O, OasDocRecord> parentPath) {
        super(path, childPath, parentPath, OAS_DOC);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class OasDocPath extends OasDoc implements Path<OasDocRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> OasDocPath(Table<O> path, ForeignKey<O, OasDocRecord> childPath, InverseForeignKey<O, OasDocRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private OasDocPath(Name alias, Table<OasDocRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public OasDocPath as(String alias) {
            return new OasDocPath(DSL.name(alias), this);
        }

        @Override
        public OasDocPath as(Name alias) {
            return new OasDocPath(alias, this);
        }

        @Override
        public OasDocPath as(Table<?> alias) {
            return new OasDocPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<OasDocRecord, ULong> getIdentity() {
        return (Identity<OasDocRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<OasDocRecord> getPrimaryKey() {
        return Keys.KEY_OAS_DOC_PRIMARY;
    }

    @Override
    public List<ForeignKey<OasDocRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OAS_DOC_CREATED_BY_FK, Keys.OAS_DOC_LAST_UPDATED_BY_FK, Keys.OAS_DOC_OWNER_USER_ID_FK);
    }

    private transient AppUserPath _oasDocCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_doc_created_by_fk</code> key.
     */
    public AppUserPath oasDocCreatedByFk() {
        if (_oasDocCreatedByFk == null)
            _oasDocCreatedByFk = new AppUserPath(this, Keys.OAS_DOC_CREATED_BY_FK, null);

        return _oasDocCreatedByFk;
    }

    private transient AppUserPath _oasDocLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_doc_last_updated_by_fk</code> key.
     */
    public AppUserPath oasDocLastUpdatedByFk() {
        if (_oasDocLastUpdatedByFk == null)
            _oasDocLastUpdatedByFk = new AppUserPath(this, Keys.OAS_DOC_LAST_UPDATED_BY_FK, null);

        return _oasDocLastUpdatedByFk;
    }

    private transient AppUserPath _oasDocOwnerUserIdFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_doc_owner_user_id_fk</code> key.
     */
    public AppUserPath oasDocOwnerUserIdFk() {
        if (_oasDocOwnerUserIdFk == null)
            _oasDocOwnerUserIdFk = new AppUserPath(this, Keys.OAS_DOC_OWNER_USER_ID_FK, null);

        return _oasDocOwnerUserIdFk;
    }

    private transient OasDocTagPath _oasDocTag;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_doc_tag</code>
     * table
     */
    public OasDocTagPath oasDocTag() {
        if (_oasDocTag == null)
            _oasDocTag = new OasDocTagPath(this, null, Keys.OAS_DOC_TAG_OAS_DOC_ID_FK.getInverseKey());

        return _oasDocTag;
    }

    private transient OasExternalDocDocPath _oasExternalDocDoc;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.oas_external_doc_doc</code> table
     */
    public OasExternalDocDocPath oasExternalDocDoc() {
        if (_oasExternalDocDoc == null)
            _oasExternalDocDoc = new OasExternalDocDocPath(this, null, Keys.OAS_EXTERNAL_DOC_OAS_DOC_ID_FK.getInverseKey());

        return _oasExternalDocDoc;
    }

    private transient OasResourcePath _oasResource;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_resource</code>
     * table
     */
    public OasResourcePath oasResource() {
        if (_oasResource == null)
            _oasResource = new OasResourcePath(this, null, Keys.OAS_RESOURCE_OAS_DOC_ID_FK.getInverseKey());

        return _oasResource;
    }

    private transient OasServerPath _oasServer;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_server</code>
     * table
     */
    public OasServerPath oasServer() {
        if (_oasServer == null)
            _oasServer = new OasServerPath(this, null, Keys.OAS_SERVER_OAS_DOC_ID_FK.getInverseKey());

        return _oasServer;
    }

    /**
     * Get the implicit many-to-many join path to the <code>oagi.oas_tag</code>
     * table
     */
    public OasTagPath oasTag() {
        return oasDocTag().oasTag();
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>oagi.oas_external_doc</code> table
     */
    public OasExternalDocPath oasExternalDoc() {
        return oasExternalDocDoc().oasExternalDoc();
    }

    @Override
    public OasDoc as(String alias) {
        return new OasDoc(DSL.name(alias), this);
    }

    @Override
    public OasDoc as(Name alias) {
        return new OasDoc(alias, this);
    }

    @Override
    public OasDoc as(Table<?> alias) {
        return new OasDoc(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OasDoc rename(String name) {
        return new OasDoc(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasDoc rename(Name name) {
        return new OasDoc(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasDoc rename(Table<?> name) {
        return new OasDoc(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDoc where(Condition condition) {
        return new OasDoc(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDoc where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDoc where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDoc where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDoc where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDoc where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDoc where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDoc where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDoc whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDoc whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
