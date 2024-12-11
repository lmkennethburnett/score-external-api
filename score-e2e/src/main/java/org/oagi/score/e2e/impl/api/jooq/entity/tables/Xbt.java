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
import org.jooq.Index;
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
import org.oagi.score.e2e.impl.api.jooq.entity.Indexes;
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.CdtAwdPriXpsTypeMap.CdtAwdPriXpsTypeMapPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.CdtScAwdPriXpsTypeMap.CdtScAwdPriXpsTypeMapPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.Xbt.XbtPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.XbtManifest.XbtManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.XbtRecord;


/**
 * This table stores XML schema built-in types and OAGIS built-in types. OAGIS
 * built-in types are those types defined in the XMLSchemaBuiltinType and the
 * XMLSchemaBuiltinType Patterns schemas.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Xbt extends TableImpl<XbtRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.xbt</code>
     */
    public static final Xbt XBT = new Xbt();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<XbtRecord> getRecordType() {
        return XbtRecord.class;
    }

    /**
     * The column <code>oagi.xbt.xbt_id</code>. Primary, internal database key.
     */
    public final TableField<XbtRecord, ULong> XBT_ID = createField(DSL.name("xbt_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary, internal database key.");

    /**
     * The column <code>oagi.xbt.guid</code>. A globally unique identifier
     * (GUID).
     */
    public final TableField<XbtRecord, String> GUID = createField(DSL.name("guid"), SQLDataType.CHAR(32).nullable(false), this, "A globally unique identifier (GUID).");

    /**
     * The column <code>oagi.xbt.name</code>. Human understandable name of the
     * built-in type.
     */
    public final TableField<XbtRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(45).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "Human understandable name of the built-in type.");

    /**
     * The column <code>oagi.xbt.builtIn_type</code>. Built-in type as it should
     * appear in the XML schema including the namespace prefix. Namespace prefix
     * for the XML schema namespace is assumed to be 'xsd' and a default prefix
     * for the OAGIS built-int type.
     */
    public final TableField<XbtRecord, String> BUILTIN_TYPE = createField(DSL.name("builtIn_type"), SQLDataType.VARCHAR(45).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "Built-in type as it should appear in the XML schema including the namespace prefix. Namespace prefix for the XML schema namespace is assumed to be 'xsd' and a default prefix for the OAGIS built-int type.");

    /**
     * The column <code>oagi.xbt.jbt_draft05_map</code>.
     */
    public final TableField<XbtRecord, String> JBT_DRAFT05_MAP = createField(DSL.name("jbt_draft05_map"), SQLDataType.VARCHAR(500).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.xbt.openapi30_map</code>.
     */
    public final TableField<XbtRecord, String> OPENAPI30_MAP = createField(DSL.name("openapi30_map"), SQLDataType.VARCHAR(500).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.xbt.avro_map</code>.
     */
    public final TableField<XbtRecord, String> AVRO_MAP = createField(DSL.name("avro_map"), SQLDataType.VARCHAR(500).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.xbt.subtype_of_xbt_id</code>. Foreign key to the
     * XBT table itself. It indicates a super type of this XSD built-in type.
     */
    public final TableField<XbtRecord, ULong> SUBTYPE_OF_XBT_ID = createField(DSL.name("subtype_of_xbt_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the XBT table itself. It indicates a super type of this XSD built-in type.");

    /**
     * The column <code>oagi.xbt.schema_definition</code>.
     */
    public final TableField<XbtRecord, String> SCHEMA_DEFINITION = createField(DSL.name("schema_definition"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "");

    /**
     * The column <code>oagi.xbt.revision_doc</code>.
     */
    public final TableField<XbtRecord, String> REVISION_DOC = createField(DSL.name("revision_doc"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "");

    /**
     * The column <code>oagi.xbt.state</code>.
     */
    public final TableField<XbtRecord, Integer> STATE = createField(DSL.name("state"), SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>oagi.xbt.created_by</code>.
     */
    public final TableField<XbtRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.xbt.owner_user_id</code>.
     */
    public final TableField<XbtRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.xbt.last_updated_by</code>.
     */
    public final TableField<XbtRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.xbt.creation_timestamp</code>.
     */
    public final TableField<XbtRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>oagi.xbt.last_update_timestamp</code>.
     */
    public final TableField<XbtRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    /**
     * The column <code>oagi.xbt.is_deprecated</code>.
     */
    public final TableField<XbtRecord, Byte> IS_DEPRECATED = createField(DSL.name("is_deprecated"), SQLDataType.TINYINT.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "");

    private Xbt(Name alias, Table<XbtRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Xbt(Name alias, Table<XbtRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("This table stores XML schema built-in types and OAGIS built-in types. OAGIS built-in types are those types defined in the XMLSchemaBuiltinType and the XMLSchemaBuiltinType Patterns schemas."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.xbt</code> table reference
     */
    public Xbt(String alias) {
        this(DSL.name(alias), XBT);
    }

    /**
     * Create an aliased <code>oagi.xbt</code> table reference
     */
    public Xbt(Name alias) {
        this(alias, XBT);
    }

    /**
     * Create a <code>oagi.xbt</code> table reference
     */
    public Xbt() {
        this(DSL.name("xbt"), null);
    }

    public <O extends Record> Xbt(Table<O> path, ForeignKey<O, XbtRecord> childPath, InverseForeignKey<O, XbtRecord> parentPath) {
        super(path, childPath, parentPath, XBT);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class XbtPath extends Xbt implements Path<XbtRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> XbtPath(Table<O> path, ForeignKey<O, XbtRecord> childPath, InverseForeignKey<O, XbtRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private XbtPath(Name alias, Table<XbtRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public XbtPath as(String alias) {
            return new XbtPath(DSL.name(alias), this);
        }

        @Override
        public XbtPath as(Name alias) {
            return new XbtPath(alias, this);
        }

        @Override
        public XbtPath as(Table<?> alias) {
            return new XbtPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.XBT_XBT_GUID_IDX, Indexes.XBT_XBT_LAST_UPDATE_TIMESTAMP_DESC_IDX);
    }

    @Override
    public Identity<XbtRecord, ULong> getIdentity() {
        return (Identity<XbtRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<XbtRecord> getPrimaryKey() {
        return Keys.KEY_XBT_PRIMARY;
    }

    @Override
    public List<ForeignKey<XbtRecord, ?>> getReferences() {
        return Arrays.asList(Keys.XBT_CREATED_BY_FK, Keys.XBT_LAST_UPDATED_BY_FK, Keys.XBT_OWNER_USER_ID_FK, Keys.XBT_SUBTYPE_OF_XBT_ID_FK);
    }

    private transient AppUserPath _xbtCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>xbt_created_by_fk</code> key.
     */
    public AppUserPath xbtCreatedByFk() {
        if (_xbtCreatedByFk == null)
            _xbtCreatedByFk = new AppUserPath(this, Keys.XBT_CREATED_BY_FK, null);

        return _xbtCreatedByFk;
    }

    private transient AppUserPath _xbtLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>xbt_last_updated_by_fk</code> key.
     */
    public AppUserPath xbtLastUpdatedByFk() {
        if (_xbtLastUpdatedByFk == null)
            _xbtLastUpdatedByFk = new AppUserPath(this, Keys.XBT_LAST_UPDATED_BY_FK, null);

        return _xbtLastUpdatedByFk;
    }

    private transient AppUserPath _xbtOwnerUserIdFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>xbt_owner_user_id_fk</code> key.
     */
    public AppUserPath xbtOwnerUserIdFk() {
        if (_xbtOwnerUserIdFk == null)
            _xbtOwnerUserIdFk = new AppUserPath(this, Keys.XBT_OWNER_USER_ID_FK, null);

        return _xbtOwnerUserIdFk;
    }

    private transient XbtPath _xbt;

    /**
     * Get the implicit join path to the <code>oagi.xbt</code> table.
     */
    public XbtPath xbt() {
        if (_xbt == null)
            _xbt = new XbtPath(this, Keys.XBT_SUBTYPE_OF_XBT_ID_FK, null);

        return _xbt;
    }

    private transient CdtAwdPriXpsTypeMapPath _cdtAwdPriXpsTypeMap;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.cdt_awd_pri_xps_type_map</code> table
     */
    public CdtAwdPriXpsTypeMapPath cdtAwdPriXpsTypeMap() {
        if (_cdtAwdPriXpsTypeMap == null)
            _cdtAwdPriXpsTypeMap = new CdtAwdPriXpsTypeMapPath(this, null, Keys.CDT_AWD_PRI_XPS_TYPE_MAP_XBT_ID_FK.getInverseKey());

        return _cdtAwdPriXpsTypeMap;
    }

    private transient CdtScAwdPriXpsTypeMapPath _cdtScAwdPriXpsTypeMap;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.cdt_sc_awd_pri_xps_type_map</code> table
     */
    public CdtScAwdPriXpsTypeMapPath cdtScAwdPriXpsTypeMap() {
        if (_cdtScAwdPriXpsTypeMap == null)
            _cdtScAwdPriXpsTypeMap = new CdtScAwdPriXpsTypeMapPath(this, null, Keys.CDT_SC_AWD_PRI_XPS_TYPE_MAP_XBT_ID_FK.getInverseKey());

        return _cdtScAwdPriXpsTypeMap;
    }

    private transient XbtManifestPath _xbtManifest;

    /**
     * Get the implicit to-many join path to the <code>oagi.xbt_manifest</code>
     * table
     */
    public XbtManifestPath xbtManifest() {
        if (_xbtManifest == null)
            _xbtManifest = new XbtManifestPath(this, null, Keys.XBT_MANIFEST_XBT_ID_FK.getInverseKey());

        return _xbtManifest;
    }

    @Override
    public Xbt as(String alias) {
        return new Xbt(DSL.name(alias), this);
    }

    @Override
    public Xbt as(Name alias) {
        return new Xbt(alias, this);
    }

    @Override
    public Xbt as(Table<?> alias) {
        return new Xbt(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Xbt rename(String name) {
        return new Xbt(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Xbt rename(Name name) {
        return new Xbt(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Xbt rename(Table<?> name) {
        return new Xbt(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Xbt where(Condition condition) {
        return new Xbt(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Xbt where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Xbt where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Xbt where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Xbt where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Xbt where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Xbt where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Xbt where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Xbt whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Xbt whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
