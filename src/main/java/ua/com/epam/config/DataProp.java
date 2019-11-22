package ua.com.epam.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ua.com.epam.config.PropConstants.*;

public class DataProp {
    private Properties props = new Properties();

    public DataProp() {
        FileInputStream fis;

        try {
            fis = new FileInputStream("src/main/resources/data.properties");
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String dbHost() {
        return props.getProperty(DB_HOST);
    }

    private int dbPort() {
        return Integer.parseInt(props.getProperty(DB_PORT));
    }

    public String apiHost() {
        return props.getProperty(API_HOST);
    }

    public String apiProtocol() {
        return props.getProperty(API_PROTOCOL);
    }

    public int apiPort() {
        return Integer.parseInt(props.getProperty(API_PORT));
    }

    public String dbName() {
        return props.getProperty(DB_NAME);
    }

    public String dbUser() {
        return props.getProperty(DB_USER);
    }

    public String dbPass() {
        return props.getProperty(DB_PASSWORD);
    }

    public String jdbcDriver() {
        return props.getProperty(JDBC_DRIVER);
    }

    public String sqlURL() {
        return String.format(props.getProperty(DB_URL), dbHost(), dbPort(), dbName());
    }
}