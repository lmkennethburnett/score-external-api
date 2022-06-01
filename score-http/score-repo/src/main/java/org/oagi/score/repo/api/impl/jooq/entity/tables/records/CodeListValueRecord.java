/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record17;
import org.jooq.Row17;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CodeListValue;


/**
 * Each record in this table stores a code list value of a code list. A code
 * list value may be inherited from another code list on which it is based.
 * However, inherited value may be restricted (i.e., disabled and cannot be
 * used) in this code list, i.e., the USED_INDICATOR = false. If the value
 * cannot be used since the based code list, then the LOCKED_INDICATOR = TRUE,
 * because the USED_INDICATOR of such code list value is FALSE by default and
 * can no longer be changed.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CodeListValueRecord extends UpdatableRecordImpl<CodeListValueRecord> implements Record17<ULong, String, ULong, ULong, String, String, String, String, Byte, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime, ULong, ULong> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.code_list_value.code_list_value_id</code>.
     * Internal, primary database key.
     */
    public void setCodeListValueId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.code_list_value_id</code>.
     * Internal, primary database key.
     */
    public ULong getCodeListValueId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.code_list_value.guid</code>. A globally unique
     * identifier (GUID).
     */
    public void setGuid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.guid</code>. A globally unique
     * identifier (GUID).
     */
    public String getGuid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.code_list_value.code_list_id</code>. Foreign key to
     * the CODE_LIST table. It indicates the code list this code value belonging
     * to.
     */
    public void setCodeListId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.code_list_id</code>. Foreign key to
     * the CODE_LIST table. It indicates the code list this code value belonging
     * to.
     */
    public ULong getCodeListId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>oagi.code_list_value.based_code_list_value_id</code>.
     * Foreign key to the CODE_LIST_VALUE table itself. This column is used when
     * the CODE_LIST is derived from the based CODE_LIST.
     */
    public void setBasedCodeListValueId(ULong value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.based_code_list_value_id</code>.
     * Foreign key to the CODE_LIST_VALUE table itself. This column is used when
     * the CODE_LIST is derived from the based CODE_LIST.
     */
    public ULong getBasedCodeListValueId() {
        return (ULong) get(3);
    }

    /**
     * Setter for <code>oagi.code_list_value.value</code>. The code list value
     * used in the instance data, e.g., EA, US-EN.
     */
    public void setValue(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.value</code>. The code list value
     * used in the instance data, e.g., EA, US-EN.
     */
    public String getValue() {
        return (String) get(4);
    }

    /**
     * Setter for <code>oagi.code_list_value.meaning</code>. The description or
     * explanation of the code list value, e.g., 'Each' for EA, 'English' for
     * EN.
     */
    public void setMeaning(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.meaning</code>. The description or
     * explanation of the code list value, e.g., 'Each' for EA, 'English' for
     * EN.
     */
    public String getMeaning() {
        return (String) get(5);
    }

    /**
     * Setter for <code>oagi.code_list_value.definition</code>. Long description
     * or explannation of the code list value, e.g., 'EA is a discrete quantity
     * for counting each unit of an item, such as, 2 shampoo bottles, 3 box of
     * cereals'.
     */
    public void setDefinition(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.definition</code>. Long description
     * or explannation of the code list value, e.g., 'EA is a discrete quantity
     * for counting each unit of an item, such as, 2 shampoo bottles, 3 box of
     * cereals'.
     */
    public String getDefinition() {
        return (String) get(6);
    }

    /**
     * Setter for <code>oagi.code_list_value.definition_source</code>. This is
     * typically a URL identifying the source of the DEFINITION column.
     */
    public void setDefinitionSource(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.definition_source</code>. This is
     * typically a URL identifying the source of the DEFINITION column.
     */
    public String getDefinitionSource() {
        return (String) get(7);
    }

    /**
     * Setter for <code>oagi.code_list_value.is_deprecated</code>. Indicates
     * whether the code list value is deprecated and should not be reused (i.e.,
     * no new reference to this record should be allowed).
     */
    public void setIsDeprecated(Byte value) {
        set(8, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.is_deprecated</code>. Indicates
     * whether the code list value is deprecated and should not be reused (i.e.,
     * no new reference to this record should be allowed).
     */
    public Byte getIsDeprecated() {
        return (Byte) get(8);
    }

    /**
     * Setter for
     * <code>oagi.code_list_value.replacement_code_list_value_id</code>. This
     * refers to a replacement if the record is deprecated.
     */
    public void setReplacementCodeListValueId(ULong value) {
        set(9, value);
    }

    /**
     * Getter for
     * <code>oagi.code_list_value.replacement_code_list_value_id</code>. This
     * refers to a replacement if the record is deprecated.
     */
    public ULong getReplacementCodeListValueId() {
        return (ULong) get(9);
    }

    /**
     * Setter for <code>oagi.code_list_value.created_by</code>. Foreign key to
     * the APP_USER table. It indicates the user who created the code list.
     */
    public void setCreatedBy(ULong value) {
        set(10, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.created_by</code>. Foreign key to
     * the APP_USER table. It indicates the user who created the code list.
     */
    public ULong getCreatedBy() {
        return (ULong) get(10);
    }

    /**
     * Setter for <code>oagi.code_list_value.owner_user_id</code>. Foreign key
     * to the APP_USER table. This is the user who owns the entity, is allowed
     * to edit the entity, and who can transfer the ownership to another user.
     * 
     * The ownership can change throughout the history, but undoing shouldn't
     * rollback the ownership.
     */
    public void setOwnerUserId(ULong value) {
        set(11, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.owner_user_id</code>. Foreign key
     * to the APP_USER table. This is the user who owns the entity, is allowed
     * to edit the entity, and who can transfer the ownership to another user.
     * 
     * The ownership can change throughout the history, but undoing shouldn't
     * rollback the ownership.
     */
    public ULong getOwnerUserId() {
        return (ULong) get(11);
    }

    /**
     * Setter for <code>oagi.code_list_value.last_updated_by</code>. Foreign key
     * to the APP_USER table. It identifies the user who last updated the code
     * list.
     */
    public void setLastUpdatedBy(ULong value) {
        set(12, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.last_updated_by</code>. Foreign key
     * to the APP_USER table. It identifies the user who last updated the code
     * list.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(12);
    }

    /**
     * Setter for <code>oagi.code_list_value.creation_timestamp</code>.
     * Timestamp when the code list was created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(13, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.creation_timestamp</code>.
     * Timestamp when the code list was created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(13);
    }

    /**
     * Setter for <code>oagi.code_list_value.last_update_timestamp</code>.
     * Timestamp when the code list was last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(14, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.last_update_timestamp</code>.
     * Timestamp when the code list was last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(14);
    }

    /**
     * Setter for <code>oagi.code_list_value.prev_code_list_value_id</code>. A
     * self-foreign key to indicate the previous history record.
     */
    public void setPrevCodeListValueId(ULong value) {
        set(15, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.prev_code_list_value_id</code>. A
     * self-foreign key to indicate the previous history record.
     */
    public ULong getPrevCodeListValueId() {
        return (ULong) get(15);
    }

    /**
     * Setter for <code>oagi.code_list_value.next_code_list_value_id</code>. A
     * self-foreign key to indicate the next history record.
     */
    public void setNextCodeListValueId(ULong value) {
        set(16, value);
    }

    /**
     * Getter for <code>oagi.code_list_value.next_code_list_value_id</code>. A
     * self-foreign key to indicate the next history record.
     */
    public ULong getNextCodeListValueId() {
        return (ULong) get(16);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record17 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row17<ULong, String, ULong, ULong, String, String, String, String, Byte, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime, ULong, ULong> fieldsRow() {
        return (Row17) super.fieldsRow();
    }

    @Override
    public Row17<ULong, String, ULong, ULong, String, String, String, String, Byte, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime, ULong, ULong> valuesRow() {
        return (Row17) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CodeListValue.CODE_LIST_VALUE.CODE_LIST_VALUE_ID;
    }

    @Override
    public Field<String> field2() {
        return CodeListValue.CODE_LIST_VALUE.GUID;
    }

    @Override
    public Field<ULong> field3() {
        return CodeListValue.CODE_LIST_VALUE.CODE_LIST_ID;
    }

    @Override
    public Field<ULong> field4() {
        return CodeListValue.CODE_LIST_VALUE.BASED_CODE_LIST_VALUE_ID;
    }

    @Override
    public Field<String> field5() {
        return CodeListValue.CODE_LIST_VALUE.VALUE;
    }

    @Override
    public Field<String> field6() {
        return CodeListValue.CODE_LIST_VALUE.MEANING;
    }

    @Override
    public Field<String> field7() {
        return CodeListValue.CODE_LIST_VALUE.DEFINITION;
    }

    @Override
    public Field<String> field8() {
        return CodeListValue.CODE_LIST_VALUE.DEFINITION_SOURCE;
    }

    @Override
    public Field<Byte> field9() {
        return CodeListValue.CODE_LIST_VALUE.IS_DEPRECATED;
    }

    @Override
    public Field<ULong> field10() {
        return CodeListValue.CODE_LIST_VALUE.REPLACEMENT_CODE_LIST_VALUE_ID;
    }

    @Override
    public Field<ULong> field11() {
        return CodeListValue.CODE_LIST_VALUE.CREATED_BY;
    }

    @Override
    public Field<ULong> field12() {
        return CodeListValue.CODE_LIST_VALUE.OWNER_USER_ID;
    }

    @Override
    public Field<ULong> field13() {
        return CodeListValue.CODE_LIST_VALUE.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field14() {
        return CodeListValue.CODE_LIST_VALUE.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field15() {
        return CodeListValue.CODE_LIST_VALUE.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public Field<ULong> field16() {
        return CodeListValue.CODE_LIST_VALUE.PREV_CODE_LIST_VALUE_ID;
    }

    @Override
    public Field<ULong> field17() {
        return CodeListValue.CODE_LIST_VALUE.NEXT_CODE_LIST_VALUE_ID;
    }

    @Override
    public ULong component1() {
        return getCodeListValueId();
    }

    @Override
    public String component2() {
        return getGuid();
    }

    @Override
    public ULong component3() {
        return getCodeListId();
    }

    @Override
    public ULong component4() {
        return getBasedCodeListValueId();
    }

    @Override
    public String component5() {
        return getValue();
    }

    @Override
    public String component6() {
        return getMeaning();
    }

    @Override
    public String component7() {
        return getDefinition();
    }

    @Override
    public String component8() {
        return getDefinitionSource();
    }

    @Override
    public Byte component9() {
        return getIsDeprecated();
    }

    @Override
    public ULong component10() {
        return getReplacementCodeListValueId();
    }

    @Override
    public ULong component11() {
        return getCreatedBy();
    }

    @Override
    public ULong component12() {
        return getOwnerUserId();
    }

    @Override
    public ULong component13() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime component14() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime component15() {
        return getLastUpdateTimestamp();
    }

    @Override
    public ULong component16() {
        return getPrevCodeListValueId();
    }

    @Override
    public ULong component17() {
        return getNextCodeListValueId();
    }

    @Override
    public ULong value1() {
        return getCodeListValueId();
    }

    @Override
    public String value2() {
        return getGuid();
    }

    @Override
    public ULong value3() {
        return getCodeListId();
    }

    @Override
    public ULong value4() {
        return getBasedCodeListValueId();
    }

    @Override
    public String value5() {
        return getValue();
    }

    @Override
    public String value6() {
        return getMeaning();
    }

    @Override
    public String value7() {
        return getDefinition();
    }

    @Override
    public String value8() {
        return getDefinitionSource();
    }

    @Override
    public Byte value9() {
        return getIsDeprecated();
    }

    @Override
    public ULong value10() {
        return getReplacementCodeListValueId();
    }

    @Override
    public ULong value11() {
        return getCreatedBy();
    }

    @Override
    public ULong value12() {
        return getOwnerUserId();
    }

    @Override
    public ULong value13() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime value14() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime value15() {
        return getLastUpdateTimestamp();
    }

    @Override
    public ULong value16() {
        return getPrevCodeListValueId();
    }

    @Override
    public ULong value17() {
        return getNextCodeListValueId();
    }

    @Override
    public CodeListValueRecord value1(ULong value) {
        setCodeListValueId(value);
        return this;
    }

    @Override
    public CodeListValueRecord value2(String value) {
        setGuid(value);
        return this;
    }

    @Override
    public CodeListValueRecord value3(ULong value) {
        setCodeListId(value);
        return this;
    }

    @Override
    public CodeListValueRecord value4(ULong value) {
        setBasedCodeListValueId(value);
        return this;
    }

    @Override
    public CodeListValueRecord value5(String value) {
        setValue(value);
        return this;
    }

    @Override
    public CodeListValueRecord value6(String value) {
        setMeaning(value);
        return this;
    }

    @Override
    public CodeListValueRecord value7(String value) {
        setDefinition(value);
        return this;
    }

    @Override
    public CodeListValueRecord value8(String value) {
        setDefinitionSource(value);
        return this;
    }

    @Override
    public CodeListValueRecord value9(Byte value) {
        setIsDeprecated(value);
        return this;
    }

    @Override
    public CodeListValueRecord value10(ULong value) {
        setReplacementCodeListValueId(value);
        return this;
    }

    @Override
    public CodeListValueRecord value11(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public CodeListValueRecord value12(ULong value) {
        setOwnerUserId(value);
        return this;
    }

    @Override
    public CodeListValueRecord value13(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public CodeListValueRecord value14(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public CodeListValueRecord value15(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public CodeListValueRecord value16(ULong value) {
        setPrevCodeListValueId(value);
        return this;
    }

    @Override
    public CodeListValueRecord value17(ULong value) {
        setNextCodeListValueId(value);
        return this;
    }

    @Override
    public CodeListValueRecord values(ULong value1, String value2, ULong value3, ULong value4, String value5, String value6, String value7, String value8, Byte value9, ULong value10, ULong value11, ULong value12, ULong value13, LocalDateTime value14, LocalDateTime value15, ULong value16, ULong value17) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CodeListValueRecord
     */
    public CodeListValueRecord() {
        super(CodeListValue.CODE_LIST_VALUE);
    }

    /**
     * Create a detached, initialised CodeListValueRecord
     */
    public CodeListValueRecord(ULong codeListValueId, String guid, ULong codeListId, ULong basedCodeListValueId, String value, String meaning, String definition, String definitionSource, Byte isDeprecated, ULong replacementCodeListValueId, ULong createdBy, ULong ownerUserId, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp, ULong prevCodeListValueId, ULong nextCodeListValueId) {
        super(CodeListValue.CODE_LIST_VALUE);

        setCodeListValueId(codeListValueId);
        setGuid(guid);
        setCodeListId(codeListId);
        setBasedCodeListValueId(basedCodeListValueId);
        setValue(value);
        setMeaning(meaning);
        setDefinition(definition);
        setDefinitionSource(definitionSource);
        setIsDeprecated(isDeprecated);
        setReplacementCodeListValueId(replacementCodeListValueId);
        setCreatedBy(createdBy);
        setOwnerUserId(ownerUserId);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        setPrevCodeListValueId(prevCodeListValueId);
        setNextCodeListValueId(nextCodeListValueId);
    }
}
