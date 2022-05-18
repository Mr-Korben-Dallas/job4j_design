package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void createTable(String tableName) {
        String query = "create table if not exists %s();";
        String preparedQuery = prepareQuery(query, tableName);
        exec(preparedQuery);
    }

    public void dropTable(String tableName) {
        String query = "drop table if exists %s;";
        String preparedQuery = prepareQuery(query, tableName);
        exec(preparedQuery);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String query = "ALTER TABLE IF EXISTS %s ADD COLUMN IF NOT EXISTS %s %s;";
        String preparedQuery = prepareQuery(query, tableName, columnName, type);
        exec(preparedQuery);
    }

    public void dropColumn(String tableName, String columnName) {
        String query = "ALTER TABLE IF EXISTS %s DROP COLUMN %s;";
        String preparedQuery = prepareQuery(query, tableName, columnName);
        exec(preparedQuery);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String query = "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;";
        String preparedQuery = prepareQuery(query, tableName, columnName, newColumnName);
        exec(preparedQuery);
    }

    private void initConnection() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            this.properties.load(in);
            Class.forName(this.properties.getProperty("jdbc.driver"));
            connection = DriverManager.getConnection(
                    this.properties.getProperty("jdbc.url"),
                    this.properties.getProperty("jdbc.username"),
                    this.properties.getProperty("jdbc.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exec(String formattedQuery) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(formattedQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = this.connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private String prepareQuery(String query, Object... args) {
        return String.format(query, args);
    }

    private static void showTableState(TableEditor tableEditor, String tableName) throws Exception {
        System.out.println(tableEditor.getTableScheme(tableName));
    }

    public static void main(String[] args) {
        try (TableEditor tableEditor = new TableEditor(new Properties())) {
            String tableName = "demo_table";
            tableEditor.createTable(tableName);
            showTableState(tableEditor, tableName);
            tableEditor.addColumn(tableName, "new_column_name", "text");
            showTableState(tableEditor, tableName);
            tableEditor.renameColumn(tableName, "new_column_name", "some_other_column_name");
            showTableState(tableEditor, tableName);
            tableEditor.dropColumn(tableName, "some_other_column_name");
            showTableState(tableEditor, tableName);
            tableEditor.dropTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}