package ua.com.epam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.BeforeSuite;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.CleanUpService;
import ua.com.epam.utils.DataFactory;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;
import java.util.List;

public class BaseTest {
    //to parse JSON String to needed model (with correct date parsing possibility)
    protected Gson g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    protected List<Author> authorList;
    protected RestClient client = new RestClient();
    protected DataFactory testData = new DataFactory();
    protected CleanUpService clean = new CleanUpService(client);

    //don't delete this!!!
    @BeforeSuite
    public void reinitialize() {
        client = new RestClient();
        testData = new DataFactory();
        clean = new CleanUpService(client);
        authorList = testData.authors().getDefaultAuthors();
    }

}
