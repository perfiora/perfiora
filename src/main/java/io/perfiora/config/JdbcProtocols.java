package io.perfiora.config;

/**
 * JDBC protocol constants for various database vendors.
 */
public final class JdbcProtocols {

    /**
     * MySQL JDBC protocol.
     */
    public static final String MYSQL = "mysql";

    /**
     * PostgreSQL JDBC protocol.
     */
    public static final String POSTGRESQL = "postgresql";

    /**
     * Default port for MySQL.
     */
    public static final int MYSQL_DEFAULT_PORT = 3306;

    /**
     * Default port for PostgreSQL.
     */
    public static final int POSTGRESQL_DEFAULT_PORT = 5432;

    /**
     * Private constructor to prevent instantiation.
     */
    private JdbcProtocols() {
        throw new AssertionError("Cannot instantiate constants class");
    }
}

