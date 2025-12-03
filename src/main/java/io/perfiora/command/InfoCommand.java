package io.perfiora.command;

import io.perfiora.base.PerfioraCommand;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class InfoCommand implements PerfioraCommand {

    private static final Logger log = Logger.getLogger(InfoCommand.class.getName());

    private final String unifiedResourceLocator;

    public InfoCommand(String unifiedResourceLocator) {
        this.unifiedResourceLocator = unifiedResourceLocator;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public void execute() {
        try (Connection connection = DriverManager.getConnection(unifiedResourceLocator)) {
            DatabaseMetaData metaData = connection.getMetaData();
            
            // DBMS Information
            String databaseProductName = metaData.getDatabaseProductName();
            String databaseProductVersion = metaData.getDatabaseProductVersion();
            String driverName = metaData.getDriverName();
            String driverVersion = metaData.getDriverVersion();
            
            log.info("=== DBMS Information ===");
            log.info("Product Name: " + databaseProductName);
            log.info("Product Version: " + databaseProductVersion);
            log.info("Driver Name: " + driverName);
            log.info("Driver Version: " + driverVersion);
            
            // Database Information
            String catalog = connection.getCatalog();
            String schema = connection.getSchema();
            String url = metaData.getURL();
            String userName = metaData.getUserName();
            
            log.info("=== Database Information ===");
            if (catalog != null && !catalog.isEmpty()) {
                log.info("Database: " + catalog);
            }
            if (schema != null && !schema.isEmpty()) {
                log.info("Schema: " + schema);
            }
            if (userName != null && !userName.isEmpty()) {
                log.info("User: " + userName);
            }
            log.info("URL: " + url);
        } catch (SQLException ex) {
            log.warning("SQLException: " + ex.getMessage());
            log.warning("SQLState: " + ex.getSQLState());
            log.warning("VendorError: " + ex.getErrorCode());
        }
    }
}
