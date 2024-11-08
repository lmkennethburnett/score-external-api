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
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CodeList.CodeListPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CodeListManifest.CodeListManifestPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CodeListValue.CodeListValuePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CtxScheme.CtxSchemePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Namespace.NamespacePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.CodeListRecord;


/**
 * This table stores information about a code list. When a code list is derived
 * from another code list, the whole set of code values belonging to the based
 * code list will be copied.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CodeList extends TableImpl<CodeListRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.code_list</code>
     */
    public static final CodeList CODE_LIST = new CodeList();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CodeListRecord> getRecordType() {
        return CodeListRecord.class;
    }

    /**
     * The column <code>oagi.code_list.code_list_id</code>. Internal, primary
     * database key.
     */
    public final TableField<CodeListRecord, ULong> CODE_LIST_ID = createField(DSL.name("code_list_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Internal, primary database key.");

    /**
     * The column <code>oagi.code_list.guid</code>. A globally unique identifier
     * (GUID).
     */
    public final TableField<CodeListRecord, String> GUID = createField(DSL.name("guid"), SQLDataType.CHAR(32).nullable(false), this, "A globally unique identifier (GUID).");

    /**
     * The column <code>oagi.code_list.enum_type_guid</code>. In the OAGIS Model
     * XML schema, a type, which keeps all the enumerated values, is  defined
     * separately from the type that represents a code list. This only applies
     * to some code lists. When that is the case, this column stores the GUID of
     * that enumeration type.
     */
    public final TableField<CodeListRecord, String> ENUM_TYPE_GUID = createField(DSL.name("enum_type_guid"), SQLDataType.VARCHAR(41).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "In the OAGIS Model XML schema, a type, which keeps all the enumerated values, is  defined separately from the type that represents a code list. This only applies to some code lists. When that is the case, this column stores the GUID of that enumeration type.");

    /**
     * The column <code>oagi.code_list.name</code>. Name of the code list.
     */
    public final TableField<CodeListRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "Name of the code list.");

    /**
     * The column <code>oagi.code_list.list_id</code>. External identifier.
     */
    public final TableField<CodeListRecord, String> LIST_ID = createField(DSL.name("list_id"), SQLDataType.VARCHAR(100).nullable(false), this, "External identifier.");

    /**
     * The column <code>oagi.code_list.version_id</code>. Code list version
     * number.
     */
    public final TableField<CodeListRecord, String> VERSION_ID = createField(DSL.name("version_id"), SQLDataType.VARCHAR(100).nullable(false), this, "Code list version number.");

    /**
     * The column <code>oagi.code_list.definition</code>. Description of the
     * code list.
     */
    public final TableField<CodeListRecord, String> DEFINITION = createField(DSL.name("definition"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "Description of the code list.");

    /**
     * The column <code>oagi.code_list.remark</code>. Usage information about
     * the code list.
     */
    public final TableField<CodeListRecord, String> REMARK = createField(DSL.name("remark"), SQLDataType.VARCHAR(225).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "Usage information about the code list.");

    /**
     * The column <code>oagi.code_list.definition_source</code>. This is
     * typically a URL which indicates the source of the code list's DEFINITION.
     */
    public final TableField<CodeListRecord, String> DEFINITION_SOURCE = createField(DSL.name("definition_source"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "This is typically a URL which indicates the source of the code list's DEFINITION.");

    /**
     * The column <code>oagi.code_list.namespace_id</code>. Foreign key to the
     * NAMESPACE table. This is the namespace to which the entity belongs. This
     * namespace column is primarily used in the case the component is a user's
     * component because there is also a namespace assigned at the release
     * level.
     */
    public final TableField<CodeListRecord, ULong> NAMESPACE_ID = createField(DSL.name("namespace_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the NAMESPACE table. This is the namespace to which the entity belongs. This namespace column is primarily used in the case the component is a user's component because there is also a namespace assigned at the release level.");

    /**
     * The column <code>oagi.code_list.based_code_list_id</code>. This is a
     * foreign key to the CODE_LIST table itself. This identifies the code list
     * on which this code list is based, if any. The derivation may be
     * restriction and/or extension.
     */
    public final TableField<CodeListRecord, ULong> BASED_CODE_LIST_ID = createField(DSL.name("based_code_list_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "This is a foreign key to the CODE_LIST table itself. This identifies the code list on which this code list is based, if any. The derivation may be restriction and/or extension.");

    /**
     * The column <code>oagi.code_list.extensible_indicator</code>. This is a
     * flag to indicate whether the code list is final and shall not be further
     * derived.
     */
    public final TableField<CodeListRecord, Byte> EXTENSIBLE_INDICATOR = createField(DSL.name("extensible_indicator"), SQLDataType.TINYINT.nullable(false), this, "This is a flag to indicate whether the code list is final and shall not be further derived.");

    /**
     * The column <code>oagi.code_list.is_deprecated</code>. Indicates whether
     * the code list is deprecated and should not be reused (i.e., no new
     * reference to this record should be allowed).
     */
    public final TableField<CodeListRecord, Byte> IS_DEPRECATED = createField(DSL.name("is_deprecated"), SQLDataType.TINYINT.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "Indicates whether the code list is deprecated and should not be reused (i.e., no new reference to this record should be allowed).");

    /**
     * The column <code>oagi.code_list.replacement_code_list_id</code>. This
     * refers to a replacement if the record is deprecated.
     */
    public final TableField<CodeListRecord, ULong> REPLACEMENT_CODE_LIST_ID = createField(DSL.name("replacement_code_list_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "This refers to a replacement if the record is deprecated.");

    /**
     * The column <code>oagi.code_list.created_by</code>. Foreign key to the
     * APP_USER table. It indicates the user who created the code list.
     */
    public final TableField<CodeListRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. It indicates the user who created the code list.");

    /**
     * The column <code>oagi.code_list.owner_user_id</code>. Foreign key to the
     * APP_USER table. This is the user who owns the entity, is allowed to edit
     * the entity, and who can transfer the ownership to another user.
     * 
     * The ownership can change throughout the history, but undoing shouldn't
     * rollback the ownership.
     */
    public final TableField<CodeListRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. This is the user who owns the entity, is allowed to edit the entity, and who can transfer the ownership to another user.\n\nThe ownership can change throughout the history, but undoing shouldn't rollback the ownership.");

    /**
     * The column <code>oagi.code_list.last_updated_by</code>. Foreign key to
     * the APP_USER table. It identifies the user who last updated the code
     * list.
     */
    public final TableField<CodeListRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. It identifies the user who last updated the code list.");

    /**
     * The column <code>oagi.code_list.creation_timestamp</code>. Timestamp when
     * the code list was created.
     */
    public final TableField<CodeListRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the code list was created.");

    /**
     * The column <code>oagi.code_list.last_update_timestamp</code>. Timestamp
     * when the code list was last updated.
     */
    public final TableField<CodeListRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the code list was last updated.");

    /**
     * The column <code>oagi.code_list.state</code>.
     */
    public final TableField<CodeListRecord, String> STATE = createField(DSL.name("state"), SQLDataType.VARCHAR(20).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.code_list.prev_code_list_id</code>. A self-foreign
     * key to indicate the previous history record.
     */
    public final TableField<CodeListRecord, ULong> PREV_CODE_LIST_ID = createField(DSL.name("prev_code_list_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "A self-foreign key to indicate the previous history record.");

    /**
     * The column <code>oagi.code_list.next_code_list_id</code>. A self-foreign
     * key to indicate the next history record.
     */
    public final TableField<CodeListRecord, ULong> NEXT_CODE_LIST_ID = createField(DSL.name("next_code_list_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "A self-foreign key to indicate the next history record.");

    private CodeList(Name alias, Table<CodeListRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private CodeList(Name alias, Table<CodeListRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("This table stores information about a code list. When a code list is derived from another code list, the whole set of code values belonging to the based code list will be copied."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.code_list</code> table reference
     */
    public CodeList(String alias) {
        this(DSL.name(alias), CODE_LIST);
    }

    /**
     * Create an aliased <code>oagi.code_list</code> table reference
     */
    public CodeList(Name alias) {
        this(alias, CODE_LIST);
    }

    /**
     * Create a <code>oagi.code_list</code> table reference
     */
    public CodeList() {
        this(DSL.name("code_list"), null);
    }

    public <O extends Record> CodeList(Table<O> path, ForeignKey<O, CodeListRecord> childPath, InverseForeignKey<O, CodeListRecord> parentPath) {
        super(path, childPath, parentPath, CODE_LIST);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class CodeListPath extends CodeList implements Path<CodeListRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> CodeListPath(Table<O> path, ForeignKey<O, CodeListRecord> childPath, InverseForeignKey<O, CodeListRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private CodeListPath(Name alias, Table<CodeListRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public CodeListPath as(String alias) {
            return new CodeListPath(DSL.name(alias), this);
        }

        @Override
        public CodeListPath as(Name alias) {
            return new CodeListPath(alias, this);
        }

        @Override
        public CodeListPath as(Table<?> alias) {
            return new CodeListPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<CodeListRecord, ULong> getIdentity() {
        return (Identity<CodeListRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<CodeListRecord> getPrimaryKey() {
        return Keys.KEY_CODE_LIST_PRIMARY;
    }

    @Override
    public List<ForeignKey<CodeListRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CODE_LIST_BASED_CODE_LIST_ID_FK, Keys.CODE_LIST_CREATED_BY_FK, Keys.CODE_LIST_LAST_UPDATED_BY_FK, Keys.CODE_LIST_NAMESPACE_ID_FK, Keys.CODE_LIST_NEXT_CODE_LIST_ID_FK, Keys.CODE_LIST_OWNER_USER_ID_FK, Keys.CODE_LIST_PREV_CODE_LIST_ID_FK, Keys.CODE_LIST_REPLACEMENT_CODE_LIST_ID_FK);
    }

    private transient CodeListPath _codeListBasedCodeListIdFk;

    /**
     * Get the implicit join path to the <code>oagi.code_list</code> table, via
     * the <code>code_list_based_code_list_id_fk</code> key.
     */
    public CodeListPath codeListBasedCodeListIdFk() {
        if (_codeListBasedCodeListIdFk == null)
            _codeListBasedCodeListIdFk = new CodeListPath(this, Keys.CODE_LIST_BASED_CODE_LIST_ID_FK, null);

        return _codeListBasedCodeListIdFk;
    }

    private transient AppUserPath _codeListCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>code_list_created_by_fk</code> key.
     */
    public AppUserPath codeListCreatedByFk() {
        if (_codeListCreatedByFk == null)
            _codeListCreatedByFk = new AppUserPath(this, Keys.CODE_LIST_CREATED_BY_FK, null);

        return _codeListCreatedByFk;
    }

    private transient AppUserPath _codeListLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>code_list_last_updated_by_fk</code> key.
     */
    public AppUserPath codeListLastUpdatedByFk() {
        if (_codeListLastUpdatedByFk == null)
            _codeListLastUpdatedByFk = new AppUserPath(this, Keys.CODE_LIST_LAST_UPDATED_BY_FK, null);

        return _codeListLastUpdatedByFk;
    }

    private transient NamespacePath _namespace;

    /**
     * Get the implicit join path to the <code>oagi.namespace</code> table.
     */
    public NamespacePath namespace() {
        if (_namespace == null)
            _namespace = new NamespacePath(this, Keys.CODE_LIST_NAMESPACE_ID_FK, null);

        return _namespace;
    }

    private transient CodeListPath _codeListNextCodeListIdFk;

    /**
     * Get the implicit join path to the <code>oagi.code_list</code> table, via
     * the <code>code_list_next_code_list_id_fk</code> key.
     */
    public CodeListPath codeListNextCodeListIdFk() {
        if (_codeListNextCodeListIdFk == null)
            _codeListNextCodeListIdFk = new CodeListPath(this, Keys.CODE_LIST_NEXT_CODE_LIST_ID_FK, null);

        return _codeListNextCodeListIdFk;
    }

    private transient AppUserPath _codeListOwnerUserIdFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>code_list_owner_user_id_fk</code> key.
     */
    public AppUserPath codeListOwnerUserIdFk() {
        if (_codeListOwnerUserIdFk == null)
            _codeListOwnerUserIdFk = new AppUserPath(this, Keys.CODE_LIST_OWNER_USER_ID_FK, null);

        return _codeListOwnerUserIdFk;
    }

    private transient CodeListPath _codeListPrevCodeListIdFk;

    /**
     * Get the implicit join path to the <code>oagi.code_list</code> table, via
     * the <code>code_list_prev_code_list_id_fk</code> key.
     */
    public CodeListPath codeListPrevCodeListIdFk() {
        if (_codeListPrevCodeListIdFk == null)
            _codeListPrevCodeListIdFk = new CodeListPath(this, Keys.CODE_LIST_PREV_CODE_LIST_ID_FK, null);

        return _codeListPrevCodeListIdFk;
    }

    private transient CodeListPath _codeListReplacementCodeListIdFk;

    /**
     * Get the implicit join path to the <code>oagi.code_list</code> table, via
     * the <code>code_list_replacement_code_list_id_fk</code> key.
     */
    public CodeListPath codeListReplacementCodeListIdFk() {
        if (_codeListReplacementCodeListIdFk == null)
            _codeListReplacementCodeListIdFk = new CodeListPath(this, Keys.CODE_LIST_REPLACEMENT_CODE_LIST_ID_FK, null);

        return _codeListReplacementCodeListIdFk;
    }

    private transient CodeListManifestPath _codeListManifest;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.code_list_manifest</code> table
     */
    public CodeListManifestPath codeListManifest() {
        if (_codeListManifest == null)
            _codeListManifest = new CodeListManifestPath(this, null, Keys.CODE_LIST_MANIFEST_CODE_LIST_ID_FK.getInverseKey());

        return _codeListManifest;
    }

    private transient CodeListValuePath _codeListValue;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.code_list_value</code> table
     */
    public CodeListValuePath codeListValue() {
        if (_codeListValue == null)
            _codeListValue = new CodeListValuePath(this, null, Keys.CODE_LIST_VALUE_CODE_LIST_ID_FK.getInverseKey());

        return _codeListValue;
    }

    private transient CtxSchemePath _ctxScheme;

    /**
     * Get the implicit to-many join path to the <code>oagi.ctx_scheme</code>
     * table
     */
    public CtxSchemePath ctxScheme() {
        if (_ctxScheme == null)
            _ctxScheme = new CtxSchemePath(this, null, Keys.CTX_SCHEME_CODE_LIST_ID_FK.getInverseKey());

        return _ctxScheme;
    }

    @Override
    public CodeList as(String alias) {
        return new CodeList(DSL.name(alias), this);
    }

    @Override
    public CodeList as(Name alias) {
        return new CodeList(alias, this);
    }

    @Override
    public CodeList as(Table<?> alias) {
        return new CodeList(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CodeList rename(String name) {
        return new CodeList(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CodeList rename(Name name) {
        return new CodeList(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CodeList rename(Table<?> name) {
        return new CodeList(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CodeList where(Condition condition) {
        return new CodeList(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CodeList where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CodeList where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CodeList where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CodeList where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CodeList where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CodeList where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CodeList where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CodeList whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CodeList whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
