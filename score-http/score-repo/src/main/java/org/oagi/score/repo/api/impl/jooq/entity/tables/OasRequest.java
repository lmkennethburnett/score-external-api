/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function14;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row14;
import org.jooq.Schema;
import org.jooq.SelectField;
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
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.OasRequestRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasRequest extends TableImpl<OasRequestRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.oas_request</code>
     */
    public static final OasRequest OAS_REQUEST = new OasRequest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OasRequestRecord> getRecordType() {
        return OasRequestRecord.class;
    }

    /**
     * The column <code>oagi.oas_request.oas_request_id</code>. The primary key
     * of the record.
     */
    public final TableField<OasRequestRecord, ULong> OAS_REQUEST_ID = createField(DSL.name("oas_request_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_request.oas_operation_id</code>.
     */
    public final TableField<OasRequestRecord, ULong> OAS_OPERATION_ID = createField(DSL.name("oas_operation_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.oas_request.description</code>. A brief description
     * of the request body. This could contain examples of use. CommonMark
     * syntax MAY be used for rich text representation.
     */
    public final TableField<OasRequestRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "A brief description of the request body. This could contain examples of use. CommonMark syntax MAY be used for rich text representation.");

    /**
     * The column <code>oagi.oas_request.required</code>. Determines if the
     * request body is required in the request. Defaults to false.
     */
    public final TableField<OasRequestRecord, Byte> REQUIRED = createField(DSL.name("required"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "Determines if the request body is required in the request. Defaults to false.");

    /**
     * The column <code>oagi.oas_request.oas_message_body_id</code>.
     */
    public final TableField<OasRequestRecord, ULong> OAS_MESSAGE_BODY_ID = createField(DSL.name("oas_message_body_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.oas_request.make_array_indicator</code>.
     */
    public final TableField<OasRequestRecord, Byte> MAKE_ARRAY_INDICATOR = createField(DSL.name("make_array_indicator"), SQLDataType.TINYINT.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>oagi.oas_request.suppress_root_indicator</code>.
     */
    public final TableField<OasRequestRecord, Byte> SUPPRESS_ROOT_INDICATOR = createField(DSL.name("suppress_root_indicator"), SQLDataType.TINYINT.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>oagi.oas_request.meta_header_top_level_asbiep_id</code>.
     */
    public final TableField<OasRequestRecord, ULong> META_HEADER_TOP_LEVEL_ASBIEP_ID = createField(DSL.name("meta_header_top_level_asbiep_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>oagi.oas_request.pagination_top_level_asbiep_id</code>.
     */
    public final TableField<OasRequestRecord, ULong> PAGINATION_TOP_LEVEL_ASBIEP_ID = createField(DSL.name("pagination_top_level_asbiep_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>oagi.oas_request.is_callback</code>. If is_callback ==
     * true, oas_callback table has URL rows in it, with eventType=Success or
     * Failed values to allow different endpoints to be called when a successful
     * request is processed, or failure endpoint when an exception occurs.
     */
    public final TableField<OasRequestRecord, Byte> IS_CALLBACK = createField(DSL.name("is_callback"), SQLDataType.TINYINT.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "If is_callback == true, oas_callback table has URL rows in it, with eventType=Success or Failed values to allow different endpoints to be called when a successful request is processed, or failure endpoint when an exception occurs.");

    /**
     * The column <code>oagi.oas_request.created_by</code>. The user who creates
     * the record.
     */
    public final TableField<OasRequestRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who creates the record.");

    /**
     * The column <code>oagi.oas_request.last_updated_by</code>. The user who
     * last updates the record.
     */
    public final TableField<OasRequestRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who last updates the record.");

    /**
     * The column <code>oagi.oas_request.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public final TableField<OasRequestRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is created.");

    /**
     * The column <code>oagi.oas_request.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public final TableField<OasRequestRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is last updated.");

    private OasRequest(Name alias, Table<OasRequestRecord> aliased) {
        this(alias, aliased, null);
    }

    private OasRequest(Name alias, Table<OasRequestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.oas_request</code> table reference
     */
    public OasRequest(String alias) {
        this(DSL.name(alias), OAS_REQUEST);
    }

    /**
     * Create an aliased <code>oagi.oas_request</code> table reference
     */
    public OasRequest(Name alias) {
        this(alias, OAS_REQUEST);
    }

    /**
     * Create a <code>oagi.oas_request</code> table reference
     */
    public OasRequest() {
        this(DSL.name("oas_request"), null);
    }

    public <O extends Record> OasRequest(Table<O> child, ForeignKey<O, OasRequestRecord> key) {
        super(child, key, OAS_REQUEST);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<OasRequestRecord, ULong> getIdentity() {
        return (Identity<OasRequestRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<OasRequestRecord> getPrimaryKey() {
        return Keys.KEY_OAS_REQUEST_PRIMARY;
    }

    @Override
    public List<ForeignKey<OasRequestRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OAS_REQUEST_OAS_OPERATION_ID_FK, Keys.OAS_REQUEST_OAS_MESSAGE_BODY_ID_FK, Keys.OAS_REQUEST_META_HEADER_TOP_LEVEL_ASBIEP_ID_FK, Keys.OAS_REQUEST_PAGINATION_TOP_LEVEL_ASBIEP_ID_FK, Keys.OAS_REQUEST_CREATED_BY_FK, Keys.OAS_REQUEST_LAST_UPDATED_BY_FK);
    }

    private transient OasOperation _oasOperation;
    private transient OasMessageBody _oasMessageBody;
    private transient TopLevelAsbiep _oasRequestMetaHeaderTopLevelAsbiepIdFk;
    private transient TopLevelAsbiep _oasRequestPaginationTopLevelAsbiepIdFk;
    private transient AppUser _oasRequestCreatedByFk;
    private transient AppUser _oasRequestLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.oas_operation</code> table.
     */
    public OasOperation oasOperation() {
        if (_oasOperation == null)
            _oasOperation = new OasOperation(this, Keys.OAS_REQUEST_OAS_OPERATION_ID_FK);

        return _oasOperation;
    }

    /**
     * Get the implicit join path to the <code>oagi.oas_message_body</code>
     * table.
     */
    public OasMessageBody oasMessageBody() {
        if (_oasMessageBody == null)
            _oasMessageBody = new OasMessageBody(this, Keys.OAS_REQUEST_OAS_MESSAGE_BODY_ID_FK);

        return _oasMessageBody;
    }

    /**
     * Get the implicit join path to the <code>oagi.top_level_asbiep</code>
     * table, via the
     * <code>oas_request_meta_header_top_level_asbiep_id_fk</code> key.
     */
    public TopLevelAsbiep oasRequestMetaHeaderTopLevelAsbiepIdFk() {
        if (_oasRequestMetaHeaderTopLevelAsbiepIdFk == null)
            _oasRequestMetaHeaderTopLevelAsbiepIdFk = new TopLevelAsbiep(this, Keys.OAS_REQUEST_META_HEADER_TOP_LEVEL_ASBIEP_ID_FK);

        return _oasRequestMetaHeaderTopLevelAsbiepIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.top_level_asbiep</code>
     * table, via the <code>oas_request_pagination_top_level_asbiep_id_fk</code>
     * key.
     */
    public TopLevelAsbiep oasRequestPaginationTopLevelAsbiepIdFk() {
        if (_oasRequestPaginationTopLevelAsbiepIdFk == null)
            _oasRequestPaginationTopLevelAsbiepIdFk = new TopLevelAsbiep(this, Keys.OAS_REQUEST_PAGINATION_TOP_LEVEL_ASBIEP_ID_FK);

        return _oasRequestPaginationTopLevelAsbiepIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_request_created_by_fk</code> key.
     */
    public AppUser oasRequestCreatedByFk() {
        if (_oasRequestCreatedByFk == null)
            _oasRequestCreatedByFk = new AppUser(this, Keys.OAS_REQUEST_CREATED_BY_FK);

        return _oasRequestCreatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_request_last_updated_by_fk</code> key.
     */
    public AppUser oasRequestLastUpdatedByFk() {
        if (_oasRequestLastUpdatedByFk == null)
            _oasRequestLastUpdatedByFk = new AppUser(this, Keys.OAS_REQUEST_LAST_UPDATED_BY_FK);

        return _oasRequestLastUpdatedByFk;
    }

    @Override
    public OasRequest as(String alias) {
        return new OasRequest(DSL.name(alias), this);
    }

    @Override
    public OasRequest as(Name alias) {
        return new OasRequest(alias, this);
    }

    @Override
    public OasRequest as(Table<?> alias) {
        return new OasRequest(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OasRequest rename(String name) {
        return new OasRequest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasRequest rename(Name name) {
        return new OasRequest(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasRequest rename(Table<?> name) {
        return new OasRequest(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<ULong, ULong, String, Byte, ULong, Byte, Byte, ULong, ULong, Byte, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function14<? super ULong, ? super ULong, ? super String, ? super Byte, ? super ULong, ? super Byte, ? super Byte, ? super ULong, ? super ULong, ? super Byte, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function14<? super ULong, ? super ULong, ? super String, ? super Byte, ? super ULong, ? super Byte, ? super Byte, ? super ULong, ? super ULong, ? super Byte, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
