package ua.com.epam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.BeforeClass;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.AuthorService;
import ua.com.epam.service.CleanUpService;
import ua.com.epam.utils.DataFactory;
import ua.com.epam.utils.helpers.LocalDateAdapter;
import ua.com.epam.validation.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    //to parse JSON String to needed model (with correct date parsing possibility)
    protected Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    List<Author> expAuthorList;
    private RestClient client;
    private DataFactory testData;
    CleanUpService clean;
    AuthorService authorService;
    Author expAuthor;
    String expAuthorId;
    Validation validation;
    //don't delete this!!!
    @BeforeClass
    public void reinitialize() {
        client = new RestClient();
        testData = new DataFactory();
        clean = new CleanUpService(client);
        expAuthorList = new ArrayList<>();
        expAuthorList.addAll(testData.authors().getDefaultAuthors());
        authorService = new AuthorService(client);
        expAuthor = testData.authors().getRandomOne();
        expAuthorId=expAuthor.getAuthorId().toString();
        validation= new Validation(authorService);
    }

}
