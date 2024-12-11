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
import org.oagi.score.e2e.impl.api.jooq.entity.tables.BbieBizterm.BbieBiztermPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.Bcc.BccPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.BusinessTerm.BusinessTermPath;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.BccBiztermRecord;


/**
 * The bcc_bizterm table stores information about the aggregation between the
 * business term and BCC. TODO: Placeholder, definition is missing.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BccBizterm extends TableImpl<BccBiztermRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.bcc_bizterm</code>
     */
    public static final BccBizterm BCC_BIZTERM = new BccBizterm();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BccBiztermRecord> getRecordType() {
        return BccBiztermRecord.class;
    }

    /**
     * The column <code>oagi.bcc_bizterm.bcc_bizterm_id</code>. An internal,
     * primary database key of an bcc_bizterm record.
     */
    public final TableField<BccBiztermRecord, ULong> BCC_BIZTERM_ID = createField(DSL.name("bcc_bizterm_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "An internal, primary database key of an bcc_bizterm record.");

    /**
     * The column <code>oagi.bcc_bizterm.business_term_id</code>. An internal ID
     * of the associated business term
     */
    public final TableField<BccBiztermRecord, ULong> BUSINESS_TERM_ID = createField(DSL.name("business_term_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "An internal ID of the associated business term");

    /**
     * The column <code>oagi.bcc_bizterm.bcc_id</code>. An internal ID of the
     * associated BCC
     */
    public final TableField<BccBiztermRecord, ULong> BCC_ID = createField(DSL.name("bcc_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "An internal ID of the associated BCC");

    /**
     * The column <code>oagi.bcc_bizterm.created_by</code>. A foreign key
     * referring to the user who creates the bcc_bizterm record. The creator of
     * the bcc_bizterm is also its owner by default.
     */
    public final TableField<BccBiztermRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key referring to the user who creates the bcc_bizterm record. The creator of the bcc_bizterm is also its owner by default.");

    /**
     * The column <code>oagi.bcc_bizterm.last_updated_by</code>. A foreign key
     * referring to the last user who has updated the bcc_bizterm record. This
     * may be the user who is in the same group as the creator.
     */
    public final TableField<BccBiztermRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key referring to the last user who has updated the bcc_bizterm record. This may be the user who is in the same group as the creator.");

    /**
     * The column <code>oagi.bcc_bizterm.creation_timestamp</code>. Timestamp
     * when the bcc_bizterm record was first created.
     */
    public final TableField<BccBiztermRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the bcc_bizterm record was first created.");

    /**
     * The column <code>oagi.bcc_bizterm.last_update_timestamp</code>. The
     * timestamp when the bcc_bizterm was last updated.
     */
    public final TableField<BccBiztermRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the bcc_bizterm was last updated.");

    private BccBizterm(Name alias, Table<BccBiztermRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private BccBizterm(Name alias, Table<BccBiztermRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("The bcc_bizterm table stores information about the aggregation between the business term and BCC. TODO: Placeholder, definition is missing."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.bcc_bizterm</code> table reference
     */
    public BccBizterm(String alias) {
        this(DSL.name(alias), BCC_BIZTERM);
    }

    /**
     * Create an aliased <code>oagi.bcc_bizterm</code> table reference
     */
    public BccBizterm(Name alias) {
        this(alias, BCC_BIZTERM);
    }

    /**
     * Create a <code>oagi.bcc_bizterm</code> table reference
     */
    public BccBizterm() {
        this(DSL.name("bcc_bizterm"), null);
    }

    public <O extends Record> BccBizterm(Table<O> path, ForeignKey<O, BccBiztermRecord> childPath, InverseForeignKey<O, BccBiztermRecord> parentPath) {
        super(path, childPath, parentPath, BCC_BIZTERM);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BccBiztermPath extends BccBizterm implements Path<BccBiztermRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> BccBiztermPath(Table<O> path, ForeignKey<O, BccBiztermRecord> childPath, InverseForeignKey<O, BccBiztermRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BccBiztermPath(Name alias, Table<BccBiztermRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BccBiztermPath as(String alias) {
            return new BccBiztermPath(DSL.name(alias), this);
        }

        @Override
        public BccBiztermPath as(Name alias) {
            return new BccBiztermPath(alias, this);
        }

        @Override
        public BccBiztermPath as(Table<?> alias) {
            return new BccBiztermPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<BccBiztermRecord, ULong> getIdentity() {
        return (Identity<BccBiztermRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<BccBiztermRecord> getPrimaryKey() {
        return Keys.KEY_BCC_BIZTERM_PRIMARY;
    }

    @Override
    public List<ForeignKey<BccBiztermRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BCC_BIZTERM_BCC_FK, Keys.BCC_BIZTERM_BUSINESS_TERM_FK);
    }

    private transient BccPath _bcc;

    /**
     * Get the implicit join path to the <code>oagi.bcc</code> table.
     */
    public BccPath bcc() {
        if (_bcc == null)
            _bcc = new BccPath(this, Keys.BCC_BIZTERM_BCC_FK, null);

        return _bcc;
    }

    private transient BusinessTermPath _businessTerm;

    /**
     * Get the implicit join path to the <code>oagi.business_term</code> table.
     */
    public BusinessTermPath businessTerm() {
        if (_businessTerm == null)
            _businessTerm = new BusinessTermPath(this, Keys.BCC_BIZTERM_BUSINESS_TERM_FK, null);

        return _businessTerm;
    }

    private transient BbieBiztermPath _bbieBizterm;

    /**
     * Get the implicit to-many join path to the <code>oagi.bbie_bizterm</code>
     * table
     */
    public BbieBiztermPath bbieBizterm() {
        if (_bbieBizterm == null)
            _bbieBizterm = new BbieBiztermPath(this, null, Keys.BBIE_BIZTERM_BCC_BIZTERM_FK.getInverseKey());

        return _bbieBizterm;
    }

    @Override
    public BccBizterm as(String alias) {
        return new BccBizterm(DSL.name(alias), this);
    }

    @Override
    public BccBizterm as(Name alias) {
        return new BccBizterm(alias, this);
    }

    @Override
    public BccBizterm as(Table<?> alias) {
        return new BccBizterm(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BccBizterm rename(String name) {
        return new BccBizterm(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BccBizterm rename(Name name) {
        return new BccBizterm(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BccBizterm rename(Table<?> name) {
        return new BccBizterm(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccBizterm where(Condition condition) {
        return new BccBizterm(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccBizterm where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccBizterm where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccBizterm where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccBizterm where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccBizterm where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccBizterm where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccBizterm where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccBizterm whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccBizterm whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
