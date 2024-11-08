/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


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
import org.oagi.score.repo.api.impl.jooq.entity.tables.BizCtx.BizCtxPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Tenant.TenantPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.TenantBusinessCtxRecord;


/**
 * This table captures the tenant role and theirs business contexts.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TenantBusinessCtx extends TableImpl<TenantBusinessCtxRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.tenant_business_ctx</code>
     */
    public static final TenantBusinessCtx TENANT_BUSINESS_CTX = new TenantBusinessCtx();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TenantBusinessCtxRecord> getRecordType() {
        return TenantBusinessCtxRecord.class;
    }

    /**
     * The column <code>oagi.tenant_business_ctx.tenant_business_ctx_id</code>.
     * Primary key column.
     */
    public final TableField<TenantBusinessCtxRecord, ULong> TENANT_BUSINESS_CTX_ID = createField(DSL.name("tenant_business_ctx_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key column.");

    /**
     * The column <code>oagi.tenant_business_ctx.tenant_id</code>. Tenant role.
     */
    public final TableField<TenantBusinessCtxRecord, ULong> TENANT_ID = createField(DSL.name("tenant_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Tenant role.");

    /**
     * The column <code>oagi.tenant_business_ctx.biz_ctx_id</code>. Concrete
     * business context for the company.
     */
    public final TableField<TenantBusinessCtxRecord, ULong> BIZ_CTX_ID = createField(DSL.name("biz_ctx_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Concrete business context for the company.");

    private TenantBusinessCtx(Name alias, Table<TenantBusinessCtxRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TenantBusinessCtx(Name alias, Table<TenantBusinessCtxRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("This table captures the tenant role and theirs business contexts."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.tenant_business_ctx</code> table reference
     */
    public TenantBusinessCtx(String alias) {
        this(DSL.name(alias), TENANT_BUSINESS_CTX);
    }

    /**
     * Create an aliased <code>oagi.tenant_business_ctx</code> table reference
     */
    public TenantBusinessCtx(Name alias) {
        this(alias, TENANT_BUSINESS_CTX);
    }

    /**
     * Create a <code>oagi.tenant_business_ctx</code> table reference
     */
    public TenantBusinessCtx() {
        this(DSL.name("tenant_business_ctx"), null);
    }

    public <O extends Record> TenantBusinessCtx(Table<O> path, ForeignKey<O, TenantBusinessCtxRecord> childPath, InverseForeignKey<O, TenantBusinessCtxRecord> parentPath) {
        super(path, childPath, parentPath, TENANT_BUSINESS_CTX);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TenantBusinessCtxPath extends TenantBusinessCtx implements Path<TenantBusinessCtxRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TenantBusinessCtxPath(Table<O> path, ForeignKey<O, TenantBusinessCtxRecord> childPath, InverseForeignKey<O, TenantBusinessCtxRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TenantBusinessCtxPath(Name alias, Table<TenantBusinessCtxRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TenantBusinessCtxPath as(String alias) {
            return new TenantBusinessCtxPath(DSL.name(alias), this);
        }

        @Override
        public TenantBusinessCtxPath as(Name alias) {
            return new TenantBusinessCtxPath(alias, this);
        }

        @Override
        public TenantBusinessCtxPath as(Table<?> alias) {
            return new TenantBusinessCtxPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<TenantBusinessCtxRecord, ULong> getIdentity() {
        return (Identity<TenantBusinessCtxRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<TenantBusinessCtxRecord> getPrimaryKey() {
        return Keys.KEY_TENANT_BUSINESS_CTX_PRIMARY;
    }

    @Override
    public List<UniqueKey<TenantBusinessCtxRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_TENANT_BUSINESS_CTX_TENANT_BUSINESS_CTX_PAIR);
    }

    @Override
    public List<ForeignKey<TenantBusinessCtxRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ORGANIZATION_BUSINESS_CTX_BIZ_CTX_ID_FK, Keys.TENANT_BUSINESS_CTX_TENANT_ID_FK);
    }

    private transient BizCtxPath _bizCtx;

    /**
     * Get the implicit join path to the <code>oagi.biz_ctx</code> table.
     */
    public BizCtxPath bizCtx() {
        if (_bizCtx == null)
            _bizCtx = new BizCtxPath(this, Keys.ORGANIZATION_BUSINESS_CTX_BIZ_CTX_ID_FK, null);

        return _bizCtx;
    }

    private transient TenantPath _tenant;

    /**
     * Get the implicit join path to the <code>oagi.tenant</code> table.
     */
    public TenantPath tenant() {
        if (_tenant == null)
            _tenant = new TenantPath(this, Keys.TENANT_BUSINESS_CTX_TENANT_ID_FK, null);

        return _tenant;
    }

    @Override
    public TenantBusinessCtx as(String alias) {
        return new TenantBusinessCtx(DSL.name(alias), this);
    }

    @Override
    public TenantBusinessCtx as(Name alias) {
        return new TenantBusinessCtx(alias, this);
    }

    @Override
    public TenantBusinessCtx as(Table<?> alias) {
        return new TenantBusinessCtx(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TenantBusinessCtx rename(String name) {
        return new TenantBusinessCtx(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TenantBusinessCtx rename(Name name) {
        return new TenantBusinessCtx(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TenantBusinessCtx rename(Table<?> name) {
        return new TenantBusinessCtx(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TenantBusinessCtx where(Condition condition) {
        return new TenantBusinessCtx(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TenantBusinessCtx where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TenantBusinessCtx where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TenantBusinessCtx where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TenantBusinessCtx where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TenantBusinessCtx where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TenantBusinessCtx where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TenantBusinessCtx where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TenantBusinessCtx whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TenantBusinessCtx whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
