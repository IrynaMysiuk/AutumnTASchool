package ua.com.epam.config;

public enum TemplatesURI {
    GET_AUTHOR_SINGLE_OBJ("/api/library/author/%s"),
    GET_AUTHOR_OF_BOOK_OBJ("/api/library/book/%s/author"),
    GET_ALL_AUTHORS_ARR("/api/library/authors"),
    SEARCH_FOR_EXISTED_AUTHORS_ARR("/api/library/authors/search"),
    GET_ALL_AUTHORS_IN_GENRE_ARR("/api/library/genre/%s/authors"),
    POST_AUTHOR_SINGLE_OBJ("/api/library/author"),
    PUT_AUTHOR_SINGLE_OBJ("/api/library/author/%s"),
    DELETE_AUTHOR_SINGLE_OBJ("/api/library/author/%s");
    private String URI;
    private String id;

    TemplatesURI(String URI) {
        this.URI = URI;
    }

    public String getURI() {
        return URI;
    }

    public TemplatesURI setId(String id) {
        this.id = id;
        URI = String.format(URI, id);
        return this;
    }

    public TemplatesURI clearUri() {
        if (id != null) {
            URI = URI.replace(id, "%s");
        }
        return this;
    }
}
