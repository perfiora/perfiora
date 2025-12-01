package io.perfiora.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionConfigTest {

    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/test";

    private final String CONNECTION_STRING_WITH_CREDENTIALS =  "jdbc:mysql://user:pass@localhost:3306/test";

    @Test
    public void testFromConnectionString() {
        ConnectionConfig connectionConfig = ConnectionConfig.fromConnectionString(CONNECTION_STRING_WITH_CREDENTIALS);

        assertEquals(JdbcProtocols.MYSQL, connectionConfig.getProtocol());
        assertEquals("user",  connectionConfig.getUser());
        assertEquals("pass", connectionConfig.getPassword());
        assertEquals("localhost", connectionConfig.getHost());
        assertEquals(JdbcProtocols.MYSQL_DEFAULT_PORT, connectionConfig.getPort());
        assertEquals("test", connectionConfig.getDatabase());
    }

    @Test
    public void testFromConnectionStringWithoutPort() {
        String connectionString = "jdbc:mysql://user:pass@localhost/test";
        ConnectionConfig connectionConfig = ConnectionConfig.fromConnectionString(connectionString);

        assertEquals(JdbcProtocols.MYSQL, connectionConfig.getProtocol());
        assertEquals("user", connectionConfig.getUser());
        assertEquals("pass", connectionConfig.getPassword());
        assertEquals("localhost", connectionConfig.getHost());
        assertEquals(JdbcProtocols.MYSQL_DEFAULT_PORT, connectionConfig.getPort()); // Default port
        assertEquals("test", connectionConfig.getDatabase());
    }

    @Test
    public void testFromConnectionStringPostgreSQL() {
        String connectionString = "jdbc:postgresql://user:pass@localhost:5432/testdb";
        ConnectionConfig connectionConfig = ConnectionConfig.fromConnectionString(connectionString);

        assertEquals(JdbcProtocols.POSTGRESQL, connectionConfig.getProtocol());
        assertEquals("user", connectionConfig.getUser());
        assertEquals("pass", connectionConfig.getPassword());
        assertEquals("localhost", connectionConfig.getHost());
        assertEquals(JdbcProtocols.POSTGRESQL_DEFAULT_PORT, connectionConfig.getPort());
        assertEquals("testdb", connectionConfig.getDatabase());
    }

    @Test
    public void testFromConnectionStringPostgreSQLWithoutPort() {
        String connectionString = "jdbc:postgresql://user:pass@localhost/testdb";
        ConnectionConfig connectionConfig = ConnectionConfig.fromConnectionString(connectionString);

        assertEquals(JdbcProtocols.POSTGRESQL, connectionConfig.getProtocol());
        assertEquals("user", connectionConfig.getUser());
        assertEquals("pass", connectionConfig.getPassword());
        assertEquals("localhost", connectionConfig.getHost());
        assertEquals(JdbcProtocols.POSTGRESQL_DEFAULT_PORT, connectionConfig.getPort()); // Default port
        assertEquals("testdb", connectionConfig.getDatabase());
    }
}
