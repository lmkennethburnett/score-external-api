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
import org.oagi.score.repo.api.impl.jooq.entity.tables.BdtScPriRestri.BdtScPriRestriPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CdtScAwdPri.CdtScAwdPriPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Xbt.XbtPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.CdtScAwdPriXpsTypeMapRecord;


/**
 * The purpose of this table is the same as that of the
 * CDT_AWD_PRI_XPS_TYPE_MAP, but it is for the supplementary component (SC). It
 * allows for the concrete mapping between the CDT Primitives and types in a
 * particular expression such as XML Schema, JSON. 
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CdtScAwdPriXpsTypeMap extends TableImpl<CdtScAwdPriXpsTypeMapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.cdt_sc_awd_pri_xps_type_map</code>
     */
    public static final CdtScAwdPriXpsTypeMap CDT_SC_AWD_PRI_XPS_TYPE_MAP = new CdtScAwdPriXpsTypeMap();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CdtScAwdPriXpsTypeMapRecord> getRecordType() {
        return CdtScAwdPriXpsTypeMapRecord.class;
    }

    /**
     * The column
     * <code>oagi.cdt_sc_awd_pri_xps_type_map.cdt_sc_awd_pri_xps_type_map_id</code>.
     * Internal, primary database key.
     */
    public final TableField<CdtScAwdPriXpsTypeMapRecord, ULong> CDT_SC_AWD_PRI_XPS_TYPE_MAP_ID = createField(DSL.name("cdt_sc_awd_pri_xps_type_map_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Internal, primary database key.");

    /**
     * The column
     * <code>oagi.cdt_sc_awd_pri_xps_type_map.cdt_sc_awd_pri_id</code>. Foreign
     * key to the CDT_SC_AWD_PRI table.
     */
    public final TableField<CdtScAwdPriXpsTypeMapRecord, ULong> CDT_SC_AWD_PRI_ID = createField(DSL.name("cdt_sc_awd_pri_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the CDT_SC_AWD_PRI table.");

    /**
     * The column <code>oagi.cdt_sc_awd_pri_xps_type_map.xbt_id</code>. Foreign
     * key to the Xbt table. It identifies an XML schema built-in type that maps
     * to the CDT SC Allowed Primitive identified in the CDT_SC_AWD_PRI column.
     */
    public final TableField<CdtScAwdPriXpsTypeMapRecord, ULong> XBT_ID = createField(DSL.name("xbt_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the Xbt table. It identifies an XML schema built-in type that maps to the CDT SC Allowed Primitive identified in the CDT_SC_AWD_PRI column.");

    /**
     * The column <code>oagi.cdt_sc_awd_pri_xps_type_map.is_default</code>.
     * Indicating a default value domain mapping.
     */
    public final TableField<CdtScAwdPriXpsTypeMapRecord, Byte> IS_DEFAULT = createField(DSL.name("is_default"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.field(DSL.raw("0"), SQLDataType.TINYINT)), this, "Indicating a default value domain mapping.");

    private CdtScAwdPriXpsTypeMap(Name alias, Table<CdtScAwdPriXpsTypeMapRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private CdtScAwdPriXpsTypeMap(Name alias, Table<CdtScAwdPriXpsTypeMapRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("The purpose of this table is the same as that of the CDT_AWD_PRI_XPS_TYPE_MAP, but it is for the supplementary component (SC). It allows for the concrete mapping between the CDT Primitives and types in a particular expression such as XML Schema, JSON. "), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.cdt_sc_awd_pri_xps_type_map</code> table
     * reference
     */
    public CdtScAwdPriXpsTypeMap(String alias) {
        this(DSL.name(alias), CDT_SC_AWD_PRI_XPS_TYPE_MAP);
    }

    /**
     * Create an aliased <code>oagi.cdt_sc_awd_pri_xps_type_map</code> table
     * reference
     */
    public CdtScAwdPriXpsTypeMap(Name alias) {
        this(alias, CDT_SC_AWD_PRI_XPS_TYPE_MAP);
    }

    /**
     * Create a <code>oagi.cdt_sc_awd_pri_xps_type_map</code> table reference
     */
    public CdtScAwdPriXpsTypeMap() {
        this(DSL.name("cdt_sc_awd_pri_xps_type_map"), null);
    }

    public <O extends Record> CdtScAwdPriXpsTypeMap(Table<O> path, ForeignKey<O, CdtScAwdPriXpsTypeMapRecord> childPath, InverseForeignKey<O, CdtScAwdPriXpsTypeMapRecord> parentPath) {
        super(path, childPath, parentPath, CDT_SC_AWD_PRI_XPS_TYPE_MAP);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class CdtScAwdPriXpsTypeMapPath extends CdtScAwdPriXpsTypeMap implements Path<CdtScAwdPriXpsTypeMapRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> CdtScAwdPriXpsTypeMapPath(Table<O> path, ForeignKey<O, CdtScAwdPriXpsTypeMapRecord> childPath, InverseForeignKey<O, CdtScAwdPriXpsTypeMapRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private CdtScAwdPriXpsTypeMapPath(Name alias, Table<CdtScAwdPriXpsTypeMapRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public CdtScAwdPriXpsTypeMapPath as(String alias) {
            return new CdtScAwdPriXpsTypeMapPath(DSL.name(alias), this);
        }

        @Override
        public CdtScAwdPriXpsTypeMapPath as(Name alias) {
            return new CdtScAwdPriXpsTypeMapPath(alias, this);
        }

        @Override
        public CdtScAwdPriXpsTypeMapPath as(Table<?> alias) {
            return new CdtScAwdPriXpsTypeMapPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<CdtScAwdPriXpsTypeMapRecord, ULong> getIdentity() {
        return (Identity<CdtScAwdPriXpsTypeMapRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<CdtScAwdPriXpsTypeMapRecord> getPrimaryKey() {
        return Keys.KEY_CDT_SC_AWD_PRI_XPS_TYPE_MAP_PRIMARY;
    }

    @Override
    public List<ForeignKey<CdtScAwdPriXpsTypeMapRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CDT_SC_AWD_PRI_XPS_TYPE_MAP_CDT_SC_AWD_PRI_ID_FK, Keys.CDT_SC_AWD_PRI_XPS_TYPE_MAP_XBT_ID_FK);
    }

    private transient CdtScAwdPriPath _cdtScAwdPri;

    /**
     * Get the implicit join path to the <code>oagi.cdt_sc_awd_pri</code> table.
     */
    public CdtScAwdPriPath cdtScAwdPri() {
        if (_cdtScAwdPri == null)
            _cdtScAwdPri = new CdtScAwdPriPath(this, Keys.CDT_SC_AWD_PRI_XPS_TYPE_MAP_CDT_SC_AWD_PRI_ID_FK, null);

        return _cdtScAwdPri;
    }

    private transient XbtPath _xbt;

    /**
     * Get the implicit join path to the <code>oagi.xbt</code> table.
     */
    public XbtPath xbt() {
        if (_xbt == null)
            _xbt = new XbtPath(this, Keys.CDT_SC_AWD_PRI_XPS_TYPE_MAP_XBT_ID_FK, null);

        return _xbt;
    }

    private transient BdtScPriRestriPath _bdtScPriRestri;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.bdt_sc_pri_restri</code> table
     */
    public BdtScPriRestriPath bdtScPriRestri() {
        if (_bdtScPriRestri == null)
            _bdtScPriRestri = new BdtScPriRestriPath(this, null, Keys.BDT_SC_PRI_RESTRI_CDT_SC_AWD_PRI_XPS_TYPE_MAP_ID_FK.getInverseKey());

        return _bdtScPriRestri;
    }

    @Override
    public CdtScAwdPriXpsTypeMap as(String alias) {
        return new CdtScAwdPriXpsTypeMap(DSL.name(alias), this);
    }

    @Override
    public CdtScAwdPriXpsTypeMap as(Name alias) {
        return new CdtScAwdPriXpsTypeMap(alias, this);
    }

    @Override
    public CdtScAwdPriXpsTypeMap as(Table<?> alias) {
        return new CdtScAwdPriXpsTypeMap(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap rename(String name) {
        return new CdtScAwdPriXpsTypeMap(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap rename(Name name) {
        return new CdtScAwdPriXpsTypeMap(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap rename(Table<?> name) {
        return new CdtScAwdPriXpsTypeMap(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap where(Condition condition) {
        return new CdtScAwdPriXpsTypeMap(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CdtScAwdPriXpsTypeMap where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CdtScAwdPriXpsTypeMap where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CdtScAwdPriXpsTypeMap where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CdtScAwdPriXpsTypeMap where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CdtScAwdPriXpsTypeMap whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
