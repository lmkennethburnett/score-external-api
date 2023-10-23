/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasRequestParameter;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasRequestParameterRecord extends UpdatableRecordImpl<OasRequestParameterRecord> implements Record6<ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.oas_request_parameter.oas_parameter_id</code>. The
     * primary key of the record.
     */
    public void setOasParameterId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.oas_request_parameter.oas_parameter_id</code>. The
     * primary key of the record.
     */
    public ULong getOasParameterId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.oas_request_parameter.oas_request_id</code>. The
     * primary key of the record.
     */
    public void setOasRequestId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.oas_request_parameter.oas_request_id</code>. The
     * primary key of the record.
     */
    public ULong getOasRequestId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.oas_request_parameter.created_by</code>. The user
     * who creates the record.
     */
    public void setCreatedBy(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.oas_request_parameter.created_by</code>. The user
     * who creates the record.
     */
    public ULong getCreatedBy() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>oagi.oas_request_parameter.last_updated_by</code>. The
     * user who last updates the record.
     */
    public void setLastUpdatedBy(ULong value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.oas_request_parameter.last_updated_by</code>. The
     * user who last updates the record.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(3);
    }

    /**
     * Setter for <code>oagi.oas_request_parameter.creation_timestamp</code>.
     * The timestamp when the record is created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.oas_request_parameter.creation_timestamp</code>.
     * The timestamp when the record is created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>oagi.oas_request_parameter.last_update_timestamp</code>.
     * The timestamp when the record is last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.oas_request_parameter.last_update_timestamp</code>.
     * The timestamp when the record is last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<ULong, ULong> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return OasRequestParameter.OAS_REQUEST_PARAMETER.OAS_PARAMETER_ID;
    }

    @Override
    public Field<ULong> field2() {
        return OasRequestParameter.OAS_REQUEST_PARAMETER.OAS_REQUEST_ID;
    }

    @Override
    public Field<ULong> field3() {
        return OasRequestParameter.OAS_REQUEST_PARAMETER.CREATED_BY;
    }

    @Override
    public Field<ULong> field4() {
        return OasRequestParameter.OAS_REQUEST_PARAMETER.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return OasRequestParameter.OAS_REQUEST_PARAMETER.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return OasRequestParameter.OAS_REQUEST_PARAMETER.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public ULong component1() {
        return getOasParameterId();
    }

    @Override
    public ULong component2() {
        return getOasRequestId();
    }

    @Override
    public ULong component3() {
        return getCreatedBy();
    }

    @Override
    public ULong component4() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime component5() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime component6() {
        return getLastUpdateTimestamp();
    }

    @Override
    public ULong value1() {
        return getOasParameterId();
    }

    @Override
    public ULong value2() {
        return getOasRequestId();
    }

    @Override
    public ULong value3() {
        return getCreatedBy();
    }

    @Override
    public ULong value4() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime value5() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime value6() {
        return getLastUpdateTimestamp();
    }

    @Override
    public OasRequestParameterRecord value1(ULong value) {
        setOasParameterId(value);
        return this;
    }

    @Override
    public OasRequestParameterRecord value2(ULong value) {
        setOasRequestId(value);
        return this;
    }

    @Override
    public OasRequestParameterRecord value3(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public OasRequestParameterRecord value4(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public OasRequestParameterRecord value5(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public OasRequestParameterRecord value6(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public OasRequestParameterRecord values(ULong value1, ULong value2, ULong value3, ULong value4, LocalDateTime value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OasRequestParameterRecord
     */
    public OasRequestParameterRecord() {
        super(OasRequestParameter.OAS_REQUEST_PARAMETER);
    }

    /**
     * Create a detached, initialised OasRequestParameterRecord
     */
    public OasRequestParameterRecord(ULong oasParameterId, ULong oasRequestId, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp) {
        super(OasRequestParameter.OAS_REQUEST_PARAMETER);

        setOasParameterId(oasParameterId);
        setOasRequestId(oasRequestId);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        resetChangedOnNotNull();
    }
}
