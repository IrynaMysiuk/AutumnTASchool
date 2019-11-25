package ua.com.epam.utils;

import ua.com.epam.core.service.AuthorFileData;
import ua.com.epam.utils.data.AuthorData;

public class DataFactory {

    public AuthorData authors() {
        return new AuthorFileData();
    }
}