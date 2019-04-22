package com.company.drivers.database;

import com.company.drivers.database.interfaces.TableName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBaseModel <T extends DataBaseModel <T>> extends DataBase implements TableName {

    public String tableName;
    private List<String[]> subclassFields = new ArrayList<>();

    public DataBaseModel () {
        Field[] fields = this.getClass().getFields();

        for (Field i : fields) {
            String[] fieldSplits = i.toString().split(" ");

            String[] typeSplits = fieldSplits[1].split("\\.");
            String[] nameSplits = fieldSplits[2].split("\\.");

            String type;
            String name;

            if (typeSplits.length == 1)
                type = typeSplits[0].toLowerCase();
            else
                type = typeSplits[typeSplits.length - 1].toLowerCase();

            if (nameSplits.length == 1)
                name = nameSplits[0];
            else
                name = nameSplits[nameSplits.length - 1];

            this.subclassFields.add(
                    new String[] {
                            name, type
                    }
            );
        }

        this.setTable(this.setTableName());
    }

    public String setTableName () {
        return tableName;
    }

    public T test () {
        return (T) this;
    }

    public void setValue () {
        Field field;

        try {
            field = this.getClass().getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            field = null;
        }

        Class superClass = this.getClass().getSuperclass();

        while (field == null && superClass != null) {
            try {
                field = superClass.getDeclaredField("id");
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }

        field.setAccessible(true);

        try {
            field.set(this, 50);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
