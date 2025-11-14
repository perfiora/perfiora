package io.perfiora.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionConfigTest {

    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/test";

    private final String CONNECTION_STRING_WITH_CREDENTIALS =  "jdbc:mysql://user:pass@localhost:3306/test";

    @Test
    public void testFromConnectionString() {
        ConnectionConfig connectionConfig = ConnectionConfig.fromConnectionString(CONNECTION_STRING_WITH_CREDENTIALS);

        assertEquals("user",  connectionConfig.getUser());
        assertEquals("pass", connectionConfig.getPassword());
        assertEquals("localhost", connectionConfig.getHost());
        assertEquals(3306, connectionConfig.getPort());
        assertEquals("test", connectionConfig.getDatabase());
    }
}
