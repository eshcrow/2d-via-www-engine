package com.company.drivers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.List;

import com.company.helpers.Prop;
import com.company.helpers.Str;
import com.company.helpers.Log;


/**
 * Instruction:
 * First set table:
 * db.setTable("your_table")
 * When you want results filtered by where do this like that
 * db.where("int_col_name", "=").setInt(1).or("str_col_name", "!=").setStr("String").select().results()
 */
public class DataBase {

    private Connection connection;
    private Statement statement;
    private ArrayList<String> where = new ArrayList();
    private PreparedStatement preparedStatement;
    private String[] selectColumn;
    private String tableName = "";
    private ResultSet resultsSet;
    private Results results;
    private List<String> queue = new ArrayList<>();
    private List<Integer> intValues = new ArrayList<>();
    private List<String> strValues = new ArrayList<>();

    /**
     * Loading properties file with database connection data.
     * Getting JDBC driver class.
     * Opening new connection with data base.
     * Creating statement.
     */
    public DataBase () {
        try {
            Prop properties = new Prop("../server.properties");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(Str.replace(
                    "jdbc:mysql://:host/:db_name?user=:user_name&password=:password:other",
                    new String[][] {
                            {"host", properties.get("DB_HOST")},
                            {"db_name", properties.get("DB_NAME")},
                            {"user_name", properties.get("DB_USER")},
                            {"password", properties.get("DB_PASSWORD")},
                            {"other", "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"}
                    }
            ));

            this.statement = this.connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }

    }

    /**
     * @param operation SQL operation type. (SELECT, DELETE, UPDATE)
     * @return DataBase
     *
     * Concatenate operation, table name and where conditions to one SQL string.
     */
    private String queryBuild (String operation) {
        String sql = operation;

        if (this.selectColumn != null)
            sql = sql + " " + String.join(",", this.selectColumn);
        else
            sql = sql + " *";

        sql = sql + " FROM " + this.tableName;

        if (this.whereHasValues()) {
            sql = sql + " WHERE";

            for (String i : this.where) {
                sql = sql + " " + i;
            }

        }

        Log.info(sql);

        return sql;
    }

    /**
     * @param tableName Data base table name.
     * @return DataBase
     *
     * Set table name to operate it. This is first action.
     */
    public DataBase setTable (String tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     * @param columnName Column name in table
     * @param operator Condition operator.
     * @return DataBase
     *
     * Create string with column name and given operator and add it  to where list.
     */
    public DataBase operation (String columnName, String operator) {

        try {
            if (this.tableName == null)
                throw new Error("You can't create conditions when table is not selected.");

            switch (operator) {
                case "!=":
                    this.where.add(columnName + " NOT IN (?)");
                    break;
                case "null":
                    this.where.add(columnName + "IS NULL");
                    break;
                case "notNull":
                    this.where.add(columnName + "IS NOT NULL");
                    break;
                default:
                    this.where.add(columnName + operator + "?");
            }

            return this;
        } catch (Error e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param column Column names.
     * @return DataBase
     *
     * Here you can set what columns can be use in ResultSet in select operation.
     */
    public DataBase getOnly (String[] column) {
        this.selectColumn = column;
        return this;
    }

    /**
     * DOC FOR WHERE CONDITIONS BEGIN
     *
     * @param columnLabel name in database.
     * @param operator Condition operator.
     * @return DataBase
     *
     * All these methods are used to creating SQL where conditions.
     * Where condition is created by method operation and DataBase object is returned.
     */
    public DataBase where (
            String columnLabel,
            String operator
    ) {
        return this.operation(columnLabel, operator);
    }

    public DataBase and (
            String columnLabel,
            String operator
    ) {
        return this.operation("AND " + columnLabel, operator);
    }

    public DataBase or (
            String columnLabel,
            String operator
    ) {
        return this.operation("OR " + columnLabel, operator);
    }
    /* DOC FOR WHERE CONDITIONS END */

    /**
     * @param value Integer value to condition.
     * @return DataBase
     */
    public DataBase setInt (int value) {
        this.queue.add("int");
        this.intValues.add(value);
        return this;
    }

    /**
     * @param value String value to condition.
     * @return DataBase
     */
    public DataBase setStr (String value) {
        this.queue.add("str");
        this.strValues.add(value);
        return this;
    }

    /**
     * @return DataBase
     *
     * Check if something is setting in where list. If it is field this try to create prepared statement.
     * If it is empty sql request is immediately done here.
     */
    public DataBase select () {
        try {
            if (this.whereHasValues()) {
                this.prepareStatement(this.queryBuild("SELECT"));
                this.prepareStatements();
            } else
                this.resultsSet = this.statement.executeQuery(this.queryBuild("SELECT"));

            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Here all types from queue are related in conditions.
     * Next values are allocated to intValues or strValues list.
     * Next SQL request is executed and results are saved to resultsSet.
     */
    private void prepareStatements () {
        try {
            int preparedValues = 1;

            for (String i : this.queue) {
                if (i.equals("int")) {
                    this.preparedStatement.setInt(preparedValues, this.intValues.get(0));
                    this.intValues.remove(0);
                } else if (i.equals("str")) {
                    this.preparedStatement.setString(preparedValues, this.strValues.get(0));
                    this.strValues.remove(0);
                }
                preparedValues++;
            }

            this.resultsSet = this.preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return resultSet object.
     */
    public ResultSet getResultsSet () {
        return this.resultsSet;
    }

    /**
     * @return Result object.
     */
    public Results results () {
        if (this.resultsSet == null)
            return null;

        if (this.results == null)
            this.results = new Results(this.resultsSet);

        return this.results;
    }

    /**
     * @return Move cursor to begin of result set.
     */
    public ResultSet first () {
        try {
            this.resultsSet.first();
            return this.resultsSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return Move cursor to end of result set.
     */
    public ResultSet last () {
        try {
            this.resultsSet.last();
            return this.resultsSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param sql String with sql conditions.
     *
     * Here is created statement based on your sql conditions.
     */
    private void prepareStatement (String sql) {
        try {
            this.preparedStatement = this.connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return true if something is in where.
     */
    private Boolean whereHasValues () {
        return this.where.size() > 0;
    }

}