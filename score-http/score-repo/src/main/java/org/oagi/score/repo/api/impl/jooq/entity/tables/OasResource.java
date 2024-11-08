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
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasDoc.OasDocPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasOperation.OasOperationPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.OasResourceRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasResource extends TableImpl<OasResourceRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.oas_resource</code>
     */
    public static final OasResource OAS_RESOURCE = new OasResource();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OasResourceRecord> getRecordType() {
        return OasResourceRecord.class;
    }

    /**
     * The column <code>oagi.oas_resource.oas_resource_id</code>. The primary
     * key of the record.
     */
    public final TableField<OasResourceRecord, ULong> OAS_RESOURCE_ID = createField(DSL.name("oas_resource_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_resource.oas_doc_id</code>. A reference of the
     * doc record.
     */
    public final TableField<OasResourceRecord, ULong> OAS_DOC_ID = createField(DSL.name("oas_doc_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "A reference of the doc record.");

    /**
     * The column <code>oagi.oas_resource.path</code>. This will hold the BIE
     * name by default.
     */
    public final TableField<OasResourceRecord, String> PATH = createField(DSL.name("path"), SQLDataType.CLOB.nullable(false), this, "This will hold the BIE name by default.");

    /**
     * The column <code>oagi.oas_resource.ref</code>.
     */
    public final TableField<OasResourceRecord, String> REF = createField(DSL.name("ref"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "");

    /**
     * The column <code>oagi.oas_resource.created_by</code>. The user who
     * creates the record.
     */
    public final TableField<OasResourceRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who creates the record.");

    /**
     * The column <code>oagi.oas_resource.last_updated_by</code>. The user who
     * last updates the record.
     */
    public final TableField<OasResourceRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who last updates the record.");

    /**
     * The column <code>oagi.oas_resource.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public final TableField<OasResourceRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is created.");

    /**
     * The column <code>oagi.oas_resource.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public final TableField<OasResourceRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is last updated.");

    private OasResource(Name alias, Table<OasResourceRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private OasResource(Name alias, Table<OasResourceRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.oas_resource</code> table reference
     */
    public OasResource(String alias) {
        this(DSL.name(alias), OAS_RESOURCE);
    }

    /**
     * Create an aliased <code>oagi.oas_resource</code> table reference
     */
    public OasResource(Name alias) {
        this(alias, OAS_RESOURCE);
    }

    /**
     * Create a <code>oagi.oas_resource</code> table reference
     */
    public OasResource() {
        this(DSL.name("oas_resource"), null);
    }

    public <O extends Record> OasResource(Table<O> path, ForeignKey<O, OasResourceRecord> childPath, InverseForeignKey<O, OasResourceRecord> parentPath) {
        super(path, childPath, parentPath, OAS_RESOURCE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class OasResourcePath extends OasResource implements Path<OasResourceRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> OasResourcePath(Table<O> path, ForeignKey<O, OasResourceRecord> childPath, InverseForeignKey<O, OasResourceRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private OasResourcePath(Name alias, Table<OasResourceRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public OasResourcePath as(String alias) {
            return new OasResourcePath(DSL.name(alias), this);
        }

        @Override
        public OasResourcePath as(Name alias) {
            return new OasResourcePath(alias, this);
        }

        @Override
        public OasResourcePath as(Table<?> alias) {
            return new OasResourcePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<OasResourceRecord, ULong> getIdentity() {
        return (Identity<OasResourceRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<OasResourceRecord> getPrimaryKey() {
        return Keys.KEY_OAS_RESOURCE_PRIMARY;
    }

    @Override
    public List<ForeignKey<OasResourceRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OAS_RESOURCE_CREATED_BY_FK, Keys.OAS_RESOURCE_LAST_UPDATED_BY_FK, Keys.OAS_RESOURCE_OAS_DOC_ID_FK);
    }

    private transient AppUserPath _oasResourceCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_resource_created_by_fk</code> key.
     */
    public AppUserPath oasResourceCreatedByFk() {
        if (_oasResourceCreatedByFk == null)
            _oasResourceCreatedByFk = new AppUserPath(this, Keys.OAS_RESOURCE_CREATED_BY_FK, null);

        return _oasResourceCreatedByFk;
    }

    private transient AppUserPath _oasResourceLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_resource_last_updated_by_fk</code> key.
     */
    public AppUserPath oasResourceLastUpdatedByFk() {
        if (_oasResourceLastUpdatedByFk == null)
            _oasResourceLastUpdatedByFk = new AppUserPath(this, Keys.OAS_RESOURCE_LAST_UPDATED_BY_FK, null);

        return _oasResourceLastUpdatedByFk;
    }

    private transient OasDocPath _oasDoc;

    /**
     * Get the implicit join path to the <code>oagi.oas_doc</code> table.
     */
    public OasDocPath oasDoc() {
        if (_oasDoc == null)
            _oasDoc = new OasDocPath(this, Keys.OAS_RESOURCE_OAS_DOC_ID_FK, null);

        return _oasDoc;
    }

    private transient OasOperationPath _oasOperation;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_operation</code>
     * table
     */
    public OasOperationPath oasOperation() {
        if (_oasOperation == null)
            _oasOperation = new OasOperationPath(this, null, Keys.OAS_OPERATION_OAS_RESOURCE_ID_FK.getInverseKey());

        return _oasOperation;
    }

    @Override
    public OasResource as(String alias) {
        return new OasResource(DSL.name(alias), this);
    }

    @Override
    public OasResource as(Name alias) {
        return new OasResource(alias, this);
    }

    @Override
    public OasResource as(Table<?> alias) {
        return new OasResource(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OasResource rename(String name) {
        return new OasResource(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasResource rename(Name name) {
        return new OasResource(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasResource rename(Table<?> name) {
        return new OasResource(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasResource where(Condition condition) {
        return new OasResource(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasResource where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasResource where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasResource where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasResource where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasResource where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasResource where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasResource where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasResource whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasResource whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
