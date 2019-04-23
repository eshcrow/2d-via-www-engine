package com.company.temporary.images;

import com.company.drivers.database.DataBaseModel;
import com.company.drivers.database.interfaces.TableName;

public class Image extends DataBaseModel <Image> implements TableName {

    public int id;
    public String file_name;

    @Override
    public String setTableName () {
        return "images";
    }

    public Image newInstance () { return new Image(); }

}
