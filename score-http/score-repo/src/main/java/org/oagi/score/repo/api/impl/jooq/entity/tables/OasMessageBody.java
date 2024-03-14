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
import org.oagi.score.repo.api.impl.jooq.entity.Indexes;
import org.oagi.score.repo.api.impl.jooq.entity.Keys;
import org.oagi.score.repo.api.impl.jooq.entity.Oagi;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasRequest.OasRequestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasResponse.OasResponsePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.TopLevelAsbiep.TopLevelAsbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.OasMessageBodyRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasMessageBody extends TableImpl<OasMessageBodyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.oas_message_body</code>
     */
    public static final OasMessageBody OAS_MESSAGE_BODY = new OasMessageBody();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OasMessageBodyRecord> getRecordType() {
        return OasMessageBodyRecord.class;
    }

    /**
     * The column <code>oagi.oas_message_body.oas_message_body_id</code>. The
     * primary key of the record.
     */
    public final TableField<OasMessageBodyRecord, ULong> OAS_MESSAGE_BODY_ID = createField(DSL.name("oas_message_body_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_message_body.top_level_asbiep_id</code>. A
     * reference of the ASBIEP record.
     */
    public final TableField<OasMessageBodyRecord, ULong> TOP_LEVEL_ASBIEP_ID = createField(DSL.name("top_level_asbiep_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "A reference of the ASBIEP record.");

    /**
     * The column <code>oagi.oas_message_body.created_by</code>. The user who
     * creates the record.
     */
    public final TableField<OasMessageBodyRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who creates the record.");

    /**
     * The column <code>oagi.oas_message_body.last_updated_by</code>. The user
     * who last updates the record.
     */
    public final TableField<OasMessageBodyRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who last updates the record.");

    /**
     * The column <code>oagi.oas_message_body.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public final TableField<OasMessageBodyRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is created.");

    /**
     * The column <code>oagi.oas_message_body.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public final TableField<OasMessageBodyRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is last updated.");

    private OasMessageBody(Name alias, Table<OasMessageBodyRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private OasMessageBody(Name alias, Table<OasMessageBodyRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.oas_message_body</code> table reference
     */
    public OasMessageBody(String alias) {
        this(DSL.name(alias), OAS_MESSAGE_BODY);
    }

    /**
     * Create an aliased <code>oagi.oas_message_body</code> table reference
     */
    public OasMessageBody(Name alias) {
        this(alias, OAS_MESSAGE_BODY);
    }

    /**
     * Create a <code>oagi.oas_message_body</code> table reference
     */
    public OasMessageBody() {
        this(DSL.name("oas_message_body"), null);
    }

    public <O extends Record> OasMessageBody(Table<O> path, ForeignKey<O, OasMessageBodyRecord> childPath, InverseForeignKey<O, OasMessageBodyRecord> parentPath) {
        super(path, childPath, parentPath, OAS_MESSAGE_BODY);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class OasMessageBodyPath extends OasMessageBody implements Path<OasMessageBodyRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> OasMessageBodyPath(Table<O> path, ForeignKey<O, OasMessageBodyRecord> childPath, InverseForeignKey<O, OasMessageBodyRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private OasMessageBodyPath(Name alias, Table<OasMessageBodyRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public OasMessageBodyPath as(String alias) {
            return new OasMessageBodyPath(DSL.name(alias), this);
        }

        @Override
        public OasMessageBodyPath as(Name alias) {
            return new OasMessageBodyPath(alias, this);
        }

        @Override
        public OasMessageBodyPath as(Table<?> alias) {
            return new OasMessageBodyPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.OAS_MESSAGE_BODY_OAS_MESSAGE_BODY_OAS_ASBIEP_ID_FK);
    }

    @Override
    public Identity<OasMessageBodyRecord, ULong> getIdentity() {
        return (Identity<OasMessageBodyRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<OasMessageBodyRecord> getPrimaryKey() {
        return Keys.KEY_OAS_MESSAGE_BODY_PRIMARY;
    }

    @Override
    public List<ForeignKey<OasMessageBodyRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OAS_MESSAGE_BODY_TOP_LEVEL_ASBIEP_ID_FK, Keys.OAS_MESSAGE_BODY_CREATED_BY_FK, Keys.OAS_MESSAGE_BODY_LAST_UPDATED_BY_FK);
    }

    private transient TopLevelAsbiepPath _topLevelAsbiep;

    /**
     * Get the implicit join path to the <code>oagi.top_level_asbiep</code>
     * table.
     */
    public TopLevelAsbiepPath topLevelAsbiep() {
        if (_topLevelAsbiep == null)
            _topLevelAsbiep = new TopLevelAsbiepPath(this, Keys.OAS_MESSAGE_BODY_TOP_LEVEL_ASBIEP_ID_FK, null);

        return _topLevelAsbiep;
    }

    private transient AppUserPath _oasMessageBodyCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_message_body_created_by_fk</code> key.
     */
    public AppUserPath oasMessageBodyCreatedByFk() {
        if (_oasMessageBodyCreatedByFk == null)
            _oasMessageBodyCreatedByFk = new AppUserPath(this, Keys.OAS_MESSAGE_BODY_CREATED_BY_FK, null);

        return _oasMessageBodyCreatedByFk;
    }

    private transient AppUserPath _oasMessageBodyLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_message_body_last_updated_by_fk</code> key.
     */
    public AppUserPath oasMessageBodyLastUpdatedByFk() {
        if (_oasMessageBodyLastUpdatedByFk == null)
            _oasMessageBodyLastUpdatedByFk = new AppUserPath(this, Keys.OAS_MESSAGE_BODY_LAST_UPDATED_BY_FK, null);

        return _oasMessageBodyLastUpdatedByFk;
    }

    private transient OasRequestPath _oasRequest;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_request</code>
     * table
     */
    public OasRequestPath oasRequest() {
        if (_oasRequest == null)
            _oasRequest = new OasRequestPath(this, null, Keys.OAS_REQUEST_OAS_MESSAGE_BODY_ID_FK.getInverseKey());

        return _oasRequest;
    }

    private transient OasResponsePath _oasResponse;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_response</code>
     * table
     */
    public OasResponsePath oasResponse() {
        if (_oasResponse == null)
            _oasResponse = new OasResponsePath(this, null, Keys.OAS_RESPONSE_OAS_MESSAGE_BODY_ID_FK.getInverseKey());

        return _oasResponse;
    }

    @Override
    public OasMessageBody as(String alias) {
        return new OasMessageBody(DSL.name(alias), this);
    }

    @Override
    public OasMessageBody as(Name alias) {
        return new OasMessageBody(alias, this);
    }

    @Override
    public OasMessageBody as(Table<?> alias) {
        return new OasMessageBody(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OasMessageBody rename(String name) {
        return new OasMessageBody(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasMessageBody rename(Name name) {
        return new OasMessageBody(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasMessageBody rename(Table<?> name) {
        return new OasMessageBody(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasMessageBody where(Condition condition) {
        return new OasMessageBody(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasMessageBody where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasMessageBody where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasMessageBody where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasMessageBody where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasMessageBody where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasMessageBody where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasMessageBody where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasMessageBody whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasMessageBody whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
