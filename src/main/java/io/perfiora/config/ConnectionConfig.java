package io.perfiora.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionConfig {

    private String protocol;

    private String host;

    private int port;

    private String database;

    private String user;

    private String password;

    public ConnectionConfig() {
    }

    public ConnectionConfig(String protocol, String host, int port, String database, String user, String password) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Parse connection string using next pattern:
     * jdbc:mysql://user:pass@host/database
     *
     * @param connectionString String
     * @return ConnectionConfig
     */
    public static ConnectionConfig fromConnectionString(String connectionString) {
        Pattern pattern = Pattern.compile(
                "^jdbc:mysql://(?:(?<user>[^:]+)(?::(?<pass>[^@]+))?@)?(?<host>[^:/]+)(?::(?<port>\\d+))?/(?<db>[^?]+)"
        );

        Matcher m = pattern.matcher(connectionString);

        ConnectionConfig connectionConfig = new ConnectionConfig();

        if (m.matches()) {
            connectionConfig.setUser(m.group("user"));
            connectionConfig.setPassword(m.group("pass"));
            connectionConfig.setHost(m.group("host"));
            connectionConfig.setPort(Integer.parseInt(m.group("port")));
            connectionConfig.setDatabase(m.group("db"));
        }

        return connectionConfig;
    }
}
