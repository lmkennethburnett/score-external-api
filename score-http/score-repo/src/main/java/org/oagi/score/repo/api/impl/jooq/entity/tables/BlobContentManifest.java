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
import org.oagi.score.repo.api.impl.jooq.entity.tables.BlobContent.BlobContentPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BlobContentManifest.BlobContentManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.ModuleBlobContentManifest.ModuleBlobContentManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Release.ReleasePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.BlobContentManifestRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BlobContentManifest extends TableImpl<BlobContentManifestRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.blob_content_manifest</code>
     */
    public static final BlobContentManifest BLOB_CONTENT_MANIFEST = new BlobContentManifest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BlobContentManifestRecord> getRecordType() {
        return BlobContentManifestRecord.class;
    }

    /**
     * The column
     * <code>oagi.blob_content_manifest.blob_content_manifest_id</code>.
     */
    public final TableField<BlobContentManifestRecord, ULong> BLOB_CONTENT_MANIFEST_ID = createField(DSL.name("blob_content_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.blob_content_manifest.blob_content_id</code>.
     */
    public final TableField<BlobContentManifestRecord, ULong> BLOB_CONTENT_ID = createField(DSL.name("blob_content_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.blob_content_manifest.release_id</code>.
     */
    public final TableField<BlobContentManifestRecord, ULong> RELEASE_ID = createField(DSL.name("release_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.blob_content_manifest.conflict</code>. This
     * indicates that there is a conflict between self and relationship.
     */
    public final TableField<BlobContentManifestRecord, Byte> CONFLICT = createField(DSL.name("conflict"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "This indicates that there is a conflict between self and relationship.");

    /**
     * The column
     * <code>oagi.blob_content_manifest.prev_blob_content_manifest_id</code>.
     */
    public final TableField<BlobContentManifestRecord, ULong> PREV_BLOB_CONTENT_MANIFEST_ID = createField(DSL.name("prev_blob_content_manifest_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column
     * <code>oagi.blob_content_manifest.next_blob_content_manifest_id</code>.
     */
    public final TableField<BlobContentManifestRecord, ULong> NEXT_BLOB_CONTENT_MANIFEST_ID = createField(DSL.name("next_blob_content_manifest_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    private BlobContentManifest(Name alias, Table<BlobContentManifestRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private BlobContentManifest(Name alias, Table<BlobContentManifestRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.blob_content_manifest</code> table reference
     */
    public BlobContentManifest(String alias) {
        this(DSL.name(alias), BLOB_CONTENT_MANIFEST);
    }

    /**
     * Create an aliased <code>oagi.blob_content_manifest</code> table reference
     */
    public BlobContentManifest(Name alias) {
        this(alias, BLOB_CONTENT_MANIFEST);
    }

    /**
     * Create a <code>oagi.blob_content_manifest</code> table reference
     */
    public BlobContentManifest() {
        this(DSL.name("blob_content_manifest"), null);
    }

    public <O extends Record> BlobContentManifest(Table<O> path, ForeignKey<O, BlobContentManifestRecord> childPath, InverseForeignKey<O, BlobContentManifestRecord> parentPath) {
        super(path, childPath, parentPath, BLOB_CONTENT_MANIFEST);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BlobContentManifestPath extends BlobContentManifest implements Path<BlobContentManifestRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> BlobContentManifestPath(Table<O> path, ForeignKey<O, BlobContentManifestRecord> childPath, InverseForeignKey<O, BlobContentManifestRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BlobContentManifestPath(Name alias, Table<BlobContentManifestRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BlobContentManifestPath as(String alias) {
            return new BlobContentManifestPath(DSL.name(alias), this);
        }

        @Override
        public BlobContentManifestPath as(Name alias) {
            return new BlobContentManifestPath(alias, this);
        }

        @Override
        public BlobContentManifestPath as(Table<?> alias) {
            return new BlobContentManifestPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<BlobContentManifestRecord, ULong> getIdentity() {
        return (Identity<BlobContentManifestRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<BlobContentManifestRecord> getPrimaryKey() {
        return Keys.KEY_BLOB_CONTENT_MANIFEST_PRIMARY;
    }

    @Override
    public List<ForeignKey<BlobContentManifestRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BLOB_CONTENT_MANIFEST_BLOB_CONTENT_ID_FK, Keys.BLOB_CONTENT_MANIFEST_NEXT_BLOB_CONTENT_MANIFEST_ID_FK, Keys.BLOB_CONTENT_MANIFEST_PREV_BLOB_CONTENT_MANIFEST_ID_FK, Keys.BLOB_CONTENT_MANIFEST_RELEASE_ID_FK);
    }

    private transient BlobContentPath _blobContent;

    /**
     * Get the implicit join path to the <code>oagi.blob_content</code> table.
     */
    public BlobContentPath blobContent() {
        if (_blobContent == null)
            _blobContent = new BlobContentPath(this, Keys.BLOB_CONTENT_MANIFEST_BLOB_CONTENT_ID_FK, null);

        return _blobContent;
    }

    private transient BlobContentManifestPath _blobContentManifestNextBlobContentManifestIdFk;

    /**
     * Get the implicit join path to the <code>oagi.blob_content_manifest</code>
     * table, via the
     * <code>blob_content_manifest_next_blob_content_manifest_id_fk</code> key.
     */
    public BlobContentManifestPath blobContentManifestNextBlobContentManifestIdFk() {
        if (_blobContentManifestNextBlobContentManifestIdFk == null)
            _blobContentManifestNextBlobContentManifestIdFk = new BlobContentManifestPath(this, Keys.BLOB_CONTENT_MANIFEST_NEXT_BLOB_CONTENT_MANIFEST_ID_FK, null);

        return _blobContentManifestNextBlobContentManifestIdFk;
    }

    private transient BlobContentManifestPath _blobContentManifestPrevBlobContentManifestIdFk;

    /**
     * Get the implicit join path to the <code>oagi.blob_content_manifest</code>
     * table, via the
     * <code>blob_content_manifest_prev_blob_content_manifest_id_fk</code> key.
     */
    public BlobContentManifestPath blobContentManifestPrevBlobContentManifestIdFk() {
        if (_blobContentManifestPrevBlobContentManifestIdFk == null)
            _blobContentManifestPrevBlobContentManifestIdFk = new BlobContentManifestPath(this, Keys.BLOB_CONTENT_MANIFEST_PREV_BLOB_CONTENT_MANIFEST_ID_FK, null);

        return _blobContentManifestPrevBlobContentManifestIdFk;
    }

    private transient ReleasePath _release;

    /**
     * Get the implicit join path to the <code>oagi.release</code> table.
     */
    public ReleasePath release() {
        if (_release == null)
            _release = new ReleasePath(this, Keys.BLOB_CONTENT_MANIFEST_RELEASE_ID_FK, null);

        return _release;
    }

    private transient ModuleBlobContentManifestPath _moduleBlobContentManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_blob_content_manifest</code> table
     */
    public ModuleBlobContentManifestPath moduleBlobContentManifest() {
        if (_moduleBlobContentManifest == null)
            _moduleBlobContentManifest = new ModuleBlobContentManifestPath(this, null, Keys.MODULE_BLOB_CONTENT_MANIFEST_ACC_MANIFEST_ID_FK.getInverseKey());

        return _moduleBlobContentManifest;
    }

    @Override
    public BlobContentManifest as(String alias) {
        return new BlobContentManifest(DSL.name(alias), this);
    }

    @Override
    public BlobContentManifest as(Name alias) {
        return new BlobContentManifest(alias, this);
    }

    @Override
    public BlobContentManifest as(Table<?> alias) {
        return new BlobContentManifest(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BlobContentManifest rename(String name) {
        return new BlobContentManifest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BlobContentManifest rename(Name name) {
        return new BlobContentManifest(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BlobContentManifest rename(Table<?> name) {
        return new BlobContentManifest(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BlobContentManifest where(Condition condition) {
        return new BlobContentManifest(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BlobContentManifest where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BlobContentManifest where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BlobContentManifest where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BlobContentManifest where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BlobContentManifest where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BlobContentManifest where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BlobContentManifest where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BlobContentManifest whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BlobContentManifest whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
