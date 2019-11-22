package ua.com.epam.core.mysql;

import org.apache.log4j.Logger;
import ua.com.epam.config.DataProp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLClient {
    private static Logger log = Logger.getLogger(MySQLClient.class);

    private static DataProp props = new DataProp();
    private static Connection connection;

    private MySQLClient() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (MySQLClient.class) {
                try {
                    Class.forName(props.jdbcDriver());
                    log.debug("Try connect to \'" + props.dbName()
                            + "\' with user: \'" + props.dbUser() + "\' and password: \'" + props.dbPass() + "\' ...");

                    connection = DriverManager.getConnection(props.sqlURL(), props.dbUser(), props.dbPass());
                    log.debug("Connected to \'" + props.dbName() + "\' database successfully!");
                } catch (ClassNotFoundException e) {
                    log.error("Class \'" + props.jdbcDriver() + "\' not found!");
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                log.debug("DB connection was closed successfully!");
            } catch (SQLException e) {
                log.error("Database access error occurs! Cannot close Connection!!!");
            }
        }
    }
}
