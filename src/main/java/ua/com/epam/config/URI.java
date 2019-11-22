package ua.com.epam.config;

public interface URI {
    DataProp dp = new DataProp();
    String BASE_URI = dp.apiProtocol() + "://" + dp.apiHost() + ":" + dp.apiPort();

    //Author
    String GET_AUTHOR_SINGLE_OBJ = "/api/library/author/%s";
    String GET_AUTHOR_OF_BOOK_OBJ = "/api/library/book/%s/author";
    String GET_ALL_AUTHORS_ARR = "/api/library/authors";
    String SEARCH_FOR_EXISTED_AUTHORS_ARR = "/api/library/authors/search";
    String GET_ALL_AUTHORS_IN_GENRE_ARR = "/api/library/genre/%s/authors";
    String POST_AUTHOR_SINGLE_OBJ = "/api/library/author";
    String PUT_AUTHOR_SINGLE_OBJ = "/api/library/author/%s";
    String DELETE_AUTHOR_SINGLE_OBJ = "/api/library/author/%s";
}
