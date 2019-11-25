package ua.com.epam;

import org.testng.Assert;
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
        for (Author author : authorList) {
            Response createdAuthor = authorBO.createAuthor(author);
            Assert.assertEquals(createdAuthor.getStatusCode(), SC_CREATED, "Authors is not add!");
        }
    }

    @Test(description = "Verify author size")
    public void checkSizeAuthors() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSize(TEST_EXPECTED_SIZE);
        List<Author> authors = authorBO.getAuthors(request);
        Assert.assertEquals(authors.size(), TEST_EXPECTED_SIZE, "The size is incorrect");
    }

    @Test(description = "Verify author page")
    public void checkPageAuthors() {
        int authorsSum = 0;
        for (int page = 1; page <= Math.round(authorList.size() / TEST_EXPECTED_SIZE); page++) {
            Request request = new Request()
                    .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                    .setPage(page)
                    .setSize(TEST_EXPECTED_SIZE);
            List<Author> authors = authorBO.getAuthors(request);

            Assert.assertTrue(authors.size() <= TEST_EXPECTED_SIZE,
                    "The size is incorrect on page " + page);
            authorsSum += authors.size();
        }
        Assert.assertEquals(authorsSum, authorList.size(), "The sum of authors is not correct");
    }

    @Test(description = "Verify orderType for author")
    public void checkOrderTypeAsc() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSortBy(AUTHOR_FIRST_NAME)
                .setOrderType(ASCENDING);
        List<Author> authors = authorBO.getAuthors(request);
        List<String> actFirstNames = authors.stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        List<String> expectedFirstNames = new AuthorFileData()
                .getSorted(AUTHOR_FIRST_NAME, ASCENDING).stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        Assert.assertEquals(actFirstNames, expectedFirstNames,
                " Author list is not sorted  by first name for asc");
    }

    @Test(description = "Verify orderType for author")
    public void checkOrderTypeDesc() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSortBy(AUTHOR_FIRST_NAME)
                .setOrderType(DESCENDING);
        List<Author> authors = authorBO.getAuthors(request);
        List<String> firstNames = authors.stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        List<String> expectedFirstNames = new AuthorFileData()
                .getSorted(AUTHOR_FIRST_NAME, DESCENDING).stream()
                .map(Author::getAuthorName)
                .map(Name::getFirst)
                .collect(Collectors.toList());
        Assert.assertEquals(firstNames, expectedFirstNames,
                " Author list is not sorted  by first name for desc");
    }

    @Test(description = "Verify sortBy for author")
    public void checkSortBy() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSortBy("authorName.second");
        List<Author> authors = authorBO.getAuthors(request);
        List<String> secondNames = authors.stream()
                .map(Author::getAuthorName)
                .map(Name::getSecond)
                .collect(Collectors.toList());
        Assert.assertEquals(secondNames, secondNames.stream().sorted().collect(Collectors.toList()),
                " Author list is not sorted by second name");
    }

    @Test(description = "Verify author pagination='false' ")
    public void checkPaginationAuthors() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setSize(5)
                .setPagination(false);
        List<Author> authors = authorBO.getAuthors(request);
        Assert.assertTrue(authors.size() > 5, "The size is incorrect");
        Assert.assertEquals(authors.size(), authorList.size(), "The list is not the same");
    }

    @AfterClass
    public void cleanUp() {
        clean.authors();
    }
}
