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
import org.oagi.score.repo.api.impl.jooq.entity.tables.Abie.AbiePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Asbie.AsbiePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Asbiep.AsbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bbie.BbiePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BbieSc.BbieScPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bbiep.BbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BiePackageTopLevelAsbiep.BiePackageTopLevelAsbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BieUserExtRevision.BieUserExtRevisionPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BizCtx.BizCtxPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BizCtxAssignment.BizCtxAssignmentPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasMessageBody.OasMessageBodyPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasRequest.OasRequestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasResponse.OasResponsePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Release.ReleasePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.TopLevelAsbiep.TopLevelAsbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.TopLevelAsbiepRecord;


/**
 * This table indexes the ASBIEP which is a top-level ASBIEP. This table and the
 * owner_top_level_asbiep_id column in all BIE tables allow all related BIEs to
 * be retrieved all at once speeding up the profile BOD transactions.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TopLevelAsbiep extends TableImpl<TopLevelAsbiepRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.top_level_asbiep</code>
     */
    public static final TopLevelAsbiep TOP_LEVEL_ASBIEP = new TopLevelAsbiep();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TopLevelAsbiepRecord> getRecordType() {
        return TopLevelAsbiepRecord.class;
    }

    /**
     * The column <code>oagi.top_level_asbiep.top_level_asbiep_id</code>. A
     * internal, primary database key of an top-level ASBIEP.
     */
    public final TableField<TopLevelAsbiepRecord, ULong> TOP_LEVEL_ASBIEP_ID = createField(DSL.name("top_level_asbiep_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "A internal, primary database key of an top-level ASBIEP.");

    /**
     * The column <code>oagi.top_level_asbiep.asbiep_id</code>. Foreign key to
     * the ASBIEP table pointing to a record which is a top-level ASBIEP.
     */
    public final TableField<TopLevelAsbiepRecord, ULong> ASBIEP_ID = createField(DSL.name("asbiep_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the ASBIEP table pointing to a record which is a top-level ASBIEP.");

    /**
     * The column <code>oagi.top_level_asbiep.owner_user_id</code>.
     */
    public final TableField<TopLevelAsbiepRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.top_level_asbiep.last_update_timestamp</code>. The
     * timestamp when among all related bie records was last updated.
     */
    public final TableField<TopLevelAsbiepRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("current_timestamp(6)"), SQLDataType.LOCALDATETIME)), this, "The timestamp when among all related bie records was last updated.");

    /**
     * The column <code>oagi.top_level_asbiep.last_updated_by</code>. A foreign
     * key referring to the last user who has updated any related bie records.
     */
    public final TableField<TopLevelAsbiepRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key referring to the last user who has updated any related bie records.");

    /**
     * The column <code>oagi.top_level_asbiep.release_id</code>. Foreign key to
     * the RELEASE table. It identifies the release, for which this module is
     * associated.
     */
    public final TableField<TopLevelAsbiepRecord, ULong> RELEASE_ID = createField(DSL.name("release_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the RELEASE table. It identifies the release, for which this module is associated.");

    /**
     * The column <code>oagi.top_level_asbiep.version</code>. This column hold a
     * version number assigned by the user. This column is only used by the
     * top-level ASBIEP. No format of version is enforced.
     */
    public final TableField<TopLevelAsbiepRecord, String> VERSION = createField(DSL.name("version"), SQLDataType.VARCHAR(45).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "This column hold a version number assigned by the user. This column is only used by the top-level ASBIEP. No format of version is enforced.");

    /**
     * The column <code>oagi.top_level_asbiep.status</code>. This is different
     * from the STATE column which is CRUD life cycle of an entity. The use case
     * for this is to allow the user to indicate the usage status of a top-level
     * ASBIEP (a profile BOD). An integration architect can use this column.
     * Example values are ?Prototype?, ?Test?, and ?Production?. Only the
     * top-level ASBIEP can use this field.
     */
    public final TableField<TopLevelAsbiepRecord, String> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR(45).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "This is different from the STATE column which is CRUD life cycle of an entity. The use case for this is to allow the user to indicate the usage status of a top-level ASBIEP (a profile BOD). An integration architect can use this column. Example values are ?Prototype?, ?Test?, and ?Production?. Only the top-level ASBIEP can use this field.");

    /**
     * The column <code>oagi.top_level_asbiep.state</code>.
     */
    public final TableField<TopLevelAsbiepRecord, String> STATE = createField(DSL.name("state"), SQLDataType.VARCHAR(20).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.top_level_asbiep.inverse_mode</code>. If this is
     * true, all BIEs not edited by users under this TOP_LEVEL_ASBIEP will be
     * treated as used BIEs.
     */
    public final TableField<TopLevelAsbiepRecord, Byte> INVERSE_MODE = createField(DSL.name("inverse_mode"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "If this is true, all BIEs not edited by users under this TOP_LEVEL_ASBIEP will be treated as used BIEs.");

    /**
     * The column <code>oagi.top_level_asbiep.is_deprecated</code>. Indicates
     * whether the TOP_LEVEL_ASBIEP is deprecated.
     */
    public final TableField<TopLevelAsbiepRecord, Byte> IS_DEPRECATED = createField(DSL.name("is_deprecated"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "Indicates whether the TOP_LEVEL_ASBIEP is deprecated.");

    /**
     * The column <code>oagi.top_level_asbiep.deprecated_reason</code>. The
     * reason for the deprecation of the TOP_LEVEL_ASBIEP.
     */
    public final TableField<TopLevelAsbiepRecord, String> DEPRECATED_REASON = createField(DSL.name("deprecated_reason"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "The reason for the deprecation of the TOP_LEVEL_ASBIEP.");

    /**
     * The column <code>oagi.top_level_asbiep.deprecated_remark</code>. The
     * remark for the deprecation of the TOP_LEVEL_ASBIEP.
     */
    public final TableField<TopLevelAsbiepRecord, String> DEPRECATED_REMARK = createField(DSL.name("deprecated_remark"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "The remark for the deprecation of the TOP_LEVEL_ASBIEP.");

    /**
     * The column <code>oagi.top_level_asbiep.source_top_level_asbiep_id</code>.
     * A foreign key referring to the source TOP_LEVEL_ASBIEP_ID which has
     * linked to this record.
     */
    public final TableField<TopLevelAsbiepRecord, ULong> SOURCE_TOP_LEVEL_ASBIEP_ID = createField(DSL.name("source_top_level_asbiep_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "A foreign key referring to the source TOP_LEVEL_ASBIEP_ID which has linked to this record.");

    /**
     * The column <code>oagi.top_level_asbiep.source_action</code>. An action
     * that had used to create a reference from the source (e.g., 'Copy' or
     * 'Uplift'.)
     */
    public final TableField<TopLevelAsbiepRecord, String> SOURCE_ACTION = createField(DSL.name("source_action"), SQLDataType.VARCHAR(20).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "An action that had used to create a reference from the source (e.g., 'Copy' or 'Uplift'.)");

    /**
     * The column <code>oagi.top_level_asbiep.source_timestamp</code>. A
     * timestamp when a source reference had been made.
     */
    public final TableField<TopLevelAsbiepRecord, LocalDateTime> SOURCE_TIMESTAMP = createField(DSL.name("source_timestamp"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.LOCALDATETIME)), this, "A timestamp when a source reference had been made.");

    private TopLevelAsbiep(Name alias, Table<TopLevelAsbiepRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TopLevelAsbiep(Name alias, Table<TopLevelAsbiepRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("This table indexes the ASBIEP which is a top-level ASBIEP. This table and the owner_top_level_asbiep_id column in all BIE tables allow all related BIEs to be retrieved all at once speeding up the profile BOD transactions."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.top_level_asbiep</code> table reference
     */
    public TopLevelAsbiep(String alias) {
        this(DSL.name(alias), TOP_LEVEL_ASBIEP);
    }

    /**
     * Create an aliased <code>oagi.top_level_asbiep</code> table reference
     */
    public TopLevelAsbiep(Name alias) {
        this(alias, TOP_LEVEL_ASBIEP);
    }

    /**
     * Create a <code>oagi.top_level_asbiep</code> table reference
     */
    public TopLevelAsbiep() {
        this(DSL.name("top_level_asbiep"), null);
    }

    public <O extends Record> TopLevelAsbiep(Table<O> path, ForeignKey<O, TopLevelAsbiepRecord> childPath, InverseForeignKey<O, TopLevelAsbiepRecord> parentPath) {
        super(path, childPath, parentPath, TOP_LEVEL_ASBIEP);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TopLevelAsbiepPath extends TopLevelAsbiep implements Path<TopLevelAsbiepRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TopLevelAsbiepPath(Table<O> path, ForeignKey<O, TopLevelAsbiepRecord> childPath, InverseForeignKey<O, TopLevelAsbiepRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TopLevelAsbiepPath(Name alias, Table<TopLevelAsbiepRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TopLevelAsbiepPath as(String alias) {
            return new TopLevelAsbiepPath(DSL.name(alias), this);
        }

        @Override
        public TopLevelAsbiepPath as(Name alias) {
            return new TopLevelAsbiepPath(alias, this);
        }

        @Override
        public TopLevelAsbiepPath as(Table<?> alias) {
            return new TopLevelAsbiepPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<TopLevelAsbiepRecord, ULong> getIdentity() {
        return (Identity<TopLevelAsbiepRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<TopLevelAsbiepRecord> getPrimaryKey() {
        return Keys.KEY_TOP_LEVEL_ASBIEP_PRIMARY;
    }

    @Override
    public List<ForeignKey<TopLevelAsbiepRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TOP_LEVEL_ASBIEP_ASBIEP_ID_FK, Keys.TOP_LEVEL_ASBIEP_LAST_UPDATED_BY_FK, Keys.TOP_LEVEL_ASBIEP_OWNER_USER_ID_FK, Keys.TOP_LEVEL_ASBIEP_RELEASE_ID_FK, Keys.TOP_LEVEL_ASBIEP_SOURCE_TOP_LEVEL_ASBIEP_ID_FK);
    }

    private transient AsbiepPath _asbiep;

    /**
     * Get the implicit join path to the <code>oagi.asbiep</code> table.
     */
    public AsbiepPath asbiep() {
        if (_asbiep == null)
            _asbiep = new AsbiepPath(this, Keys.TOP_LEVEL_ASBIEP_ASBIEP_ID_FK, null);

        return _asbiep;
    }

    private transient AppUserPath _topLevelAsbiepLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>top_level_asbiep_last_updated_by_fk</code> key.
     */
    public AppUserPath topLevelAsbiepLastUpdatedByFk() {
        if (_topLevelAsbiepLastUpdatedByFk == null)
            _topLevelAsbiepLastUpdatedByFk = new AppUserPath(this, Keys.TOP_LEVEL_ASBIEP_LAST_UPDATED_BY_FK, null);

        return _topLevelAsbiepLastUpdatedByFk;
    }

    private transient AppUserPath _topLevelAsbiepOwnerUserIdFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>top_level_asbiep_owner_user_id_fk</code> key.
     */
    public AppUserPath topLevelAsbiepOwnerUserIdFk() {
        if (_topLevelAsbiepOwnerUserIdFk == null)
            _topLevelAsbiepOwnerUserIdFk = new AppUserPath(this, Keys.TOP_LEVEL_ASBIEP_OWNER_USER_ID_FK, null);

        return _topLevelAsbiepOwnerUserIdFk;
    }

    private transient ReleasePath _release;

    /**
     * Get the implicit join path to the <code>oagi.release</code> table.
     */
    public ReleasePath release() {
        if (_release == null)
            _release = new ReleasePath(this, Keys.TOP_LEVEL_ASBIEP_RELEASE_ID_FK, null);

        return _release;
    }

    private transient TopLevelAsbiepPath _topLevelAsbiep;

    /**
     * Get the implicit join path to the <code>oagi.top_level_asbiep</code>
     * table.
     */
    public TopLevelAsbiepPath topLevelAsbiep() {
        if (_topLevelAsbiep == null)
            _topLevelAsbiep = new TopLevelAsbiepPath(this, Keys.TOP_LEVEL_ASBIEP_SOURCE_TOP_LEVEL_ASBIEP_ID_FK, null);

        return _topLevelAsbiep;
    }

    private transient AbiePath _abie;

    /**
     * Get the implicit to-many join path to the <code>oagi.abie</code> table
     */
    public AbiePath abie() {
        if (_abie == null)
            _abie = new AbiePath(this, null, Keys.ABIE_OWNER_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _abie;
    }

    private transient AsbiePath _asbie;

    /**
     * Get the implicit to-many join path to the <code>oagi.asbie</code> table
     */
    public AsbiePath asbie() {
        if (_asbie == null)
            _asbie = new AsbiePath(this, null, Keys.ASBIE_OWNER_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _asbie;
    }

    private transient BbiePath _bbie;

    /**
     * Get the implicit to-many join path to the <code>oagi.bbie</code> table
     */
    public BbiePath bbie() {
        if (_bbie == null)
            _bbie = new BbiePath(this, null, Keys.BBIE_OWNER_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _bbie;
    }

    private transient BbieScPath _bbieSc;

    /**
     * Get the implicit to-many join path to the <code>oagi.bbie_sc</code> table
     */
    public BbieScPath bbieSc() {
        if (_bbieSc == null)
            _bbieSc = new BbieScPath(this, null, Keys.BBIE_SC_OWNER_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _bbieSc;
    }

    private transient BbiepPath _bbiep;

    /**
     * Get the implicit to-many join path to the <code>oagi.bbiep</code> table
     */
    public BbiepPath bbiep() {
        if (_bbiep == null)
            _bbiep = new BbiepPath(this, null, Keys.BBIEP_OWNER_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _bbiep;
    }

    private transient BiePackageTopLevelAsbiepPath _biePackageTopLevelAsbiep;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.bie_package_top_level_asbiep</code> table
     */
    public BiePackageTopLevelAsbiepPath biePackageTopLevelAsbiep() {
        if (_biePackageTopLevelAsbiep == null)
            _biePackageTopLevelAsbiep = new BiePackageTopLevelAsbiepPath(this, null, Keys.BIE_PACKAGE_TOP_LEVEL_ASBIEP_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _biePackageTopLevelAsbiep;
    }

    private transient BieUserExtRevisionPath _bieUserExtRevision;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.bie_user_ext_revision</code> table
     */
    public BieUserExtRevisionPath bieUserExtRevision() {
        if (_bieUserExtRevision == null)
            _bieUserExtRevision = new BieUserExtRevisionPath(this, null, Keys.BIE_USER_EXT_REVISION_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _bieUserExtRevision;
    }

    private transient BizCtxAssignmentPath _bizCtxAssignment;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.biz_ctx_assignment</code> table
     */
    public BizCtxAssignmentPath bizCtxAssignment() {
        if (_bizCtxAssignment == null)
            _bizCtxAssignment = new BizCtxAssignmentPath(this, null, Keys.BIZ_CTX_ASSIGNMENT_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _bizCtxAssignment;
    }

    private transient OasMessageBodyPath _oasMessageBody;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.oas_message_body</code> table
     */
    public OasMessageBodyPath oasMessageBody() {
        if (_oasMessageBody == null)
            _oasMessageBody = new OasMessageBodyPath(this, null, Keys.OAS_MESSAGE_BODY_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _oasMessageBody;
    }

    private transient OasRequestPath _oasRequestMetaHeaderTopLevelAsbiepIdFk;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_request</code>
     * table, via the
     * <code>oas_request_meta_header_top_level_asbiep_id_fk</code> key
     */
    public OasRequestPath oasRequestMetaHeaderTopLevelAsbiepIdFk() {
        if (_oasRequestMetaHeaderTopLevelAsbiepIdFk == null)
            _oasRequestMetaHeaderTopLevelAsbiepIdFk = new OasRequestPath(this, null, Keys.OAS_REQUEST_META_HEADER_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _oasRequestMetaHeaderTopLevelAsbiepIdFk;
    }

    private transient OasRequestPath _oasRequestPaginationTopLevelAsbiepIdFk;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_request</code>
     * table, via the <code>oas_request_pagination_top_level_asbiep_id_fk</code>
     * key
     */
    public OasRequestPath oasRequestPaginationTopLevelAsbiepIdFk() {
        if (_oasRequestPaginationTopLevelAsbiepIdFk == null)
            _oasRequestPaginationTopLevelAsbiepIdFk = new OasRequestPath(this, null, Keys.OAS_REQUEST_PAGINATION_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _oasRequestPaginationTopLevelAsbiepIdFk;
    }

    private transient OasResponsePath _oasResponseMetaHeaderTopLevelAsbiepIdFk;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_response</code>
     * table, via the
     * <code>oas_response_meta_header_top_level_asbiep_id_fk</code> key
     */
    public OasResponsePath oasResponseMetaHeaderTopLevelAsbiepIdFk() {
        if (_oasResponseMetaHeaderTopLevelAsbiepIdFk == null)
            _oasResponseMetaHeaderTopLevelAsbiepIdFk = new OasResponsePath(this, null, Keys.OAS_RESPONSE_META_HEADER_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _oasResponseMetaHeaderTopLevelAsbiepIdFk;
    }

    private transient OasResponsePath _oasResponsePaginationTopLevelAsbiepIdFk;

    /**
     * Get the implicit to-many join path to the <code>oagi.oas_response</code>
     * table, via the
     * <code>oas_response_pagination_top_level_asbiep_id_fk</code> key
     */
    public OasResponsePath oasResponsePaginationTopLevelAsbiepIdFk() {
        if (_oasResponsePaginationTopLevelAsbiepIdFk == null)
            _oasResponsePaginationTopLevelAsbiepIdFk = new OasResponsePath(this, null, Keys.OAS_RESPONSE_PAGINATION_TOP_LEVEL_ASBIEP_ID_FK.getInverseKey());

        return _oasResponsePaginationTopLevelAsbiepIdFk;
    }

    /**
     * Get the implicit many-to-many join path to the <code>oagi.biz_ctx</code>
     * table
     */
    public BizCtxPath bizCtx() {
        return bizCtxAssignment().bizCtx();
    }

    @Override
    public TopLevelAsbiep as(String alias) {
        return new TopLevelAsbiep(DSL.name(alias), this);
    }

    @Override
    public TopLevelAsbiep as(Name alias) {
        return new TopLevelAsbiep(alias, this);
    }

    @Override
    public TopLevelAsbiep as(Table<?> alias) {
        return new TopLevelAsbiep(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TopLevelAsbiep rename(String name) {
        return new TopLevelAsbiep(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TopLevelAsbiep rename(Name name) {
        return new TopLevelAsbiep(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TopLevelAsbiep rename(Table<?> name) {
        return new TopLevelAsbiep(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TopLevelAsbiep where(Condition condition) {
        return new TopLevelAsbiep(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TopLevelAsbiep where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TopLevelAsbiep where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TopLevelAsbiep where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TopLevelAsbiep where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TopLevelAsbiep where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TopLevelAsbiep where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TopLevelAsbiep where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TopLevelAsbiep whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TopLevelAsbiep whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
