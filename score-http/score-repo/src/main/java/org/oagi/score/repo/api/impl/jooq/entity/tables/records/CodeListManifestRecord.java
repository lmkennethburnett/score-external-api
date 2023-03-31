/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CodeListManifest;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CodeListManifestRecord extends UpdatableRecordImpl<CodeListManifestRecord> implements Record10<ULong, ULong, ULong, ULong, ULong, Byte, ULong, ULong, ULong, ULong> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.code_list_manifest.code_list_manifest_id</code>.
     */
    public void setCodeListManifestId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.code_list_manifest.code_list_manifest_id</code>.
     */
    public ULong getCodeListManifestId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.code_list_manifest.release_id</code>.
     */
    public void setReleaseId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.code_list_manifest.release_id</code>.
     */
    public ULong getReleaseId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.code_list_manifest.code_list_id</code>.
     */
    public void setCodeListId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.code_list_manifest.code_list_id</code>.
     */
    public ULong getCodeListId() {
        return (ULong) get(2);
    }

    /**
     * Setter for
     * <code>oagi.code_list_manifest.based_code_list_manifest_id</code>.
     */
    public void setBasedCodeListManifestId(ULong value) {
        set(3, value);
    }

    /**
     * Getter for
     * <code>oagi.code_list_manifest.based_code_list_manifest_id</code>.
     */
    public ULong getBasedCodeListManifestId() {
        return (ULong) get(3);
    }

    /**
     * Setter for
     * <code>oagi.code_list_manifest.agency_id_list_value_manifest_id</code>.
     */
    public void setAgencyIdListValueManifestId(ULong value) {
        set(4, value);
    }

    /**
     * Getter for
     * <code>oagi.code_list_manifest.agency_id_list_value_manifest_id</code>.
     */
    public ULong getAgencyIdListValueManifestId() {
        return (ULong) get(4);
    }

    /**
     * Setter for <code>oagi.code_list_manifest.conflict</code>. This indicates
     * that there is a conflict between self and relationship.
     */
    public void setConflict(Byte value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.code_list_manifest.conflict</code>. This indicates
     * that there is a conflict between self and relationship.
     */
    public Byte getConflict() {
        return (Byte) get(5);
    }

    /**
     * Setter for <code>oagi.code_list_manifest.log_id</code>. A foreign key
     * pointed to a log for the current record.
     */
    public void setLogId(ULong value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.code_list_manifest.log_id</code>. A foreign key
     * pointed to a log for the current record.
     */
    public ULong getLogId() {
        return (ULong) get(6);
    }

    /**
     * Setter for
     * <code>oagi.code_list_manifest.replacement_code_list_manifest_id</code>.
     * This refers to a replacement manifest if the record is deprecated.
     */
    public void setReplacementCodeListManifestId(ULong value) {
        set(7, value);
    }

    /**
     * Getter for
     * <code>oagi.code_list_manifest.replacement_code_list_manifest_id</code>.
     * This refers to a replacement manifest if the record is deprecated.
     */
    public ULong getReplacementCodeListManifestId() {
        return (ULong) get(7);
    }

    /**
     * Setter for
     * <code>oagi.code_list_manifest.prev_code_list_manifest_id</code>.
     */
    public void setPrevCodeListManifestId(ULong value) {
        set(8, value);
    }

    /**
     * Getter for
     * <code>oagi.code_list_manifest.prev_code_list_manifest_id</code>.
     */
    public ULong getPrevCodeListManifestId() {
        return (ULong) get(8);
    }

    /**
     * Setter for
     * <code>oagi.code_list_manifest.next_code_list_manifest_id</code>.
     */
    public void setNextCodeListManifestId(ULong value) {
        set(9, value);
    }

    /**
     * Getter for
     * <code>oagi.code_list_manifest.next_code_list_manifest_id</code>.
     */
    public ULong getNextCodeListManifestId() {
        return (ULong) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row10<ULong, ULong, ULong, ULong, ULong, Byte, ULong, ULong, ULong, ULong> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    public Row10<ULong, ULong, ULong, ULong, ULong, Byte, ULong, ULong, ULong, ULong> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CodeListManifest.CODE_LIST_MANIFEST.CODE_LIST_MANIFEST_ID;
    }

    @Override
    public Field<ULong> field2() {
        return CodeListManifest.CODE_LIST_MANIFEST.RELEASE_ID;
    }

    @Override
    public Field<ULong> field3() {
        return CodeListManifest.CODE_LIST_MANIFEST.CODE_LIST_ID;
    }

    @Override
    public Field<ULong> field4() {
        return CodeListManifest.CODE_LIST_MANIFEST.BASED_CODE_LIST_MANIFEST_ID;
    }

    @Override
    public Field<ULong> field5() {
        return CodeListManifest.CODE_LIST_MANIFEST.AGENCY_ID_LIST_VALUE_MANIFEST_ID;
    }

    @Override
    public Field<Byte> field6() {
        return CodeListManifest.CODE_LIST_MANIFEST.CONFLICT;
    }

    @Override
    public Field<ULong> field7() {
        return CodeListManifest.CODE_LIST_MANIFEST.LOG_ID;
    }

    @Override
    public Field<ULong> field8() {
        return CodeListManifest.CODE_LIST_MANIFEST.REPLACEMENT_CODE_LIST_MANIFEST_ID;
    }

    @Override
    public Field<ULong> field9() {
        return CodeListManifest.CODE_LIST_MANIFEST.PREV_CODE_LIST_MANIFEST_ID;
    }

    @Override
    public Field<ULong> field10() {
        return CodeListManifest.CODE_LIST_MANIFEST.NEXT_CODE_LIST_MANIFEST_ID;
    }

    @Override
    public ULong component1() {
        return getCodeListManifestId();
    }

    @Override
    public ULong component2() {
        return getReleaseId();
    }

    @Override
    public ULong component3() {
        return getCodeListId();
    }

    @Override
    public ULong component4() {
        return getBasedCodeListManifestId();
    }

    @Override
    public ULong component5() {
        return getAgencyIdListValueManifestId();
    }

    @Override
    public Byte component6() {
        return getConflict();
    }

    @Override
    public ULong component7() {
        return getLogId();
    }

    @Override
    public ULong component8() {
        return getReplacementCodeListManifestId();
    }

    @Override
    public ULong component9() {
        return getPrevCodeListManifestId();
    }

    @Override
    public ULong component10() {
        return getNextCodeListManifestId();
    }

    @Override
    public ULong value1() {
        return getCodeListManifestId();
    }

    @Override
    public ULong value2() {
        return getReleaseId();
    }

    @Override
    public ULong value3() {
        return getCodeListId();
    }

    @Override
    public ULong value4() {
        return getBasedCodeListManifestId();
    }

    @Override
    public ULong value5() {
        return getAgencyIdListValueManifestId();
    }

    @Override
    public Byte value6() {
        return getConflict();
    }

    @Override
    public ULong value7() {
        return getLogId();
    }

    @Override
    public ULong value8() {
        return getReplacementCodeListManifestId();
    }

    @Override
    public ULong value9() {
        return getPrevCodeListManifestId();
    }

    @Override
    public ULong value10() {
        return getNextCodeListManifestId();
    }

    @Override
    public CodeListManifestRecord value1(ULong value) {
        setCodeListManifestId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value2(ULong value) {
        setReleaseId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value3(ULong value) {
        setCodeListId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value4(ULong value) {
        setBasedCodeListManifestId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value5(ULong value) {
        setAgencyIdListValueManifestId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value6(Byte value) {
        setConflict(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value7(ULong value) {
        setLogId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value8(ULong value) {
        setReplacementCodeListManifestId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value9(ULong value) {
        setPrevCodeListManifestId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord value10(ULong value) {
        setNextCodeListManifestId(value);
        return this;
    }

    @Override
    public CodeListManifestRecord values(ULong value1, ULong value2, ULong value3, ULong value4, ULong value5, Byte value6, ULong value7, ULong value8, ULong value9, ULong value10) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CodeListManifestRecord
     */
    public CodeListManifestRecord() {
        super(CodeListManifest.CODE_LIST_MANIFEST);
    }

    /**
     * Create a detached, initialised CodeListManifestRecord
     */
    public CodeListManifestRecord(ULong codeListManifestId, ULong releaseId, ULong codeListId, ULong basedCodeListManifestId, ULong agencyIdListValueManifestId, Byte conflict, ULong logId, ULong replacementCodeListManifestId, ULong prevCodeListManifestId, ULong nextCodeListManifestId) {
        super(CodeListManifest.CODE_LIST_MANIFEST);

        setCodeListManifestId(codeListManifestId);
        setReleaseId(releaseId);
        setCodeListId(codeListId);
        setBasedCodeListManifestId(basedCodeListManifestId);
        setAgencyIdListValueManifestId(agencyIdListValueManifestId);
        setConflict(conflict);
        setLogId(logId);
        setReplacementCodeListManifestId(replacementCodeListManifestId);
        setPrevCodeListManifestId(prevCodeListManifestId);
        setNextCodeListManifestId(nextCodeListManifestId);
        resetChangedOnNotNull();
    }
}
