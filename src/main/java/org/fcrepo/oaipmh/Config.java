package org.fcrepo.oaipmh;

import java.util.Properties;
import java.lang.ClassLoader;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Config {

    private Properties properties;

    private Logger logger = LoggerFactory.getLogger(Config.class);

    public Config() {
        this.properties = new Properties();
        ClassLoader loader = getClass().getClassLoader();
        try (InputStream in = loader.getResourceAsStream("classpath:application.properties")) {
            properties.load(in);
        } catch (Exception e) {
            logger.error("Error loading application configuration");
        }
    }

    public String getProperty(String propertyName) {
        String  property = null;
        try {
            property = properties.getProperty(propertyName, "");
        } catch (Exception e) {
            logger.error("TODO Catch property exception");
        }
        return property;
    }



}
