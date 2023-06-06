/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.UserTenant;


/**
 * This table captures the tenant roles of the user
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UserTenantRecord extends UpdatableRecordImpl<UserTenantRecord> implements Record3<ULong, ULong, ULong> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached UserTenantRecord
     */
    public UserTenantRecord() {
        super(UserTenant.USER_TENANT);
    }

    /**
     * Create a detached, initialised UserTenantRecord
     */
    public UserTenantRecord(ULong userTenantId, ULong tenantId, ULong appUserId) {
        super(UserTenant.USER_TENANT);

        setUserTenantId(userTenantId);
        setTenantId(tenantId);
        setAppUserId(appUserId);
    }

    /**
     * Getter for <code>oagi.user_tenant.user_tenant_id</code>. Primary key
     * column.
     */
    public ULong getUserTenantId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.user_tenant.user_tenant_id</code>. Primary key
     * column.
     */
    public void setUserTenantId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.user_tenant.tenant_id</code>. Assigned tenant to
     * the user.
     */
    public ULong getTenantId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.user_tenant.tenant_id</code>. Assigned tenant to
     * the user.
     */
    public void setTenantId(ULong value) {
        set(1, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>oagi.user_tenant.app_user_id</code>. Application user.
     */
    public ULong getAppUserId() {
        return (ULong) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>oagi.user_tenant.app_user_id</code>. Application user.
     */
    public void setAppUserId(ULong value) {
        set(2, value);
    }

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    @Override
    public Row3<ULong, ULong, ULong> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<ULong, ULong, ULong> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return UserTenant.USER_TENANT.USER_TENANT_ID;
    }

    @Override
    public Field<ULong> field2() {
        return UserTenant.USER_TENANT.TENANT_ID;
    }

    @Override
    public Field<ULong> field3() {
        return UserTenant.USER_TENANT.APP_USER_ID;
    }

    @Override
    public ULong component1() {
        return getUserTenantId();
    }

    @Override
    public ULong component2() {
        return getTenantId();
    }

    @Override
    public ULong component3() {
        return getAppUserId();
    }

    @Override
    public ULong value1() {
        return getUserTenantId();
    }

    @Override
    public ULong value2() {
        return getTenantId();
    }

    @Override
    public ULong value3() {
        return getAppUserId();
    }

    @Override
    public UserTenantRecord value1(ULong value) {
        setUserTenantId(value);
        return this;
    }

    @Override
    public UserTenantRecord value2(ULong value) {
        setTenantId(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    public UserTenantRecord value3(ULong value) {
        setAppUserId(value);
        return this;
    }

    @Override
    public UserTenantRecord values(ULong value1, ULong value2, ULong value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }
}
