package ua.com.epam.utils;

import ua.com.epam.utils.data.AuthorData;
import ua.com.epam.utils.data.service.AuthorFileData;

public class DataFactory {

    public AuthorData authors() {
        return new AuthorFileData();
    }
}