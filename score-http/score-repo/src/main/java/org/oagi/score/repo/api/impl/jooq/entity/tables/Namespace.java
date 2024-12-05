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
import org.oagi.score.repo.api.impl.jooq.entity.tables.Acc.AccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AgencyIdList.AgencyIdListPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Asccp.AsccpPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bccp.BccpPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CodeList.CodeListPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Dt.DtPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Library.LibraryPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Module.ModulePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Release.ReleasePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.NamespaceRecord;


/**
 * This table stores information about a namespace. Namespace is the namespace
 * as in the XML schema specification.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Namespace extends TableImpl<NamespaceRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.namespace</code>
     */
    public static final Namespace NAMESPACE = new Namespace();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NamespaceRecord> getRecordType() {
        return NamespaceRecord.class;
    }

    /**
     * The column <code>oagi.namespace.namespace_id</code>. Primary, internal
     * database key.
     */
    public final TableField<NamespaceRecord, ULong> NAMESPACE_ID = createField(DSL.name("namespace_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary, internal database key.");

    /**
     * The column <code>oagi.namespace.library_id</code>. A foreign key pointed
     * to a library of the current record.
     */
    public final TableField<NamespaceRecord, ULong> LIBRARY_ID = createField(DSL.name("library_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key pointed to a library of the current record.");

    /**
     * The column <code>oagi.namespace.uri</code>. This is the URI of the
     * namespace.
     */
    public final TableField<NamespaceRecord, String> URI = createField(DSL.name("uri"), SQLDataType.VARCHAR(100).nullable(false), this, "This is the URI of the namespace.");

    /**
     * The column <code>oagi.namespace.prefix</code>. This is a default short
     * name to represent the URI. It may be overridden during the expression
     * generation. Null or empty means the same thing like the default prefix in
     * an XML schema.
     */
    public final TableField<NamespaceRecord, String> PREFIX = createField(DSL.name("prefix"), SQLDataType.VARCHAR(45).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "This is a default short name to represent the URI. It may be overridden during the expression generation. Null or empty means the same thing like the default prefix in an XML schema.");

    /**
     * The column <code>oagi.namespace.description</code>. Description or
     * explanation about the namespace or use of the namespace.
     */
    public final TableField<NamespaceRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "Description or explanation about the namespace or use of the namespace.");

    /**
     * The column <code>oagi.namespace.is_std_nmsp</code>. This indicates
     * whether the namespace is reserved for standard used (i.e., whether it is
     * an OAGIS namespace). If it is true, then end users cannot user the
     * namespace for the end user CCs.
     */
    public final TableField<NamespaceRecord, Byte> IS_STD_NMSP = createField(DSL.name("is_std_nmsp"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "This indicates whether the namespace is reserved for standard used (i.e., whether it is an OAGIS namespace). If it is true, then end users cannot user the namespace for the end user CCs.");

    /**
     * The column <code>oagi.namespace.owner_user_id</code>. Foreign key to the
     * APP_USER table identifying the user who can update or delete the record.
     */
    public final TableField<NamespaceRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table identifying the user who can update or delete the record.");

    /**
     * The column <code>oagi.namespace.created_by</code>. Foreign key to the
     * APP_USER table identifying user who created the namespace.
     */
    public final TableField<NamespaceRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table identifying user who created the namespace.");

    /**
     * The column <code>oagi.namespace.last_updated_by</code>. Foreign key to
     * the APP_USER table identifying the user who last updated the record.
     */
    public final TableField<NamespaceRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table identifying the user who last updated the record.");

    /**
     * The column <code>oagi.namespace.creation_timestamp</code>. The timestamp
     * when the record was first created.
     */
    public final TableField<NamespaceRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was first created.");

    /**
     * The column <code>oagi.namespace.last_update_timestamp</code>. The
     * timestamp when the record was last updated.
     */
    public final TableField<NamespaceRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was last updated.");

    private Namespace(Name alias, Table<NamespaceRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Namespace(Name alias, Table<NamespaceRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("This table stores information about a namespace. Namespace is the namespace as in the XML schema specification."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.namespace</code> table reference
     */
    public Namespace(String alias) {
        this(DSL.name(alias), NAMESPACE);
    }

    /**
     * Create an aliased <code>oagi.namespace</code> table reference
     */
    public Namespace(Name alias) {
        this(alias, NAMESPACE);
    }

    /**
     * Create a <code>oagi.namespace</code> table reference
     */
    public Namespace() {
        this(DSL.name("namespace"), null);
    }

    public <O extends Record> Namespace(Table<O> path, ForeignKey<O, NamespaceRecord> childPath, InverseForeignKey<O, NamespaceRecord> parentPath) {
        super(path, childPath, parentPath, NAMESPACE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class NamespacePath extends Namespace implements Path<NamespaceRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> NamespacePath(Table<O> path, ForeignKey<O, NamespaceRecord> childPath, InverseForeignKey<O, NamespaceRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private NamespacePath(Name alias, Table<NamespaceRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public NamespacePath as(String alias) {
            return new NamespacePath(DSL.name(alias), this);
        }

        @Override
        public NamespacePath as(Name alias) {
            return new NamespacePath(alias, this);
        }

        @Override
        public NamespacePath as(Table<?> alias) {
            return new NamespacePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<NamespaceRecord, ULong> getIdentity() {
        return (Identity<NamespaceRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<NamespaceRecord> getPrimaryKey() {
        return Keys.KEY_NAMESPACE_PRIMARY;
    }

    @Override
    public List<UniqueKey<NamespaceRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_NAMESPACE_NAMESPACE_UK1);
    }

    @Override
    public List<ForeignKey<NamespaceRecord, ?>> getReferences() {
        return Arrays.asList(Keys.NAMESPACE_CREATED_BY_FK, Keys.NAMESPACE_LAST_UPDATED_BY_FK, Keys.NAMESPACE_LIBRARY_ID_FK, Keys.NAMESPACE_OWNER_USER_ID_FK);
    }

    private transient AppUserPath _namespaceCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>namespace_created_by_fk</code> key.
     */
    public AppUserPath namespaceCreatedByFk() {
        if (_namespaceCreatedByFk == null)
            _namespaceCreatedByFk = new AppUserPath(this, Keys.NAMESPACE_CREATED_BY_FK, null);

        return _namespaceCreatedByFk;
    }

    private transient AppUserPath _namespaceLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>namespace_last_updated_by_fk</code> key.
     */
    public AppUserPath namespaceLastUpdatedByFk() {
        if (_namespaceLastUpdatedByFk == null)
            _namespaceLastUpdatedByFk = new AppUserPath(this, Keys.NAMESPACE_LAST_UPDATED_BY_FK, null);

        return _namespaceLastUpdatedByFk;
    }

    private transient LibraryPath _library;

    /**
     * Get the implicit join path to the <code>oagi.library</code> table.
     */
    public LibraryPath library() {
        if (_library == null)
            _library = new LibraryPath(this, Keys.NAMESPACE_LIBRARY_ID_FK, null);

        return _library;
    }

    private transient AppUserPath _namespaceOwnerUserIdFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>namespace_owner_user_id_fk</code> key.
     */
    public AppUserPath namespaceOwnerUserIdFk() {
        if (_namespaceOwnerUserIdFk == null)
            _namespaceOwnerUserIdFk = new AppUserPath(this, Keys.NAMESPACE_OWNER_USER_ID_FK, null);

        return _namespaceOwnerUserIdFk;
    }

    private transient AccPath _acc;

    /**
     * Get the implicit to-many join path to the <code>oagi.acc</code> table
     */
    public AccPath acc() {
        if (_acc == null)
            _acc = new AccPath(this, null, Keys.ACC_NAMESPACE_ID_FK.getInverseKey());

        return _acc;
    }

    private transient AgencyIdListPath _agencyIdList;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.agency_id_list</code> table
     */
    public AgencyIdListPath agencyIdList() {
        if (_agencyIdList == null)
            _agencyIdList = new AgencyIdListPath(this, null, Keys.AGENCY_ID_LIST_NAMESPACE_ID_FK.getInverseKey());

        return _agencyIdList;
    }

    private transient AsccpPath _asccp;

    /**
     * Get the implicit to-many join path to the <code>oagi.asccp</code> table
     */
    public AsccpPath asccp() {
        if (_asccp == null)
            _asccp = new AsccpPath(this, null, Keys.ASCCP_NAMESPACE_ID_FK.getInverseKey());

        return _asccp;
    }

    private transient BccpPath _bccp;

    /**
     * Get the implicit to-many join path to the <code>oagi.bccp</code> table
     */
    public BccpPath bccp() {
        if (_bccp == null)
            _bccp = new BccpPath(this, null, Keys.BCCP_NAMESPACE_ID_FK.getInverseKey());

        return _bccp;
    }

    private transient CodeListPath _codeList;

    /**
     * Get the implicit to-many join path to the <code>oagi.code_list</code>
     * table
     */
    public CodeListPath codeList() {
        if (_codeList == null)
            _codeList = new CodeListPath(this, null, Keys.CODE_LIST_NAMESPACE_ID_FK.getInverseKey());

        return _codeList;
    }

    private transient DtPath _dt;

    /**
     * Get the implicit to-many join path to the <code>oagi.dt</code> table
     */
    public DtPath dt() {
        if (_dt == null)
            _dt = new DtPath(this, null, Keys.DT_NAMESPACE_ID_FK.getInverseKey());

        return _dt;
    }

    private transient ModulePath _module;

    /**
     * Get the implicit to-many join path to the <code>oagi.module</code> table
     */
    public ModulePath module() {
        if (_module == null)
            _module = new ModulePath(this, null, Keys.MODULE_NAMESPACE_ID_FK.getInverseKey());

        return _module;
    }

    private transient ReleasePath _release;

    /**
     * Get the implicit to-many join path to the <code>oagi.release</code> table
     */
    public ReleasePath release() {
        if (_release == null)
            _release = new ReleasePath(this, null, Keys.RELEASE_NAMESPACE_ID_FK.getInverseKey());

        return _release;
    }

    @Override
    public Namespace as(String alias) {
        return new Namespace(DSL.name(alias), this);
    }

    @Override
    public Namespace as(Name alias) {
        return new Namespace(alias, this);
    }

    @Override
    public Namespace as(Table<?> alias) {
        return new Namespace(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Namespace rename(String name) {
        return new Namespace(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Namespace rename(Name name) {
        return new Namespace(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Namespace rename(Table<?> name) {
        return new Namespace(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Namespace where(Condition condition) {
        return new Namespace(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Namespace where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Namespace where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Namespace where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Namespace where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Namespace where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Namespace where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Namespace where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Namespace whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Namespace whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
