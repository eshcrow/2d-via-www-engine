package com.company.drivers.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Results {

    private List<Map<String, String>> results = new ArrayList<>();
    private int currentIndex = -1;
    private int resultsSize = 0;


    public Results (ResultSet rs) {
        try {
            ResultSet resultSet = rs;
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                Map<String, String> column = new HashMap<>();

                for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    column.put(
                            columnName,
                            resultSet.getString(columnName)
                    );
                }

                this.results.add(column);

                this.resultsSize++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean next () {
        this.currentIndex++;

        return this.currentIndex != this.resultsSize;
    }

    public String getString (String columnLabel) {
        return this.results.get(this.currentIndex).get(columnLabel);
    }

    public int getInt (String columnLabel) {
        try {
            return Integer.parseInt(this.getString(columnLabel));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public List getAll () {
        return this.results;
    }

}
