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
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bbiep.BbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BccManifest.BccManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bccp.BccpPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BccpManifest.BccpManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BccpManifestTag.BccpManifestTagPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.DtManifest.DtManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Log.LogPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.ModuleBccpManifest.ModuleBccpManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Release.ReleasePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Tag.TagPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.BccpManifestRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BccpManifest extends TableImpl<BccpManifestRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.bccp_manifest</code>
     */
    public static final BccpManifest BCCP_MANIFEST = new BccpManifest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BccpManifestRecord> getRecordType() {
        return BccpManifestRecord.class;
    }

    /**
     * The column <code>oagi.bccp_manifest.bccp_manifest_id</code>.
     */
    public final TableField<BccpManifestRecord, ULong> BCCP_MANIFEST_ID = createField(DSL.name("bccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.bccp_manifest.release_id</code>.
     */
    public final TableField<BccpManifestRecord, ULong> RELEASE_ID = createField(DSL.name("release_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.bccp_manifest.bccp_id</code>.
     */
    public final TableField<BccpManifestRecord, ULong> BCCP_ID = createField(DSL.name("bccp_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.bccp_manifest.bdt_manifest_id</code>.
     */
    public final TableField<BccpManifestRecord, ULong> BDT_MANIFEST_ID = createField(DSL.name("bdt_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.bccp_manifest.den</code>. The dictionary entry name
     * of the BCCP. It is derived by PROPERTY_TERM + ". " + REPRESENTATION_TERM.
     */
    public final TableField<BccpManifestRecord, String> DEN = createField(DSL.name("den"), SQLDataType.VARCHAR(249).nullable(false), this, "The dictionary entry name of the BCCP. It is derived by PROPERTY_TERM + \". \" + REPRESENTATION_TERM.");

    /**
     * The column <code>oagi.bccp_manifest.conflict</code>. This indicates that
     * there is a conflict between self and relationship.
     */
    public final TableField<BccpManifestRecord, Byte> CONFLICT = createField(DSL.name("conflict"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "This indicates that there is a conflict between self and relationship.");

    /**
     * The column <code>oagi.bccp_manifest.log_id</code>. A foreign key pointed
     * to a log for the current record.
     */
    public final TableField<BccpManifestRecord, ULong> LOG_ID = createField(DSL.name("log_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "A foreign key pointed to a log for the current record.");

    /**
     * The column <code>oagi.bccp_manifest.replacement_bccp_manifest_id</code>.
     * This refers to a replacement manifest if the record is deprecated.
     */
    public final TableField<BccpManifestRecord, ULong> REPLACEMENT_BCCP_MANIFEST_ID = createField(DSL.name("replacement_bccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "This refers to a replacement manifest if the record is deprecated.");

    /**
     * The column <code>oagi.bccp_manifest.prev_bccp_manifest_id</code>.
     */
    public final TableField<BccpManifestRecord, ULong> PREV_BCCP_MANIFEST_ID = createField(DSL.name("prev_bccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>oagi.bccp_manifest.next_bccp_manifest_id</code>.
     */
    public final TableField<BccpManifestRecord, ULong> NEXT_BCCP_MANIFEST_ID = createField(DSL.name("next_bccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    private BccpManifest(Name alias, Table<BccpManifestRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private BccpManifest(Name alias, Table<BccpManifestRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.bccp_manifest</code> table reference
     */
    public BccpManifest(String alias) {
        this(DSL.name(alias), BCCP_MANIFEST);
    }

    /**
     * Create an aliased <code>oagi.bccp_manifest</code> table reference
     */
    public BccpManifest(Name alias) {
        this(alias, BCCP_MANIFEST);
    }

    /**
     * Create a <code>oagi.bccp_manifest</code> table reference
     */
    public BccpManifest() {
        this(DSL.name("bccp_manifest"), null);
    }

    public <O extends Record> BccpManifest(Table<O> path, ForeignKey<O, BccpManifestRecord> childPath, InverseForeignKey<O, BccpManifestRecord> parentPath) {
        super(path, childPath, parentPath, BCCP_MANIFEST);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BccpManifestPath extends BccpManifest implements Path<BccpManifestRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> BccpManifestPath(Table<O> path, ForeignKey<O, BccpManifestRecord> childPath, InverseForeignKey<O, BccpManifestRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BccpManifestPath(Name alias, Table<BccpManifestRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BccpManifestPath as(String alias) {
            return new BccpManifestPath(DSL.name(alias), this);
        }

        @Override
        public BccpManifestPath as(Name alias) {
            return new BccpManifestPath(alias, this);
        }

        @Override
        public BccpManifestPath as(Table<?> alias) {
            return new BccpManifestPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<BccpManifestRecord, ULong> getIdentity() {
        return (Identity<BccpManifestRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<BccpManifestRecord> getPrimaryKey() {
        return Keys.KEY_BCCP_MANIFEST_PRIMARY;
    }

    @Override
    public List<ForeignKey<BccpManifestRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BCCP_MANIFEST_BCCP_ID_FK, Keys.BCCP_MANIFEST_BDT_MANIFEST_ID_FK, Keys.BCCP_MANIFEST_LOG_ID_FK, Keys.BCCP_MANIFEST_NEXT_BCCP_MANIFEST_ID_FK, Keys.BCCP_MANIFEST_PREV_BCCP_MANIFEST_ID_FK, Keys.BCCP_MANIFEST_RELEASE_ID_FK, Keys.BCCP_REPLACEMENT_BCCP_MANIFEST_ID_FK);
    }

    private transient BccpPath _bccp;

    /**
     * Get the implicit join path to the <code>oagi.bccp</code> table.
     */
    public BccpPath bccp() {
        if (_bccp == null)
            _bccp = new BccpPath(this, Keys.BCCP_MANIFEST_BCCP_ID_FK, null);

        return _bccp;
    }

    private transient DtManifestPath _dtManifest;

    /**
     * Get the implicit join path to the <code>oagi.dt_manifest</code> table.
     */
    public DtManifestPath dtManifest() {
        if (_dtManifest == null)
            _dtManifest = new DtManifestPath(this, Keys.BCCP_MANIFEST_BDT_MANIFEST_ID_FK, null);

        return _dtManifest;
    }

    private transient LogPath _log;

    /**
     * Get the implicit join path to the <code>oagi.log</code> table.
     */
    public LogPath log() {
        if (_log == null)
            _log = new LogPath(this, Keys.BCCP_MANIFEST_LOG_ID_FK, null);

        return _log;
    }

    private transient BccpManifestPath _bccpManifestNextBccpManifestIdFk;

    /**
     * Get the implicit join path to the <code>oagi.bccp_manifest</code> table,
     * via the <code>bccp_manifest_next_bccp_manifest_id_fk</code> key.
     */
    public BccpManifestPath bccpManifestNextBccpManifestIdFk() {
        if (_bccpManifestNextBccpManifestIdFk == null)
            _bccpManifestNextBccpManifestIdFk = new BccpManifestPath(this, Keys.BCCP_MANIFEST_NEXT_BCCP_MANIFEST_ID_FK, null);

        return _bccpManifestNextBccpManifestIdFk;
    }

    private transient BccpManifestPath _bccpManifestPrevBccpManifestIdFk;

    /**
     * Get the implicit join path to the <code>oagi.bccp_manifest</code> table,
     * via the <code>bccp_manifest_prev_bccp_manifest_id_fk</code> key.
     */
    public BccpManifestPath bccpManifestPrevBccpManifestIdFk() {
        if (_bccpManifestPrevBccpManifestIdFk == null)
            _bccpManifestPrevBccpManifestIdFk = new BccpManifestPath(this, Keys.BCCP_MANIFEST_PREV_BCCP_MANIFEST_ID_FK, null);

        return _bccpManifestPrevBccpManifestIdFk;
    }

    private transient ReleasePath _release;

    /**
     * Get the implicit join path to the <code>oagi.release</code> table.
     */
    public ReleasePath release() {
        if (_release == null)
            _release = new ReleasePath(this, Keys.BCCP_MANIFEST_RELEASE_ID_FK, null);

        return _release;
    }

    private transient BccpManifestPath _bccpReplacementBccpManifestIdFk;

    /**
     * Get the implicit join path to the <code>oagi.bccp_manifest</code> table,
     * via the <code>bccp_replacement_bccp_manifest_id_fk</code> key.
     */
    public BccpManifestPath bccpReplacementBccpManifestIdFk() {
        if (_bccpReplacementBccpManifestIdFk == null)
            _bccpReplacementBccpManifestIdFk = new BccpManifestPath(this, Keys.BCCP_REPLACEMENT_BCCP_MANIFEST_ID_FK, null);

        return _bccpReplacementBccpManifestIdFk;
    }

    private transient BbiepPath _bbiep;

    /**
     * Get the implicit to-many join path to the <code>oagi.bbiep</code> table
     */
    public BbiepPath bbiep() {
        if (_bbiep == null)
            _bbiep = new BbiepPath(this, null, Keys.BBIEP_BASED_BCCP_MANIFEST_ID_FK.getInverseKey());

        return _bbiep;
    }

    private transient BccManifestPath _bccManifest;

    /**
     * Get the implicit to-many join path to the <code>oagi.bcc_manifest</code>
     * table
     */
    public BccManifestPath bccManifest() {
        if (_bccManifest == null)
            _bccManifest = new BccManifestPath(this, null, Keys.BCC_MANIFEST_TO_BCCP_MANIFEST_ID_FK.getInverseKey());

        return _bccManifest;
    }

    private transient BccpManifestTagPath _bccpManifestTag;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.bccp_manifest_tag</code> table
     */
    public BccpManifestTagPath bccpManifestTag() {
        if (_bccpManifestTag == null)
            _bccpManifestTag = new BccpManifestTagPath(this, null, Keys.BCCP_MANIFEST_TAG_BCCP_MANIFEST_ID_FK.getInverseKey());

        return _bccpManifestTag;
    }

    private transient ModuleBccpManifestPath _moduleBccpManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.module_bccp_manifest</code> table
     */
    public ModuleBccpManifestPath moduleBccpManifest() {
        if (_moduleBccpManifest == null)
            _moduleBccpManifest = new ModuleBccpManifestPath(this, null, Keys.MODULE_BCCP_MANIFEST_BCCP_MANIFEST_ID_FK.getInverseKey());

        return _moduleBccpManifest;
    }

    /**
     * Get the implicit many-to-many join path to the <code>oagi.tag</code>
     * table
     */
    public TagPath tag() {
        return bccpManifestTag().tag();
    }

    @Override
    public BccpManifest as(String alias) {
        return new BccpManifest(DSL.name(alias), this);
    }

    @Override
    public BccpManifest as(Name alias) {
        return new BccpManifest(alias, this);
    }

    @Override
    public BccpManifest as(Table<?> alias) {
        return new BccpManifest(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BccpManifest rename(String name) {
        return new BccpManifest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BccpManifest rename(Name name) {
        return new BccpManifest(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BccpManifest rename(Table<?> name) {
        return new BccpManifest(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccpManifest where(Condition condition) {
        return new BccpManifest(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccpManifest where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccpManifest where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccpManifest where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccpManifest where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccpManifest where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccpManifest where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BccpManifest where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccpManifest whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BccpManifest whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
