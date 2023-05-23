package services;

import com.datastax.oss.driver.api.core.CqlIdentifier;

public class CasConstIdentifiers {

    public static final CqlIdentifier KEYSPACE_ID = CqlIdentifier.fromCql("library");
    public static final CqlIdentifier RENT_TABLE_ID = CqlIdentifier.fromCql("rent");
    public static final CqlIdentifier BOOK_TABLE_ID = CqlIdentifier.fromCql("book");
    public static final CqlIdentifier CLIENT_TABLE_ID = CqlIdentifier.fromCql("client");
}
