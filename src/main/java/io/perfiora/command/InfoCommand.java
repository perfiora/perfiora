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
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(unifiedResourceLocator);
            DatabaseMetaData metaData = connection.getMetaData();
            String databaseProductVersion = metaData.getDatabaseProductVersion();
            String databaseProductName = metaData.getDatabaseProductName();
            log.info("Name: " + databaseProductName);
            log.info("Version: " + databaseProductVersion);
            connection.close();
        } catch (SQLException ex) {
            log.warning("SQLException: " + ex.getMessage());
            log.warning("SQLState: " + ex.getSQLState());
            log.warning("VendorError: " + ex.getErrorCode());
        }
    }
}
