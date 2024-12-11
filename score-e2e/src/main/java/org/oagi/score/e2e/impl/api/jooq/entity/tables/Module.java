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
import org.oagi.score.e2e.impl.api.jooq.entity.tables.Module.ModulePath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleAccManifest.ModuleAccManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleAgencyIdListManifest.ModuleAgencyIdListManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleAsccpManifest.ModuleAsccpManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleBccpManifest.ModuleBccpManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleBlobContentManifest.ModuleBlobContentManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleCodeListManifest.ModuleCodeListManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleDtManifest.ModuleDtManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleSet.ModuleSetPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.ModuleXbtManifest.ModuleXbtManifestPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.Namespace.NamespacePath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.ModuleRecord;


/**
 * The module table stores information about a physical file, into which CC
 * components will be generated during the expression generation.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Module extends TableImpl<ModuleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.module</code>
     */
    public static final Module MODULE = new Module();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ModuleRecord> getRecordType() {
        return ModuleRecord.class;
    }

    /**
     * The column <code>oagi.module.module_id</code>. Primary, internal database
     * key.
     */
    public final TableField<ModuleRecord, ULong> MODULE_ID = createField(DSL.name("module_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary, internal database key.");

    /**
     * The column <code>oagi.module.module_set_id</code>. This indicates a
     * module set.
     */
    public final TableField<ModuleRecord, ULong> MODULE_SET_ID = createField(DSL.name("module_set_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "This indicates a module set.");

    /**
     * The column <code>oagi.module.parent_module_id</code>. This indicates a
     * parent module id. root module will be NULL.
     */
    public final TableField<ModuleRecord, ULong> PARENT_MODULE_ID = createField(DSL.name("parent_module_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "This indicates a parent module id. root module will be NULL.");

    /**
     * The column <code>oagi.module.type</code>. This is a type column for
     * indicates module is FILE or DIRECTORY.
     */
    public final TableField<ModuleRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(45).nullable(false), this, "This is a type column for indicates module is FILE or DIRECTORY.");

    /**
     * The column <code>oagi.module.path</code>. Absolute path to the module.
     */
    public final TableField<ModuleRecord, String> PATH = createField(DSL.name("path"), SQLDataType.CLOB.nullable(false), this, "Absolute path to the module.");

    /**
     * The column <code>oagi.module.name</code>. The is the filename of the
     * module. The reason to not including the extension is that the extension
     * maybe dependent on the expression. For XML schema, '.xsd' maybe added; or
     * for JSON, '.json' maybe added as the file extension.
     */
    public final TableField<ModuleRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "The is the filename of the module. The reason to not including the extension is that the extension maybe dependent on the expression. For XML schema, '.xsd' maybe added; or for JSON, '.json' maybe added as the file extension.");

    /**
     * The column <code>oagi.module.namespace_id</code>. Note that a release
     * record has a namespace associated. The NAMESPACE_ID, if specified here,
     * overrides the release's namespace. However, the NAMESPACE_ID associated
     * with the component takes the highest precedence.
     */
    public final TableField<ModuleRecord, ULong> NAMESPACE_ID = createField(DSL.name("namespace_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Note that a release record has a namespace associated. The NAMESPACE_ID, if specified here, overrides the release's namespace. However, the NAMESPACE_ID associated with the component takes the highest precedence.");

    /**
     * The column <code>oagi.module.version_num</code>. This is the version
     * number to be assigned to the schema module.
     */
    public final TableField<ModuleRecord, String> VERSION_NUM = createField(DSL.name("version_num"), SQLDataType.VARCHAR(45).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "This is the version number to be assigned to the schema module.");

    /**
     * The column <code>oagi.module.created_by</code>. Foreign key to the
     * APP_USER table. It indicates the user who created this MODULE.
     */
    public final TableField<ModuleRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. It indicates the user who created this MODULE.");

    /**
     * The column <code>oagi.module.last_updated_by</code>. Foreign key to the
     * APP_USER table referring to the last user who updated the record. 
     * 
     * In the history record, this should always be the user who is editing the
     * entity (perhaps except when the ownership has just been changed).
     */
    public final TableField<ModuleRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table referring to the last user who updated the record. \n\nIn the history record, this should always be the user who is editing the entity (perhaps except when the ownership has just been changed).");

    /**
     * The column <code>oagi.module.owner_user_id</code>. Foreign key to the
     * APP_USER table identifying the user who can update or delete the record.
     */
    public final TableField<ModuleRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table identifying the user who can update or delete the record.");

    /**
     * The column <code>oagi.module.creation_timestamp</code>. The timestamp
     * when the record was first created.
     */
    public final TableField<ModuleRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was first created.");

    /**
     * The column <code>oagi.module.last_update_timestamp</code>. The timestamp
     * when the record was last updated.
     */
    public final TableField<ModuleRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was last updated.");

    private Module(Name alias, Table<ModuleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Module(Name alias, Table<ModuleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("The module table stores information about a physical file, into which CC components will be generated during the expression generation."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.module</code> table reference
     */
    public Module(String alias) {
        this(DSL.name(alias), MODULE);
    }

    /**
     * Create an aliased <code>oagi.module</code> table reference
     */
    public Module(Name alias) {
        this(alias, MODULE);
    }

    /**
     * Create a <code>oagi.module</code> table reference
     */
    public Module() {
        this(DSL.name("module"), null);
    }

    public <O extends Record> Module(Table<O> path, ForeignKey<O, ModuleRecord> childPath, InverseForeignKey<O, ModuleRecord> parentPath) {
        super(path, childPath, parentPath, MODULE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ModulePath extends Module implements Path<ModuleRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> ModulePath(Table<O> path, ForeignKey<O, ModuleRecord> childPath, InverseForeignKey<O, ModuleRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ModulePath(Name alias, Table<ModuleRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ModulePath as(String alias) {
            return new ModulePath(DSL.name(alias), this);
        }

        @Override
        public ModulePath as(Name alias) {
            return new ModulePath(alias, this);
        }

        @Override
        public ModulePath as(Table<?> alias) {
            return new ModulePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<ModuleRecord, ULong> getIdentity() {
        return (Identity<ModuleRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<ModuleRecord> getPrimaryKey() {
        return Keys.KEY_MODULE_PRIMARY;
    }

    @Override
    public List<ForeignKey<ModuleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.MODULE_CREATED_BY_FK, Keys.MODULE_LAST_UPDATED_BY_FK, Keys.MODULE_MODULE_SET_ID_FK, Keys.MODULE_NAMESPACE_ID_FK, Keys.MODULE_OWNER_USER_ID_FK, Keys.MODULE_PARENT_MODULE_ID_FK);
    }

    private transient AppUserPath _moduleCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>module_created_by_fk</code> key.
     */
    public AppUserPath moduleCreatedByFk() {
        if (_moduleCreatedByFk == null)
            _moduleCreatedByFk = new AppUserPath(this, Keys.MODULE_CREATED_BY_FK, null);

        return _moduleCreatedByFk;
    }

    private transient AppUserPath _moduleLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>module_last_updated_by_fk</code> key.
     */
    public AppUserPath moduleLastUpdatedByFk() {
        if (_moduleLastUpdatedByFk == null)
            _moduleLastUpdatedByFk = new AppUserPath(this, Keys.MODULE_LAST_UPDATED_BY_FK, null);

        return _moduleLastUpdatedByFk;
    }

    private transient ModuleSetPath _moduleSet;

    /**
     * Get the implicit join path to the <code>oagi.module_set</code> table.
     */
    public ModuleSetPath moduleSet() {
        if (_moduleSet == null)
            _moduleSet = new ModuleSetPath(this, Keys.MODULE_MODULE_SET_ID_FK, null);

        return _moduleSet;
    }

    private transient NamespacePath _namespace;

    /**
     * Get the implicit join path to the <code>oagi.namespace</code> table.
     */
    public NamespacePath namespace() {
        if (_namespace == null)
            _namespace = new NamespacePath(this, Keys.MODULE_NAMESPACE_ID_FK, null);

        return _namespace;
    }

    private transient AppUserPath _moduleOwnerUserIdFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>module_owner_user_id_fk</code> key.
     */
    public AppUserPath moduleOwnerUserIdFk() {
        if (_moduleOwnerUserIdFk == null)
            _moduleOwnerUserIdFk = new AppUserPath(this, Keys.MODULE_OWNER_USER_ID_FK, null);

        return _moduleOwnerUserIdFk;
    }

    private transient ModulePath _module;

    /**
     * Get the implicit join path to the <code>oagi.module</code> table.
     */
    public ModulePath module() {
        if (_module == null)
            _module = new ModulePath(this, Keys.MODULE_PARENT_MODULE_ID_FK, null);

        return _module;
    }

    private transient ModuleAccManifestPath _moduleAccManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_acc_manifest</code> table
     */
    public ModuleAccManifestPath moduleAccManifest() {
        if (_moduleAccManifest == null)
            _moduleAccManifest = new ModuleAccManifestPath(this, null, Keys.MODULE_ACC_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleAccManifest;
    }

    private transient ModuleAgencyIdListManifestPath _moduleAgencyIdListManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_agency_id_list_manifest</code> table
     */
    public ModuleAgencyIdListManifestPath moduleAgencyIdListManifest() {
        if (_moduleAgencyIdListManifest == null)
            _moduleAgencyIdListManifest = new ModuleAgencyIdListManifestPath(this, null, Keys.MODULE_AGENCY_ID_LIST_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleAgencyIdListManifest;
    }

    private transient ModuleAsccpManifestPath _moduleAsccpManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_asccp_manifest</code> table
     */
    public ModuleAsccpManifestPath moduleAsccpManifest() {
        if (_moduleAsccpManifest == null)
            _moduleAsccpManifest = new ModuleAsccpManifestPath(this, null, Keys.MODULE_ASCCP_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleAsccpManifest;
    }

    private transient ModuleBccpManifestPath _moduleBccpManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_bccp_manifest</code> table
     */
    public ModuleBccpManifestPath moduleBccpManifest() {
        if (_moduleBccpManifest == null)
            _moduleBccpManifest = new ModuleBccpManifestPath(this, null, Keys.MODULE_BCCP_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleBccpManifest;
    }

    private transient ModuleBlobContentManifestPath _moduleBlobContentManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_blob_content_manifest</code> table
     */
    public ModuleBlobContentManifestPath moduleBlobContentManifest() {
        if (_moduleBlobContentManifest == null)
            _moduleBlobContentManifest = new ModuleBlobContentManifestPath(this, null, Keys.MODULE_BLOB_CONTENT_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleBlobContentManifest;
    }

    private transient ModuleCodeListManifestPath _moduleCodeListManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_code_list_manifest</code> table
     */
    public ModuleCodeListManifestPath moduleCodeListManifest() {
        if (_moduleCodeListManifest == null)
            _moduleCodeListManifest = new ModuleCodeListManifestPath(this, null, Keys.MODULE_CODE_LIST_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleCodeListManifest;
    }

    private transient ModuleDtManifestPath _moduleDtManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_dt_manifest</code> table
     */
    public ModuleDtManifestPath moduleDtManifest() {
        if (_moduleDtManifest == null)
            _moduleDtManifest = new ModuleDtManifestPath(this, null, Keys.MODULE_DT_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleDtManifest;
    }

    private transient ModuleXbtManifestPath _moduleXbtManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_xbt_manifest</code> table
     */
    public ModuleXbtManifestPath moduleXbtManifest() {
        if (_moduleXbtManifest == null)
            _moduleXbtManifest = new ModuleXbtManifestPath(this, null, Keys.MODULE_XBT_MANIFEST_MODULE_ID_FK.getInverseKey());

        return _moduleXbtManifest;
    }

    @Override
    public Module as(String alias) {
        return new Module(DSL.name(alias), this);
    }

    @Override
    public Module as(Name alias) {
        return new Module(alias, this);
    }

    @Override
    public Module as(Table<?> alias) {
        return new Module(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Module rename(String name) {
        return new Module(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Module rename(Name name) {
        return new Module(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Module rename(Table<?> name) {
        return new Module(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Module where(Condition condition) {
        return new Module(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Module where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Module where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Module where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Module where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Module where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Module where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Module where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Module whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Module whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
