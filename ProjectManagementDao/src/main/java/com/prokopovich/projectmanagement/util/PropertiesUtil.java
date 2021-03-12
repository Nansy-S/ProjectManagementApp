package com.prokopovich.projectmanagement.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public Properties getProperties(String propertiesFile) {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/mysql.properties");
            properties.load(fis);
            return properties;
        } catch (IOException ex) {
            return null;
        }
    }
}
