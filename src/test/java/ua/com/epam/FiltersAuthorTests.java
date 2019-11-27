package ua.com.epam;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ua.com.epam.config.TemplatesURI;
import ua.com.epam.core.service.AuthorFileData;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Name;
import ua.com.epam.entity.request.Request;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpStatus.SC_CREATED;
import static ua.com.epam.utils.JsonKeys.*;
import static ua.com.epam.utils.helpers.Constants.TEST_EXPECTED_SIZE;

public class FiltersAuthorTests extends BaseTest {

    @BeforeClass
    public void prepareAuthors() {
        for (Author author : expAuthorList) {
            Response createdAuthor = authorService.createAuthor(author);
            validation.validateCreateAuthor(SC_CREATED, author, createdAuthor);
        }
    }

    @Test(description = "Verify author size")
    public void checkSizeAuthors() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSize(TEST_EXPECTED_SIZE);
        List<Author> authors = authorService.getAuthors(request);
        validation.validateAuthorSize(authors.size(), TEST_EXPECTED_SIZE);
    }

    @Test(description = "Verify author page")
    public void checkPageAuthors() {
        int authorsSum = 0;
        for (int page = 1; page <= Math.round(expAuthorList.size() / TEST_EXPECTED_SIZE); page++) {
            Request request = new Request()
                    .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                    .setPage(page)
                    .setSize(TEST_EXPECTED_SIZE);
            List<Author> authors = authorService.getAuthors(request);

            validation.validateAuthorSizeOnPage(authors.size(), TEST_EXPECTED_SIZE, page);
            authorsSum += authors.size();
        }
        validation.validateAuthorSize(authorsSum, expAuthorList.size());
    }

    @Test(description = "Verify orderType for author")
    public void checkOrderTypeAsc() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSortBy(AUTHOR_FIRST_NAME)
                .setOrderType(ASCENDING);
        List<Author> authors = authorService.getAuthors(request);
        List<String> actFirstNames = authors.stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        List<String> expectedFirstNames = new AuthorFileData()
                .getSorted(AUTHOR_FIRST_NAME, ASCENDING).stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        validation.validateSortingByFirstName(actFirstNames, expectedFirstNames, ASCENDING);
    }

    @Test(description = "Verify orderType for author")
    public void checkOrderTypeDesc() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSortBy(AUTHOR_FIRST_NAME)
                .setOrderType(DESCENDING);
        List<Author> authors = authorService.getAuthors(request);
        List<String> firstNames = authors.stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        List<String> expectedFirstNames = new AuthorFileData()
                .getSorted(AUTHOR_FIRST_NAME, DESCENDING).stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        validation.validateSortingByFirstName(firstNames, expectedFirstNames, DESCENDING);
    }

    @Test(description = "Verify sortBy for author")
    public void checkSortBy() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSortBy("authorName.second");
        List<Author> authors = authorService.getAuthors(request);
        List<String> secondNames = authors.stream()
                .map(Author::getAuthorName)
                .map(Name::getSecond)
                .collect(Collectors.toList());
        validation.validationSortingBySecondName(secondNames);
    }

    @Test(description = "Verify author pagination='false' ")
    public void checkPaginationAuthors() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSize(5)
                .setPagination(false);
        List<Author> authors = authorService.getAuthors(request);
        validation.validateAuthorExpectedSize(authors.size());
        validation.validateAuthorSize(authors.size(), expAuthorList.size());
    }

    @AfterClass
    public void cleanUp() {
        clean.authors();
    }
}
