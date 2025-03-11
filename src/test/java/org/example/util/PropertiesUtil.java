package org.example.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final String rootPath = System.getProperty("user.dir") + "/src/test/resources/";
    private static final String appConfigPath = rootPath + "config.properties";

    private static Properties appProps = null;

    public PropertiesUtil(){}

    public static Properties getInstance() {
        if (appProps == null) {
            try {
                appProps = new Properties();
                appProps.load(new FileInputStream(appConfigPath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return appProps;
    };

}
