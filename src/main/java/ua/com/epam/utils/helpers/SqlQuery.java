package ua.com.epam.utils.helpers;

public interface SqlQuery {
    String AUTHOR = "author";

    String SELECT_RANDOM_ONE = "SELECT * FROM `%s`.`%s` ORDER BY RAND() LIMIT 1";
    String SELECT_DEFAULTS = "SELECT * FROM `%s`.`%s` ORDER BY `%s` ASC LIMIT 10";
    String SELECT_CUSTOMS = "SELECT * FROM `%s`.`%s` ORDER BY `%s` %s LIMIT %d";
}