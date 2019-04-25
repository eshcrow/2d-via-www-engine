package com.company.drivers.database;

import com.company.drivers.database.interfaces.TableName;
import com.company.server.game.GameServer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBaseModel <T extends DataBaseModel <T>> extends DataBase implements TableName {

    public String tableName;
    private List<String[]> subclassFields = new ArrayList<>();
    private List<T> subclassObjects = new ArrayList<>();
    private Field field;
    private int currentObjectIndex = -1;
    private int subclassAmount = 0;

    /**
     * Get subclass variables type and name and set it to subclassFields.
     */
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

            name = nameSplits[nameSplits.length - 1];

            this.subclassFields.add(
                    new String[] {
                            name, type
                    }
            );
        }

        this.setTable(this.setTableName());
    }

    /**
     * Prepare or execute current SQL request in superclass.
     * Next results is read from Results and his values are appended to new subclass instance.
     * Next prepared subclass is added to subclassObjects list.
     * @return subclass object.
     */
    public T get () {
        this.select();
        Results results =  this.results();

        while (results.next()) {
            this.subclassAmount++;
            T newSubclassObject = newInstance();

            for (String[] i : this.subclassFields) {
                if (
                        i[1].equals("private") ||
                        i[1].equals("protected") ||
                        results.getString(i[0]) == null
                ) {
                    continue;
                }

                switch (i[1]) {
                    case "int":
                        newSubclassObject.setField(i[0]).setFieldValue(results.getInt(i[0]));
                        break;
                    case "string":
                        newSubclassObject.setField(i[0]).setFieldValue(results.getString(i[0]));
                        break;
                    case "byte":
                        newSubclassObject.setField(i[0]).setFieldValue(results.getByte(i[0]));
                        break;
                }
            }

            this.subclassObjects.add(newSubclassObject);
        }

        return (T) this;
    }

    public void save () {

    }

    public int getSubclassAmount () {
        return this.subclassAmount;
    }

    @Override
    public T where (String columnLabel, String operator) {
        this.operation(columnLabel, operator);
        return (T) this;
    }

    @Override
    public T or (String columnLabel, String operator) {
        this.operation(columnLabel, operator);
        return (T) this;
    }

    @Override
    public T and (String columnLabel, String operator) {
        this.operation(columnLabel, operator);
        return (T) this;
    }

    @Override
    public T set (int value) {
        this.queue.add("int");
        this.intValues.add(value);
        return (T) this;
    }

    @Override
    public T set (String value) {
        this.queue.add("str");
        this.strValues.add(value);
        return (T) this;
    }

    /**
     * @return false if current index not equals to results objects size.
     *
     * it supports while loop to access to subclassObjects
     */
    public Boolean next () {
        this.currentObjectIndex++;

        return this.currentObjectIndex != this.subclassObjects.size();
    }

    /**
     * @return Subclass object from subclassObjects list.
     *
     * it supports while loop to access to subclassObjects
     */
    public T getObject () {
        return this.subclassObjects.get(this.currentObjectIndex);
    }

    /**
     * @return first subclass object from subclassObjects list.
     */
    public T getFirst () {
        if (this.subclassObjects.size() == 0)
            return null;
        return this.subclassObjects.get(0);
    }

    public String setTableName () {
        return tableName;
    }

    /**
     * @return new subclass instance.
     */
    protected abstract T newInstance ();

    /**
     * Here subclass variable is prepare to set value.
     * @param fieldName subclass variable name.
     * @return DataBaseModel
     */
    public DataBaseModel setField (String fieldName) {
        this.field = null;

        try {
            this.field = this.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            this.field = null;
        }

        Class superClass = this.getClass().getSuperclass();

        while (this.field == null && superClass != null) {
            try {
                this.field = superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                superClass = superClass.getSuperclass();
            }
        }

        this.field.setAccessible(true);

        return this;
    }

    /**
     * Set subclass variable with suitable data type.
     * @param value Variable typed value.
     */
    private void setFieldValue (int value) {
        try {
            this.field.set(this, value);
            this.field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setFieldValue (String value) {
        try {
            this.field.set(this, value);
            this.field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setFieldValue (byte value) {
        try {
            this.field.set(this, value);
            this.field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
