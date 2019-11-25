package ua.com.epam.core.service;

import org.apache.log4j.Logger;
import ua.com.epam.config.DataProp;
import ua.com.epam.core.mysql.MySQLClient;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Birth;
import ua.com.epam.entity.author.nested.Name;
import ua.com.epam.utils.data.AuthorData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ua.com.epam.utils.JsonKeys.*;
import static ua.com.epam.utils.helpers.SqlQuery.*;

public class AuthorDBData implements AuthorData {
    private static Logger log = Logger.getLogger(AuthorDBData.class);

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private DataProp dp;

    public AuthorDBData() {
        this.connection = MySQLClient.getConnection();
        this.dp = new DataProp();
    }

    @Override
    public Author getRandomOne() {
        log.info("Try to find one random Author...");
        execute(String.format(SELECT_RANDOM_ONE, dp.dbName(), AUTHOR));
        Author a = new Author();
        try {
            if (!resultSet.next()) {
                log.error("No one author was found! Author table is empty!");
            } else {
                a = mapResultSetObjToAuthor(resultSet);
                log.info("Author with authorId = " + a.getAuthorId() + " was found!");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return a;
    }

    //return 10 AUTHORS sortedBy authorId in ascending order
    @Override
    public List<Author> getDefaultAuthors(int... count) {
        int limit = count.length == 0 ? 10 : count[0];
        log.info(String.format("Try to find first %s AUTHORS...\n", limit));

        execute(String.format(SELECT_CUSTOMS, dp.dbName(), AUTHOR, AUTHOR_ID, limit));
        List<Author> authors = new ArrayList<>();
        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one author was found! Author table is empty!");
            } else {
                do {
                    authors.add(mapResultSetObjToAuthor(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " AUTHORS successfully!");
            }
        } catch (SQLException e) {
            log.error("DB access error occurs or method is called on a closed ResultSet!!!");
            e.printStackTrace();
        }

        close();
        return authors;
    }

    @Override
    public List<Author> getSorted(String sortBy, String order, int... count) {
        int limit = count.length == 0 ? 10 : count[0];

        log.info("Try to find first " + limit + " AUTHORS sorted by " + sortBy + " in " + order + " order...");
        execute(String.format(SELECT_CUSTOMS, dp.dbName(), AUTHOR, sortBy, order, limit));
        List<Author> authors = new ArrayList<>();

        try {
            int i = 0;
            if (!resultSet.next()) {
                log.error("No one author was found! Author table is empty!");
            } else {
                do {
                    authors.add(mapResultSetObjToAuthor(resultSet));
                    i++;
                } while (resultSet.next());
                log.info("Found " + i + " AUTHORS successfully!\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return authors;
    }

    private Author mapResultSetObjToAuthor(ResultSet rs) throws SQLException {
        return new Author(
                rs.getLong(AUTHOR_ID),
                new Name(
                        rs.getString(AUTHOR_FIRST_NAME),
                        rs.getString(AUTHOR_SECOND_NAME)),
                rs.getString(AUTHOR_NATIONALITY),
                new Birth(
                        rs.getDate(AUTHOR_BIRTH_DATE).toLocalDate(),
                        rs.getString(AUTHOR_BIRTH_COUNTRY),
                        rs.getString(AUTHOR_BIRTH_CITY)
                ),
                rs.getString(AUTHOR_DESCRIPTION)
        );
    }

    private void execute(String query) {
        try {
            statement = connection.createStatement();
            log.debug("Execute query: " + query + "...");
            statement.execute(query);

            resultSet = statement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        if (resultSet != null) {
            log.debug("Try to close ResultSet...");
            try {
                resultSet.close();
                log.debug("ResultSet was closed successfully!");
            } catch (SQLException e) {
                log.error("Database access error occurs! Can't close ResultSet!");
                e.printStackTrace();
            }
        }

        if (statement != null) {
            log.debug("Try to close Statement...");
            try {
                statement.close();
                log.debug("Statement was closed successfully!");
            } catch (SQLException e) {
                log.error("Database access error occurs! Can't close Statement!");
                e.printStackTrace();
            }
        }
    }
}
