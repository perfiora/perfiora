package io.perfiora.command;

import io.perfiora.base.PerfioraCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class DiffCommand implements PerfioraCommand {

    private static final Logger log = Logger.getLogger(DiffCommand.class.getName());

    public DiffCommand(String sourceLink, String targetLink) {
        try (Connection sourceConnection = DriverManager.getConnection(sourceLink); Connection targetConnection = DriverManager.getConnection(targetLink)) {
            // Read tables
            Set<String> sourceTables = new HashSet<>();
            try (Statement statement = sourceConnection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE()"
                 )) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    sourceTables.add(tableName);
                }
            }
            Set<String> targetTables = new HashSet<>();
            try (Statement statement = targetConnection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE()"
                 )) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    targetTables.add(tableName);
                }
            }
            
            Set<String> missingTables = new HashSet<>(sourceTables);
            missingTables.removeAll(targetTables);
            
            Set<String> newTables = new HashSet<>(targetTables);
            newTables.removeAll(sourceTables);
            
            log.info("Missing tables: " + missingTables);
            log.info("New tables: " + newTables);
            
            // Read columns
            // Read indexes
        } catch (SQLException ex) {
            log.warning("SQLException: " + ex.getMessage());
            log.warning("SQLState: " + ex.getSQLState());
            log.warning("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public String getName() {
        return "diff";
    }

    @Override
    public void execute() {

    }
}
