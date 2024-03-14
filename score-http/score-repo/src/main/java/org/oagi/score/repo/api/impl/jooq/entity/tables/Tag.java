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
import org.oagi.score.repo.api.impl.jooq.entity.tables.AccManifest.AccManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AccManifestTag.AccManifestTagPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AsccpManifest.AsccpManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AsccpManifestTag.AsccpManifestTagPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BccpManifest.BccpManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BccpManifestTag.BccpManifestTagPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.DtManifest.DtManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.DtManifestTag.DtManifestTagPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.TagRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tag extends TableImpl<TagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.tag</code>
     */
    public static final Tag TAG = new Tag();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TagRecord> getRecordType() {
        return TagRecord.class;
    }

    /**
     * The column <code>oagi.tag.tag_id</code>. An internal, primary database
     * key of a tag record.
     */
    public final TableField<TagRecord, ULong> TAG_ID = createField(DSL.name("tag_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "An internal, primary database key of a tag record.");

    /**
     * The column <code>oagi.tag.name</code>. The name of the tag.
     */
    public final TableField<TagRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "The name of the tag.");

    /**
     * The column <code>oagi.tag.description</code>. The description of the tag.
     */
    public final TableField<TagRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "The description of the tag.");

    /**
     * The column <code>oagi.tag.text_color</code>. The text color of the tag.
     */
    public final TableField<TagRecord, String> TEXT_COLOR = createField(DSL.name("text_color"), SQLDataType.VARCHAR(10).nullable(false), this, "The text color of the tag.");

    /**
     * The column <code>oagi.tag.background_color</code>. The background color
     * of the tag.
     */
    public final TableField<TagRecord, String> BACKGROUND_COLOR = createField(DSL.name("background_color"), SQLDataType.VARCHAR(10).nullable(false), this, "The background color of the tag.");

    /**
     * The column <code>oagi.tag.created_by</code>. A foreign key referring to
     * the user who creates the tag record.
     */
    public final TableField<TagRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key referring to the user who creates the tag record.");

    /**
     * The column <code>oagi.tag.last_updated_by</code>. A foreign key referring
     * to the last user who has updated the tag record.
     */
    public final TableField<TagRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key referring to the last user who has updated the tag record.");

    /**
     * The column <code>oagi.tag.creation_timestamp</code>. Timestamp when the
     * tag record was first created.
     */
    public final TableField<TagRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the tag record was first created.");

    /**
     * The column <code>oagi.tag.last_update_timestamp</code>. The timestamp
     * when the tag was last updated.
     */
    public final TableField<TagRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the tag was last updated.");

    private Tag(Name alias, Table<TagRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Tag(Name alias, Table<TagRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.tag</code> table reference
     */
    public Tag(String alias) {
        this(DSL.name(alias), TAG);
    }

    /**
     * Create an aliased <code>oagi.tag</code> table reference
     */
    public Tag(Name alias) {
        this(alias, TAG);
    }

    /**
     * Create a <code>oagi.tag</code> table reference
     */
    public Tag() {
        this(DSL.name("tag"), null);
    }

    public <O extends Record> Tag(Table<O> path, ForeignKey<O, TagRecord> childPath, InverseForeignKey<O, TagRecord> parentPath) {
        super(path, childPath, parentPath, TAG);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TagPath extends Tag implements Path<TagRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TagPath(Table<O> path, ForeignKey<O, TagRecord> childPath, InverseForeignKey<O, TagRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TagPath(Name alias, Table<TagRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TagPath as(String alias) {
            return new TagPath(DSL.name(alias), this);
        }

        @Override
        public TagPath as(Name alias) {
            return new TagPath(alias, this);
        }

        @Override
        public TagPath as(Table<?> alias) {
            return new TagPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<TagRecord, ULong> getIdentity() {
        return (Identity<TagRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<TagRecord> getPrimaryKey() {
        return Keys.KEY_TAG_PRIMARY;
    }

    @Override
    public List<ForeignKey<TagRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TAG_CREATED_BY_FK, Keys.TAG_LAST_UPDATED_BY_FK);
    }

    private transient AppUserPath _tagCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>tag_created_by_fk</code> key.
     */
    public AppUserPath tagCreatedByFk() {
        if (_tagCreatedByFk == null)
            _tagCreatedByFk = new AppUserPath(this, Keys.TAG_CREATED_BY_FK, null);

        return _tagCreatedByFk;
    }

    private transient AppUserPath _tagLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>tag_last_updated_by_fk</code> key.
     */
    public AppUserPath tagLastUpdatedByFk() {
        if (_tagLastUpdatedByFk == null)
            _tagLastUpdatedByFk = new AppUserPath(this, Keys.TAG_LAST_UPDATED_BY_FK, null);

        return _tagLastUpdatedByFk;
    }

    private transient AccManifestTagPath _accManifestTag;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.acc_manifest_tag</code> table
     */
    public AccManifestTagPath accManifestTag() {
        if (_accManifestTag == null)
            _accManifestTag = new AccManifestTagPath(this, null, Keys.ACC_MANIFEST_TAG_TAG_ID_FK.getInverseKey());

        return _accManifestTag;
    }

    private transient AsccpManifestTagPath _asccpManifestTag;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.asccp_manifest_tag</code> table
     */
    public AsccpManifestTagPath asccpManifestTag() {
        if (_asccpManifestTag == null)
            _asccpManifestTag = new AsccpManifestTagPath(this, null, Keys.ASCCP_MANIFEST_TAG_TAG_ID_FK.getInverseKey());

        return _asccpManifestTag;
    }

    private transient BccpManifestTagPath _bccpManifestTag;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.bccp_manifest_tag</code> table
     */
    public BccpManifestTagPath bccpManifestTag() {
        if (_bccpManifestTag == null)
            _bccpManifestTag = new BccpManifestTagPath(this, null, Keys.BCCP_MANIFEST_TAG_TAG_ID_FK.getInverseKey());

        return _bccpManifestTag;
    }

    private transient DtManifestTagPath _dtManifestTag;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.dt_manifest_tag</code> table
     */
    public DtManifestTagPath dtManifestTag() {
        if (_dtManifestTag == null)
            _dtManifestTag = new DtManifestTagPath(this, null, Keys.DT_MANIFEST_TAG_TAG_ID_FK.getInverseKey());

        return _dtManifestTag;
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>oagi.acc_manifest</code> table
     */
    public AccManifestPath accManifest() {
        return accManifestTag().accManifest();
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>oagi.asccp_manifest</code> table
     */
    public AsccpManifestPath asccpManifest() {
        return asccpManifestTag().asccpManifest();
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>oagi.bccp_manifest</code> table
     */
    public BccpManifestPath bccpManifest() {
        return bccpManifestTag().bccpManifest();
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>oagi.dt_manifest</code> table
     */
    public DtManifestPath dtManifest() {
        return dtManifestTag().dtManifest();
    }

    @Override
    public Tag as(String alias) {
        return new Tag(DSL.name(alias), this);
    }

    @Override
    public Tag as(Name alias) {
        return new Tag(alias, this);
    }

    @Override
    public Tag as(Table<?> alias) {
        return new Tag(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Tag rename(String name) {
        return new Tag(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Tag rename(Name name) {
        return new Tag(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Tag rename(Table<?> name) {
        return new Tag(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tag where(Condition condition) {
        return new Tag(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tag where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tag where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tag where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tag where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tag where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tag where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Tag where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tag whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Tag whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
