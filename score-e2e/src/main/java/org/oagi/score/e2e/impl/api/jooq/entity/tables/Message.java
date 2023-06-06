/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.MessageRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Message extends TableImpl<MessageRecord> {

    /**
     * The reference instance of <code>oagi.message</code>
     */
    public static final Message MESSAGE = new Message();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>oagi.message.message_id</code>.
     */
    public final TableField<MessageRecord, ULong> MESSAGE_ID = createField(DSL.name("message_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");
    /**
     * The column <code>oagi.message.sender_id</code>. The user who created this
     * record.
     */
    public final TableField<MessageRecord, ULong> SENDER_ID = createField(DSL.name("sender_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who created this record.");
    /**
     * The column <code>oagi.message.recipient_id</code>. The user who is a
     * target to possess this record.
     */
    public final TableField<MessageRecord, ULong> RECIPIENT_ID = createField(DSL.name("recipient_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who is a target to possess this record.");
    /**
     * The column <code>oagi.message.subject</code>. A subject of the message
     */
    public final TableField<MessageRecord, String> SUBJECT = createField(DSL.name("subject"), SQLDataType.CLOB, this, "A subject of the message");
    /**
     * The column <code>oagi.message.body</code>. A body of the message.
     */
    public final TableField<MessageRecord, String> BODY = createField(DSL.name("body"), SQLDataType.CLOB, this, "A body of the message.");
    /**
     * The column <code>oagi.message.body_content_type</code>. A content type of
     * the body
     */
    public final TableField<MessageRecord, String> BODY_CONTENT_TYPE = createField(DSL.name("body_content_type"), SQLDataType.VARCHAR(50).nullable(false).defaultValue(DSL.inline("text/plain", SQLDataType.VARCHAR)), this, "A content type of the body");
    /**
     * The column <code>oagi.message.is_read</code>. An indicator whether this
     * record is read or not.
     */
    public final TableField<MessageRecord, Byte> IS_READ = createField(DSL.name("is_read"), SQLDataType.TINYINT.defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "An indicator whether this record is read or not.");
    /**
     * The column <code>oagi.message.creation_timestamp</code>. The timestamp
     * when the record was first created.
     */
    public final TableField<MessageRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was first created.");
    private transient AppUser _messageSenderIdFk;
    private transient AppUser _messageRecipientIdFk;

    private Message(Name alias, Table<MessageRecord> aliased) {
        this(alias, aliased, null);
    }

    private Message(Name alias, Table<MessageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.message</code> table reference
     */
    public Message(String alias) {
        this(DSL.name(alias), MESSAGE);
    }

    /**
     * Create an aliased <code>oagi.message</code> table reference
     */
    public Message(Name alias) {
        this(alias, MESSAGE);
    }

    /**
     * Create a <code>oagi.message</code> table reference
     */
    public Message() {
        this(DSL.name("message"), null);
    }

    public <O extends Record> Message(Table<O> child, ForeignKey<O, MessageRecord> key) {
        super(child, key, MESSAGE);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MessageRecord> getRecordType() {
        return MessageRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<MessageRecord, ULong> getIdentity() {
        return (Identity<MessageRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<MessageRecord> getPrimaryKey() {
        return Keys.KEY_MESSAGE_PRIMARY;
    }

    @Override
    public List<ForeignKey<MessageRecord, ?>> getReferences() {
        return Arrays.asList(Keys.MESSAGE_SENDER_ID_FK, Keys.MESSAGE_RECIPIENT_ID_FK);
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>message_sender_id_fk</code> key.
     */
    public AppUser messageSenderIdFk() {
        if (_messageSenderIdFk == null)
            _messageSenderIdFk = new AppUser(this, Keys.MESSAGE_SENDER_ID_FK);

        return _messageSenderIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>message_recipient_id_fk</code> key.
     */
    public AppUser messageRecipientIdFk() {
        if (_messageRecipientIdFk == null)
            _messageRecipientIdFk = new AppUser(this, Keys.MESSAGE_RECIPIENT_ID_FK);

        return _messageRecipientIdFk;
    }

    @Override
    public Message as(String alias) {
        return new Message(DSL.name(alias), this);
    }

    @Override
    public Message as(Name alias) {
        return new Message(alias, this);
    }

    @Override
    public Message as(Table<?> alias) {
        return new Message(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(String name) {
        return new Message(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(Name name) {
        return new Message(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(Table<?> name) {
        return new Message(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<ULong, ULong, ULong, String, String, String, Byte, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super ULong, ? super ULong, ? super ULong, ? super String, ? super String, ? super String, ? super Byte, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super ULong, ? super ULong, ? super ULong, ? super String, ? super String, ? super String, ? super Byte, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
