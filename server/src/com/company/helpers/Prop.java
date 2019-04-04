package com.company.helpers;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.net.URL;

public class Prop {

    private Properties properties;

    public Prop (String fileName) {
        if (this.properties == null) {
            this.properties = new Properties();
            URL url = this.getClass().getResource(fileName);
            File file = new File(url.getPath());

            try (InputStream propsFile = new FileInputStream(file)) {
                this.properties.load(propsFile);
            } catch(IOException exc) {
                assert true : "Can't read properties file!";
            }
        }
    }

    public int getAsInt (String propertiesName) {
        int number;

        try {
            number = Integer.parseInt(this.get(propertiesName));
        } catch (NumberFormatException exception) {
            number = 0;
        }

        return number;
    }

    public String get (String propertiesName) {
        return this.properties.getProperty(propertiesName);
    }

}
