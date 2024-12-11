/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


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
import org.oagi.score.repo.api.impl.jooq.entity.Keys;
import org.oagi.score.repo.api.impl.jooq.entity.Oagi;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BccpManifest.BccpManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Module.ModulePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.ModuleSetRelease.ModuleSetReleasePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.ModuleBccpManifestRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ModuleBccpManifest extends TableImpl<ModuleBccpManifestRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.module_bccp_manifest</code>
     */
    public static final ModuleBccpManifest MODULE_BCCP_MANIFEST = new ModuleBccpManifest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ModuleBccpManifestRecord> getRecordType() {
        return ModuleBccpManifestRecord.class;
    }

    /**
     * The column
     * <code>oagi.module_bccp_manifest.module_bccp_manifest_id</code>. Primary
     * key.
     */
    public final TableField<ModuleBccpManifestRecord, ULong> MODULE_BCCP_MANIFEST_ID = createField(DSL.name("module_bccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key.");

    /**
     * The column <code>oagi.module_bccp_manifest.module_set_release_id</code>.
     * A foreign key of the module set release record.
     */
    public final TableField<ModuleBccpManifestRecord, ULong> MODULE_SET_RELEASE_ID = createField(DSL.name("module_set_release_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key of the module set release record.");

    /**
     * The column <code>oagi.module_bccp_manifest.bccp_manifest_id</code>. A
     * foreign key of the bccp manifest record.
     */
    public final TableField<ModuleBccpManifestRecord, ULong> BCCP_MANIFEST_ID = createField(DSL.name("bccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key of the bccp manifest record.");

    /**
     * The column <code>oagi.module_bccp_manifest.module_id</code>. This
     * indicates a module.
     */
    public final TableField<ModuleBccpManifestRecord, ULong> MODULE_ID = createField(DSL.name("module_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "This indicates a module.");

    /**
     * The column <code>oagi.module_bccp_manifest.created_by</code>. Foreign key
     * to the APP_USER table. It indicates the user who created this record.
     */
    public final TableField<ModuleBccpManifestRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. It indicates the user who created this record.");

    /**
     * The column <code>oagi.module_bccp_manifest.last_updated_by</code>.
     * Foreign key to the APP_USER table referring to the last user who updated
     * the record.
     */
    public final TableField<ModuleBccpManifestRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table referring to the last user who updated the record.");

    /**
     * The column <code>oagi.module_bccp_manifest.creation_timestamp</code>. The
     * timestamp when the record was first created.
     */
    public final TableField<ModuleBccpManifestRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was first created.");

    /**
     * The column <code>oagi.module_bccp_manifest.last_update_timestamp</code>.
     * The timestamp when the record was last updated.
     */
    public final TableField<ModuleBccpManifestRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was last updated.");

    private ModuleBccpManifest(Name alias, Table<ModuleBccpManifestRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private ModuleBccpManifest(Name alias, Table<ModuleBccpManifestRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.module_bccp_manifest</code> table reference
     */
    public ModuleBccpManifest(String alias) {
        this(DSL.name(alias), MODULE_BCCP_MANIFEST);
    }

    /**
     * Create an aliased <code>oagi.module_bccp_manifest</code> table reference
     */
    public ModuleBccpManifest(Name alias) {
        this(alias, MODULE_BCCP_MANIFEST);
    }

    /**
     * Create a <code>oagi.module_bccp_manifest</code> table reference
     */
    public ModuleBccpManifest() {
        this(DSL.name("module_bccp_manifest"), null);
    }

    public <O extends Record> ModuleBccpManifest(Table<O> path, ForeignKey<O, ModuleBccpManifestRecord> childPath, InverseForeignKey<O, ModuleBccpManifestRecord> parentPath) {
        super(path, childPath, parentPath, MODULE_BCCP_MANIFEST);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ModuleBccpManifestPath extends ModuleBccpManifest implements Path<ModuleBccpManifestRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> ModuleBccpManifestPath(Table<O> path, ForeignKey<O, ModuleBccpManifestRecord> childPath, InverseForeignKey<O, ModuleBccpManifestRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ModuleBccpManifestPath(Name alias, Table<ModuleBccpManifestRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ModuleBccpManifestPath as(String alias) {
            return new ModuleBccpManifestPath(DSL.name(alias), this);
        }

        @Override
        public ModuleBccpManifestPath as(Name alias) {
            return new ModuleBccpManifestPath(alias, this);
        }

        @Override
        public ModuleBccpManifestPath as(Table<?> alias) {
            return new ModuleBccpManifestPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<ModuleBccpManifestRecord, ULong> getIdentity() {
        return (Identity<ModuleBccpManifestRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<ModuleBccpManifestRecord> getPrimaryKey() {
        return Keys.KEY_MODULE_BCCP_MANIFEST_PRIMARY;
    }

    @Override
    public List<ForeignKey<ModuleBccpManifestRecord, ?>> getReferences() {
        return Arrays.asList(Keys.MODULE_BCCP_MANIFEST_BCCP_MANIFEST_ID_FK, Keys.MODULE_BCCP_MANIFEST_CREATED_BY_FK, Keys.MODULE_BCCP_MANIFEST_LAST_UPDATED_BY_FK, Keys.MODULE_BCCP_MANIFEST_MODULE_ID_FK, Keys.MODULE_BCCP_MANIFEST_MODULE_SET_RELEASE_ID_FK);
    }

    private transient BccpManifestPath _bccpManifest;

    /**
     * Get the implicit join path to the <code>oagi.bccp_manifest</code> table.
     */
    public BccpManifestPath bccpManifest() {
        if (_bccpManifest == null)
            _bccpManifest = new BccpManifestPath(this, Keys.MODULE_BCCP_MANIFEST_BCCP_MANIFEST_ID_FK, null);

        return _bccpManifest;
    }

    private transient AppUserPath _moduleBccpManifestCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>module_bccp_manifest_created_by_fk</code> key.
     */
    public AppUserPath moduleBccpManifestCreatedByFk() {
        if (_moduleBccpManifestCreatedByFk == null)
            _moduleBccpManifestCreatedByFk = new AppUserPath(this, Keys.MODULE_BCCP_MANIFEST_CREATED_BY_FK, null);

        return _moduleBccpManifestCreatedByFk;
    }

    private transient AppUserPath _moduleBccpManifestLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>module_bccp_manifest_last_updated_by_fk</code> key.
     */
    public AppUserPath moduleBccpManifestLastUpdatedByFk() {
        if (_moduleBccpManifestLastUpdatedByFk == null)
            _moduleBccpManifestLastUpdatedByFk = new AppUserPath(this, Keys.MODULE_BCCP_MANIFEST_LAST_UPDATED_BY_FK, null);

        return _moduleBccpManifestLastUpdatedByFk;
    }

    private transient ModulePath _module;

    /**
     * Get the implicit join path to the <code>oagi.module</code> table.
     */
    public ModulePath module() {
        if (_module == null)
            _module = new ModulePath(this, Keys.MODULE_BCCP_MANIFEST_MODULE_ID_FK, null);

        return _module;
    }

    private transient ModuleSetReleasePath _moduleSetRelease;

    /**
     * Get the implicit join path to the <code>oagi.module_set_release</code>
     * table.
     */
    public ModuleSetReleasePath moduleSetRelease() {
        if (_moduleSetRelease == null)
            _moduleSetRelease = new ModuleSetReleasePath(this, Keys.MODULE_BCCP_MANIFEST_MODULE_SET_RELEASE_ID_FK, null);

        return _moduleSetRelease;
    }

    @Override
    public ModuleBccpManifest as(String alias) {
        return new ModuleBccpManifest(DSL.name(alias), this);
    }

    @Override
    public ModuleBccpManifest as(Name alias) {
        return new ModuleBccpManifest(alias, this);
    }

    @Override
    public ModuleBccpManifest as(Table<?> alias) {
        return new ModuleBccpManifest(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleBccpManifest rename(String name) {
        return new ModuleBccpManifest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleBccpManifest rename(Name name) {
        return new ModuleBccpManifest(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleBccpManifest rename(Table<?> name) {
        return new ModuleBccpManifest(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ModuleBccpManifest where(Condition condition) {
        return new ModuleBccpManifest(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ModuleBccpManifest where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ModuleBccpManifest where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ModuleBccpManifest where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ModuleBccpManifest where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ModuleBccpManifest where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ModuleBccpManifest where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ModuleBccpManifest where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ModuleBccpManifest whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ModuleBccpManifest whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
