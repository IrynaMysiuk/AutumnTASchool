package ua.com.epam.utils.data.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.exception.FileIsEmptyException;
import ua.com.epam.utils.data.AuthorData;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ua.com.epam.utils.JsonKeys.*;

public class AuthorFileData implements AuthorData {
    private static Logger log = Logger.getLogger(AuthorFileData.class);

    private String filePath = "src/test/resources/test-data/authors";
    private Gson g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    /**
     * Get one random Author object from file
     *
     * @return Author instance
     */
    @Override
    public Author getRandomOne() {
        log.info("Try to find one random Author...\n");

        List<Author> authors = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            authors = lines.map(s -> g.fromJson(s, Author.class)).collect(Collectors.toList());
            if (authors.isEmpty()) {
                log.error("File by path " + filePath + " is empty!");
                throw new FileIsEmptyException();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        Author a = authors.get(new Random().nextInt(authors.size()));
        log.info("Author with authorId = '" + a.getAuthorId() + "' was found!");

        return a;
    }

    /**
     * Get first 10 authors sortedBy authorId in ascending order
     * <p>
     * How it works:
     * 1. get all rows from file and parse them to List of Author objects
     * 2. sort List of Authors objects by authorId
     * 3. limit by 10 first values
     *
     * @return List of Authors objects
     */
    @Override
    public List<Author> getDefaultAuthors(int... count) {
        int limit = count.length == 0 ? 10 : count[0];
        log.info(String.format("Try to find first %s authors...\n", limit));

        List<Author> authors = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath)).limit(limit)) {
            authors = lines.map(s -> g.fromJson(s, Author.class)).collect(Collectors.toList());
            if (authors.isEmpty()) {
                log.error("File by path " + filePath + " is empty!");
                throw new FileIsEmptyException();
            }

            if (authors.size() < limit) {
                log.warn("There are only " + authors.size() + " authors found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        authors = sort(authors, AUTHOR_ID, ASCENDING, limit);
        log.info(authors.size() + " authors found!");

        return authors;
    }

    /**
     * Get first N (or 10 if not specified) authors sorted by custom key in custom (asc/desc) order
     * <p>
     * How it works:
     * 1. get all rows from file and parse them to List of Author objects
     * 2. sort List of Authors objects by your custom sort key
     * 3. limit by N first values
     *
     * @param keyToSortBy key to sort objects by (e. g. authorName.second) -> String value (required)
     * @param order       order by parameter (asc or desc) -> String value (required)
     * @param count       count of objects to get -> Integer value (optional, if not specified will set as 10)
     * @return List of Authors objects
     */
    @Override
    public List<Author> getSorted(String keyToSortBy, String order, int... count) {
        int limit = count.length == 0 ? 10 : count[0];
        log.info("Try to find first " + limit + " authors sorted by '" + keyToSortBy + "' in " + order + " order...");

        List<Author> authors = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath)).limit(limit)) {
            authors = lines.map(s -> g.fromJson(s, Author.class)).collect(Collectors.toList());

            if (authors.isEmpty()) {
                log.error("File by path " + filePath + " is empty!");
                throw new FileIsEmptyException();
            }

            if (authors.size() < limit) {
                log.warn("There are only " + authors.size() + " authors found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        authors = sort(authors, keyToSortBy, order, limit);
        log.info("Were found + " + authors.size() + " sorted by '" + keyToSortBy + "' in " + order + " order!");

        return authors;
    }

    private List<Author> sort(List<Author> authorsToSort, String keyToSort, String order, int limit) {
        Map<Object, Author> sortedAuthors = new TreeMap<>();

        if (order.equals(DESCENDING)) {
            sortedAuthors = new TreeMap<>(Collections.reverseOrder());
        }

        String[] keys = keyToSort.split("\\.");
        int len = keys.length;

        for (Author a : authorsToSort) {
            Object objToGetFrom = a;
            Class clazz = a.getClass();

            try {
                Field field = null;
                for (int i = 0; i < len; i++) {
                    field = clazz.getDeclaredField(keys[i]);
                    field.setAccessible(true);
                    clazz = field.getType();
                    if (i < len - 1) {
                        objToGetFrom = field.get(objToGetFrom);
                    }
                    field.setAccessible(false);
                }

                if (field != null) {
                    field.setAccessible(true);
                    sortedAuthors.put(field.get(objToGetFrom), a);
                    field.setAccessible(false);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return sortedAuthors.values()
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
