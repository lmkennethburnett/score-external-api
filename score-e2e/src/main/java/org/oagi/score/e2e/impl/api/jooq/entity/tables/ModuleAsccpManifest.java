/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.ModuleAsccpManifestRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ModuleAsccpManifest extends TableImpl<ModuleAsccpManifestRecord> {

    /**
     * The reference instance of <code>oagi.module_asccp_manifest</code>
     */
    public static final ModuleAsccpManifest MODULE_ASCCP_MANIFEST = new ModuleAsccpManifest();
    private static final long serialVersionUID = 1L;
    /**
     * The column
     * <code>oagi.module_asccp_manifest.module_asccp_manifest_id</code>. Primary
     * key.
     */
    public final TableField<ModuleAsccpManifestRecord, ULong> MODULE_ASCCP_MANIFEST_ID = createField(DSL.name("module_asccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key.");
    /**
     * The column <code>oagi.module_asccp_manifest.module_set_release_id</code>.
     * A foreign key of the module set release record.
     */
    public final TableField<ModuleAsccpManifestRecord, ULong> MODULE_SET_RELEASE_ID = createField(DSL.name("module_set_release_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key of the module set release record.");
    /**
     * The column <code>oagi.module_asccp_manifest.asccp_manifest_id</code>. A
     * foreign key of the asccp manifest record.
     */
    public final TableField<ModuleAsccpManifestRecord, ULong> ASCCP_MANIFEST_ID = createField(DSL.name("asccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key of the asccp manifest record.");
    /**
     * The column <code>oagi.module_asccp_manifest.module_id</code>. This
     * indicates a module.
     */
    public final TableField<ModuleAsccpManifestRecord, ULong> MODULE_ID = createField(DSL.name("module_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "This indicates a module.");
    /**
     * The column <code>oagi.module_asccp_manifest.created_by</code>. Foreign
     * key to the APP_USER table. It indicates the user who created this record.
     */
    public final TableField<ModuleAsccpManifestRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. It indicates the user who created this record.");
    /**
     * The column <code>oagi.module_asccp_manifest.last_updated_by</code>.
     * Foreign key to the APP_USER table referring to the last user who updated
     * the record.
     */
    public final TableField<ModuleAsccpManifestRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table referring to the last user who updated the record.");
    /**
     * The column <code>oagi.module_asccp_manifest.creation_timestamp</code>.
     * The timestamp when the record was first created.
     */
    public final TableField<ModuleAsccpManifestRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was first created.");
    /**
     * The column <code>oagi.module_asccp_manifest.last_update_timestamp</code>.
     * The timestamp when the record was last updated.
     */
    public final TableField<ModuleAsccpManifestRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was last updated.");
    private transient ModuleSetRelease _moduleSetRelease;
    private transient AsccpManifest _asccpManifest;
    private transient Module _module;
    private transient AppUser _moduleAsccpManifestCreatedByFk;
    private transient AppUser _moduleAsccpManifestLastUpdatedByFk;

    private ModuleAsccpManifest(Name alias, Table<ModuleAsccpManifestRecord> aliased) {
        this(alias, aliased, null);
    }

    private ModuleAsccpManifest(Name alias, Table<ModuleAsccpManifestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.module_asccp_manifest</code> table reference
     */
    public ModuleAsccpManifest(String alias) {
        this(DSL.name(alias), MODULE_ASCCP_MANIFEST);
    }

    /**
     * Create an aliased <code>oagi.module_asccp_manifest</code> table reference
     */
    public ModuleAsccpManifest(Name alias) {
        this(alias, MODULE_ASCCP_MANIFEST);
    }

    /**
     * Create a <code>oagi.module_asccp_manifest</code> table reference
     */
    public ModuleAsccpManifest() {
        this(DSL.name("module_asccp_manifest"), null);
    }

    public <O extends Record> ModuleAsccpManifest(Table<O> child, ForeignKey<O, ModuleAsccpManifestRecord> key) {
        super(child, key, MODULE_ASCCP_MANIFEST);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ModuleAsccpManifestRecord> getRecordType() {
        return ModuleAsccpManifestRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<ModuleAsccpManifestRecord, ULong> getIdentity() {
        return (Identity<ModuleAsccpManifestRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<ModuleAsccpManifestRecord> getPrimaryKey() {
        return Keys.KEY_MODULE_ASCCP_MANIFEST_PRIMARY;
    }

    @Override
    public List<ForeignKey<ModuleAsccpManifestRecord, ?>> getReferences() {
        return Arrays.asList(Keys.MODULE_ASCCP_MANIFEST_MODULE_SET_RELEASE_ID_FK, Keys.MODULE_ASCCP_MANIFEST_ASCCP_MANIFEST_ID_FK, Keys.MODULE_ASCCP_MANIFEST_MODULE_ID_FK, Keys.MODULE_ASCCP_MANIFEST_CREATED_BY_FK, Keys.MODULE_ASCCP_MANIFEST_LAST_UPDATED_BY_FK);
    }

    /**
     * Get the implicit join path to the <code>oagi.module_set_release</code>
     * table.
     */
    public ModuleSetRelease moduleSetRelease() {
        if (_moduleSetRelease == null)
            _moduleSetRelease = new ModuleSetRelease(this, Keys.MODULE_ASCCP_MANIFEST_MODULE_SET_RELEASE_ID_FK);

        return _moduleSetRelease;
    }

    /**
     * Get the implicit join path to the <code>oagi.asccp_manifest</code> table.
     */
    public AsccpManifest asccpManifest() {
        if (_asccpManifest == null)
            _asccpManifest = new AsccpManifest(this, Keys.MODULE_ASCCP_MANIFEST_ASCCP_MANIFEST_ID_FK);

        return _asccpManifest;
    }

    /**
     * Get the implicit join path to the <code>oagi.module</code> table.
     */
    public Module module() {
        if (_module == null)
            _module = new Module(this, Keys.MODULE_ASCCP_MANIFEST_MODULE_ID_FK);

        return _module;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>module_asccp_manifest_created_by_fk</code> key.
     */
    public AppUser moduleAsccpManifestCreatedByFk() {
        if (_moduleAsccpManifestCreatedByFk == null)
            _moduleAsccpManifestCreatedByFk = new AppUser(this, Keys.MODULE_ASCCP_MANIFEST_CREATED_BY_FK);

        return _moduleAsccpManifestCreatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>module_asccp_manifest_last_updated_by_fk</code> key.
     */
    public AppUser moduleAsccpManifestLastUpdatedByFk() {
        if (_moduleAsccpManifestLastUpdatedByFk == null)
            _moduleAsccpManifestLastUpdatedByFk = new AppUser(this, Keys.MODULE_ASCCP_MANIFEST_LAST_UPDATED_BY_FK);

        return _moduleAsccpManifestLastUpdatedByFk;
    }

    @Override
    public ModuleAsccpManifest as(String alias) {
        return new ModuleAsccpManifest(DSL.name(alias), this);
    }

    @Override
    public ModuleAsccpManifest as(Name alias) {
        return new ModuleAsccpManifest(alias, this);
    }

    @Override
    public ModuleAsccpManifest as(Table<?> alias) {
        return new ModuleAsccpManifest(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleAsccpManifest rename(String name) {
        return new ModuleAsccpManifest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleAsccpManifest rename(Name name) {
        return new ModuleAsccpManifest(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleAsccpManifest rename(Table<?> name) {
        return new ModuleAsccpManifest(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<ULong, ULong, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
