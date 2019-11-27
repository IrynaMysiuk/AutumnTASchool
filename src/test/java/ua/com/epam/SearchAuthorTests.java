package ua.com.epam;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.epam.config.TemplatesURI;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Name;
import ua.com.epam.entity.request.Request;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.epam.utils.helpers.Constants.EXPECTED_QUERIES;

public class SearchAuthorTests extends BaseTest {
    @Test(description = "Verify correct search")
    public void checkCorrectSearch() {
        for (String expQuery : EXPECTED_QUERIES) {
            Request request = new Request()
                    .setTemplateURL(TemplatesURI.SEARCH_FOR_EXISTED_AUTHORS_ARR)
                    .setQuery(expQuery);
            List<Author> authors = authorService.getAuthors(request);
            List<String> firstNames = authors.stream()
                    .map(Author::getAuthorName)
                    .map(Name::getFirst)
                    .collect(Collectors.toList());
            validation.validationQueryFirstName(firstNames,expQuery);
        }
    }

    @Test(description = "Verify incorrect search")
    public void checkIncorrectSearch() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.SEARCH_FOR_EXISTED_AUTHORS_ARR)
                .setQuery("Aab");
        List<Author> authors = authorService.getAuthors(request);
        validation.validationIncorrectQuery(authors);
    }

}
