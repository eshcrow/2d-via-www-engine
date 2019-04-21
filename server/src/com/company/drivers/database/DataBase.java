package com.company.drivers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import com.company.helpers.Prop;
import com.company.helpers.Str;
import com.company.helpers.Log;

/**
 * Instruction:
 * First set table:
 * db.setTable("your_table")
 * When you want results filtered by where do this like that
 * db.where("col_name", "operator")...etc.select().setInt(1).execute().results()
 * You get a object where you can manage your db results.
 * When you want all results from table do this like that
 * db.select().results()
 */
public class DataBase {

    private Connection connection;
    private Statement statement;
    private ArrayList<String> where = new ArrayList();
    private PreparedStatement preparedStatement;
    private String[] selectColumn;
    private String tableName;
    private ResultSet resultsSet;
    private Results results;
    private int preparedValuesAmount = 1;

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
     * SQL WHERE condition.
     */
    public DataBase where (String columnName, String operator) {
        return this.operation(columnName, operator);
    }

    /**
     * @param columnName Column name in table
     * @param operator Condition operator.
     * @return DataBase
     *
     * SQL OR condition.
     */
    public DataBase or (String columnName, String operator) {
        return this.operation("OR " + columnName, operator);
    }

    /**
     * @param columnName Column name in table
     * @param operator Condition operator.
     * @return DataBase
     *
     * SQL AND condition.
     */
    public DataBase and (String columnName, String operator) {
        return this.operation("AND " + columnName, operator);
    }

    public DataBase get () {
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
     * Here you can set what columns can be use in ResultSet.
     */
    public DataBase getOnly (String[] column) {
        this.selectColumn = column;
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
            if (this.whereHasValues())
                this.prepareStatement(this.queryBuild("SELECT"));
            else
                this.resultsSet = this.statement.executeQuery(this.queryBuild("SELECT"));

            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataBase setString (String value) {
        try {
            this.preparedStatement.setString(this.preparedValuesAmount, value);
            this.preparedValuesAmount++;
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataBase setInt (int value) {
        try {
            this.preparedStatement.setInt(this.preparedValuesAmount, value);
            this.preparedValuesAmount++;
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getResultsSet () {
        return this.resultsSet;
    }

    public Results results () {
        if (this.resultsSet == null)
            return null;

        if (this.results == null)
            this.results = new Results(this.resultsSet);

        return this.results;
    }

    public ResultSet first () {
        try {
            this.resultsSet.first();
            return this.resultsSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet last () {
        try {
            this.resultsSet.last();
            return this.resultsSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataBase execute () {
        try {
            this.resultsSet = this.preparedStatement.executeQuery();
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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