package utils;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigManager {
    private static INIConfiguration config;
    private static INIConfiguration endpoints;
    private static String env = System.getProperty("env", "CI"); // Default to "CI" if not specified

    static {
        try {
            Configurations configs = new Configurations();
            config = configs.ini(new File("src/main/resources/config.ini"));
            endpoints = configs.ini(new File("src/main/resources/apiendpoints.ini"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load INI configuration files.");
        }
    }

    public static String getBaseUrl() {
        return config.getString(env + ".BASE_URI");  // Fetch BASE_URL from the correct section
    }

    public static String getEndpoint(String key) {
        return endpoints.getString("ENDPOINTS." + key);  // Fetch endpoint from the correct section
    }

    public static String getEnvironment() {
        return env;
    }
}







/*
package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties configProperties = new Properties();
    private static Properties endpointProperties = new Properties();
    private static String env = System.getProperty("env", "CI");

    static {
        try {
            FileInputStream configFile = new FileInputStream("src/main/resources/config.ini");
            FileInputStream endpointsFile = new FileInputStream("src/main/resources/apiendpoints.ini");
            configProperties.load(configFile);
            endpointProperties.load(endpointsFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration files.");
        }
    }

    public static String getBaseUrl() {
        return configProperties.getProperty(env + ".BASE_URI");
    }

    public static String getEndpoint(String key) {
        return endpointProperties.getProperty(key);
    }

    public static String getEnvironment() {
        return env;
    }
}
*/
