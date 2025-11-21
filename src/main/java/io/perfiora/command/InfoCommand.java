package io.perfiora.command;

import io.perfiora.base.PerfioraCommand;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InfoCommand implements PerfioraCommand {

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
            System.out.println("Name: " + databaseProductName);
            System.out.println("Version: " + databaseProductVersion);
            connection.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
