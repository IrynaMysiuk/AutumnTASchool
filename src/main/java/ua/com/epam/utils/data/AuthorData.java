package ua.com.epam.utils.data;

import ua.com.epam.entity.author.Author;

import java.util.List;

public interface AuthorData {
    Author getRandomOne();

    List<Author> getDefaultAuthors();

    List<Author> getSorted(String keyToSortBy, String order, int... count);
}
