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
import org.oagi.score.repo.api.impl.jooq.entity.tables.BiePackage.BiePackagePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.ModuleSet.ModuleSetPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Namespace.NamespacePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Release.ReleasePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.LibraryRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Library extends TableImpl<LibraryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.library</code>
     */
    public static final Library LIBRARY = new Library();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LibraryRecord> getRecordType() {
        return LibraryRecord.class;
    }

    /**
     * The column <code>oagi.library.library_id</code>. Internal, primary
     * database key.
     */
    public final TableField<LibraryRecord, ULong> LIBRARY_ID = createField(DSL.name("library_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Internal, primary database key.");

    /**
     * The column <code>oagi.library.name</code>. A library name.
     */
    public final TableField<LibraryRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "A library name.");

    /**
     * The column <code>oagi.library.organization</code>. The name of the
     * organization responsible for maintaining or managing the library.
     */
    public final TableField<LibraryRecord, String> ORGANIZATION = createField(DSL.name("organization"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "The name of the organization responsible for maintaining or managing the library.");

    /**
     * The column <code>oagi.library.description</code>. A brief summary or
     * overview of the library's purpose and functionality.
     */
    public final TableField<LibraryRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "A brief summary or overview of the library's purpose and functionality.");

    /**
     * The column <code>oagi.library.link</code>. A URL directing to the
     * library's homepage, documentation, or repository for further details.
     */
    public final TableField<LibraryRecord, String> LINK = createField(DSL.name("link"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "A URL directing to the library's homepage, documentation, or repository for further details.");

    /**
     * The column <code>oagi.library.domain</code>. Specifies the area of focus
     * or application domain of the library (e.g., agriculture, finance, or
     * aerospace).
     */
    public final TableField<LibraryRecord, String> DOMAIN = createField(DSL.name("domain"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "Specifies the area of focus or application domain of the library (e.g., agriculture, finance, or aerospace).");

    /**
     * The column <code>oagi.library.state</code>. Indicates the current status
     * of the library.
     */
    public final TableField<LibraryRecord, String> STATE = createField(DSL.name("state"), SQLDataType.VARCHAR(20).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "Indicates the current status of the library.");

    /**
     * The column <code>oagi.library.created_by</code>. Foreign key to the
     * APP_USER table referring to the user who creates the record.
     */
    public final TableField<LibraryRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table referring to the user who creates the record.");

    /**
     * The column <code>oagi.library.last_updated_by</code>. Foreign key to the
     * APP_USER table referring to the last user who updated the record.
     */
    public final TableField<LibraryRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table referring to the last user who updated the record.");

    /**
     * The column <code>oagi.library.creation_timestamp</code>. Timestamp when
     * the record was created.
     */
    public final TableField<LibraryRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the record was created.");

    /**
     * The column <code>oagi.library.last_update_timestamp</code>. Timestamp
     * when the record was last updated.
     */
    public final TableField<LibraryRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the record was last updated.");

    private Library(Name alias, Table<LibraryRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Library(Name alias, Table<LibraryRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.library</code> table reference
     */
    public Library(String alias) {
        this(DSL.name(alias), LIBRARY);
    }

    /**
     * Create an aliased <code>oagi.library</code> table reference
     */
    public Library(Name alias) {
        this(alias, LIBRARY);
    }

    /**
     * Create a <code>oagi.library</code> table reference
     */
    public Library() {
        this(DSL.name("library"), null);
    }

    public <O extends Record> Library(Table<O> path, ForeignKey<O, LibraryRecord> childPath, InverseForeignKey<O, LibraryRecord> parentPath) {
        super(path, childPath, parentPath, LIBRARY);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class LibraryPath extends Library implements Path<LibraryRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> LibraryPath(Table<O> path, ForeignKey<O, LibraryRecord> childPath, InverseForeignKey<O, LibraryRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private LibraryPath(Name alias, Table<LibraryRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public LibraryPath as(String alias) {
            return new LibraryPath(DSL.name(alias), this);
        }

        @Override
        public LibraryPath as(Name alias) {
            return new LibraryPath(alias, this);
        }

        @Override
        public LibraryPath as(Table<?> alias) {
            return new LibraryPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<LibraryRecord, ULong> getIdentity() {
        return (Identity<LibraryRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<LibraryRecord> getPrimaryKey() {
        return Keys.KEY_LIBRARY_PRIMARY;
    }

    @Override
    public List<ForeignKey<LibraryRecord, ?>> getReferences() {
        return Arrays.asList(Keys.LIBRARY_CREATED_BY_FK, Keys.LIBRARY_LAST_UPDATED_BY_FK);
    }

    private transient AppUserPath _libraryCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>library_created_by_fk</code> key.
     */
    public AppUserPath libraryCreatedByFk() {
        if (_libraryCreatedByFk == null)
            _libraryCreatedByFk = new AppUserPath(this, Keys.LIBRARY_CREATED_BY_FK, null);

        return _libraryCreatedByFk;
    }

    private transient AppUserPath _libraryLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>library_last_updated_by_fk</code> key.
     */
    public AppUserPath libraryLastUpdatedByFk() {
        if (_libraryLastUpdatedByFk == null)
            _libraryLastUpdatedByFk = new AppUserPath(this, Keys.LIBRARY_LAST_UPDATED_BY_FK, null);

        return _libraryLastUpdatedByFk;
    }

    private transient BiePackagePath _biePackage;

    /**
     * Get the implicit to-many join path to the <code>oagi.bie_package</code>
     * table
     */
    public BiePackagePath biePackage() {
        if (_biePackage == null)
            _biePackage = new BiePackagePath(this, null, Keys.BIE_PACKAGE_LIBRARY_ID_FK.getInverseKey());

        return _biePackage;
    }

    private transient ModuleSetPath _moduleSet;

    /**
     * Get the implicit to-many join path to the <code>oagi.module_set</code>
     * table
     */
    public ModuleSetPath moduleSet() {
        if (_moduleSet == null)
            _moduleSet = new ModuleSetPath(this, null, Keys.MODULE_SET_LIBRARY_ID_FK.getInverseKey());

        return _moduleSet;
    }

    private transient NamespacePath _namespace;

    /**
     * Get the implicit to-many join path to the <code>oagi.namespace</code>
     * table
     */
    public NamespacePath namespace() {
        if (_namespace == null)
            _namespace = new NamespacePath(this, null, Keys.NAMESPACE_LIBRARY_ID_FK.getInverseKey());

        return _namespace;
    }

    private transient ReleasePath _release;

    /**
     * Get the implicit to-many join path to the <code>oagi.release</code> table
     */
    public ReleasePath release() {
        if (_release == null)
            _release = new ReleasePath(this, null, Keys.RELEASE_LIBRARY_ID_FK.getInverseKey());

        return _release;
    }

    @Override
    public Library as(String alias) {
        return new Library(DSL.name(alias), this);
    }

    @Override
    public Library as(Name alias) {
        return new Library(alias, this);
    }

    @Override
    public Library as(Table<?> alias) {
        return new Library(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Library rename(String name) {
        return new Library(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Library rename(Name name) {
        return new Library(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Library rename(Table<?> name) {
        return new Library(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Library where(Condition condition) {
        return new Library(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Library where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Library where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Library where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Library where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Library where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Library where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Library where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Library whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Library whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
