/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.Library;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LibraryRecord extends UpdatableRecordImpl<LibraryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.library.library_id</code>. Internal, primary
     * database key.
     */
    public void setLibraryId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.library.library_id</code>. Internal, primary
     * database key.
     */
    public ULong getLibraryId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.library.name</code>. A library name.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.library.name</code>. A library name.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LibraryRecord
     */
    public LibraryRecord() {
        super(Library.LIBRARY);
    }

    /**
     * Create a detached, initialised LibraryRecord
     */
    public LibraryRecord(ULong libraryId, String name) {
        super(Library.LIBRARY);

        setLibraryId(libraryId);
        setName(name);
        resetChangedOnNotNull();
    }
}
