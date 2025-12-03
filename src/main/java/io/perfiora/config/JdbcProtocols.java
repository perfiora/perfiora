package io.perfiora.config;

/**
 * JDBC protocol enum for various database vendors.
 */
public enum JdbcProtocols {
    /**
     * MySQL JDBC protocol.
     */
    MYSQL("mysql", 3306),

    /**
     * PostgreSQL JDBC protocol.
     */
    POSTGRESQL("postgresql", 5432);

    private final String protocol;
    private final int defaultPort;

    JdbcProtocols(String protocol, int defaultPort) {
        this.protocol = protocol;
        this.defaultPort = defaultPort;
    }

    /**
     * Get the protocol string value.
     *
     * @return The protocol string (e.g., "mysql", "postgresql")
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Get the default port for this protocol.
     *
     * @return The default port number
     */
    public int getDefaultPort() {
        return defaultPort;
    }

    /**
     * Find a JDBC protocol enum by its protocol string value.
     *
     * @param protocol The protocol string (case-insensitive)
     * @return The matching JdbcProtocols enum
     * @throws IllegalArgumentException if the protocol is not recognized
     */
    public static JdbcProtocols fromProtocol(String protocol) {
        if (protocol == null) {
            throw new IllegalArgumentException("Protocol cannot be null");
        }
        String lowerProtocol = protocol.toLowerCase();
        for (JdbcProtocols jdbcProtocol : values()) {
            if (jdbcProtocol.protocol.equals(lowerProtocol)) {
                return jdbcProtocol;
            }
        }
        throw new IllegalArgumentException("Invalid protocol: " + protocol);
    }
}

