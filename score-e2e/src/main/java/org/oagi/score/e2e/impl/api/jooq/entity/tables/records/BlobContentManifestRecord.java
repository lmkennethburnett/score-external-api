/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.BlobContentManifest;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BlobContentManifestRecord extends UpdatableRecordImpl<BlobContentManifestRecord> implements Record6<ULong, ULong, ULong, Byte, ULong, ULong> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>oagi.blob_content_manifest.blob_content_manifest_id</code>.
     */
    public void setBlobContentManifestId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for
     * <code>oagi.blob_content_manifest.blob_content_manifest_id</code>.
     */
    public ULong getBlobContentManifestId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.blob_content_manifest.blob_content_id</code>.
     */
    public void setBlobContentId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.blob_content_manifest.blob_content_id</code>.
     */
    public ULong getBlobContentId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.blob_content_manifest.release_id</code>.
     */
    public void setReleaseId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.blob_content_manifest.release_id</code>.
     */
    public ULong getReleaseId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>oagi.blob_content_manifest.conflict</code>. This
     * indicates that there is a conflict between self and relationship.
     */
    public void setConflict(Byte value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.blob_content_manifest.conflict</code>. This
     * indicates that there is a conflict between self and relationship.
     */
    public Byte getConflict() {
        return (Byte) get(3);
    }

    /**
     * Setter for
     * <code>oagi.blob_content_manifest.prev_blob_content_manifest_id</code>.
     */
    public void setPrevBlobContentManifestId(ULong value) {
        set(4, value);
    }

    /**
     * Getter for
     * <code>oagi.blob_content_manifest.prev_blob_content_manifest_id</code>.
     */
    public ULong getPrevBlobContentManifestId() {
        return (ULong) get(4);
    }

    /**
     * Setter for
     * <code>oagi.blob_content_manifest.next_blob_content_manifest_id</code>.
     */
    public void setNextBlobContentManifestId(ULong value) {
        set(5, value);
    }

    /**
     * Getter for
     * <code>oagi.blob_content_manifest.next_blob_content_manifest_id</code>.
     */
    public ULong getNextBlobContentManifestId() {
        return (ULong) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<ULong, ULong, ULong, Byte, ULong, ULong> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<ULong, ULong, ULong, Byte, ULong, ULong> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return BlobContentManifest.BLOB_CONTENT_MANIFEST.BLOB_CONTENT_MANIFEST_ID;
    }

    @Override
    public Field<ULong> field2() {
        return BlobContentManifest.BLOB_CONTENT_MANIFEST.BLOB_CONTENT_ID;
    }

    @Override
    public Field<ULong> field3() {
        return BlobContentManifest.BLOB_CONTENT_MANIFEST.RELEASE_ID;
    }

    @Override
    public Field<Byte> field4() {
        return BlobContentManifest.BLOB_CONTENT_MANIFEST.CONFLICT;
    }

    @Override
    public Field<ULong> field5() {
        return BlobContentManifest.BLOB_CONTENT_MANIFEST.PREV_BLOB_CONTENT_MANIFEST_ID;
    }

    @Override
    public Field<ULong> field6() {
        return BlobContentManifest.BLOB_CONTENT_MANIFEST.NEXT_BLOB_CONTENT_MANIFEST_ID;
    }

    @Override
    public ULong component1() {
        return getBlobContentManifestId();
    }

    @Override
    public ULong component2() {
        return getBlobContentId();
    }

    @Override
    public ULong component3() {
        return getReleaseId();
    }

    @Override
    public Byte component4() {
        return getConflict();
    }

    @Override
    public ULong component5() {
        return getPrevBlobContentManifestId();
    }

    @Override
    public ULong component6() {
        return getNextBlobContentManifestId();
    }

    @Override
    public ULong value1() {
        return getBlobContentManifestId();
    }

    @Override
    public ULong value2() {
        return getBlobContentId();
    }

    @Override
    public ULong value3() {
        return getReleaseId();
    }

    @Override
    public Byte value4() {
        return getConflict();
    }

    @Override
    public ULong value5() {
        return getPrevBlobContentManifestId();
    }

    @Override
    public ULong value6() {
        return getNextBlobContentManifestId();
    }

    @Override
    public BlobContentManifestRecord value1(ULong value) {
        setBlobContentManifestId(value);
        return this;
    }

    @Override
    public BlobContentManifestRecord value2(ULong value) {
        setBlobContentId(value);
        return this;
    }

    @Override
    public BlobContentManifestRecord value3(ULong value) {
        setReleaseId(value);
        return this;
    }

    @Override
    public BlobContentManifestRecord value4(Byte value) {
        setConflict(value);
        return this;
    }

    @Override
    public BlobContentManifestRecord value5(ULong value) {
        setPrevBlobContentManifestId(value);
        return this;
    }

    @Override
    public BlobContentManifestRecord value6(ULong value) {
        setNextBlobContentManifestId(value);
        return this;
    }

    @Override
    public BlobContentManifestRecord values(ULong value1, ULong value2, ULong value3, Byte value4, ULong value5, ULong value6) {
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
     * Create a detached BlobContentManifestRecord
     */
    public BlobContentManifestRecord() {
        super(BlobContentManifest.BLOB_CONTENT_MANIFEST);
    }

    /**
     * Create a detached, initialised BlobContentManifestRecord
     */
    public BlobContentManifestRecord(ULong blobContentManifestId, ULong blobContentId, ULong releaseId, Byte conflict, ULong prevBlobContentManifestId, ULong nextBlobContentManifestId) {
        super(BlobContentManifest.BLOB_CONTENT_MANIFEST);

        setBlobContentManifestId(blobContentManifestId);
        setBlobContentId(blobContentId);
        setReleaseId(releaseId);
        setConflict(conflict);
        setPrevBlobContentManifestId(prevBlobContentManifestId);
        setNextBlobContentManifestId(nextBlobContentManifestId);
    }
}
