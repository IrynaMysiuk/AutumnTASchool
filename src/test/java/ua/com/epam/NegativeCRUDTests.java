package ua.com.epam;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.epam.business.AuthorBO;
import ua.com.epam.entity.Fault;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.request.Request;

import static org.apache.http.HttpStatus.*;

public class NegativeCRUDTests extends BaseTest {
    private Author expAuthor = testData.authors().getRandomOne();
    private AuthorBO authorBO = new AuthorBO();
    String expAuthorId = expAuthor.getAuthorId().toString();

    @Test(description = "Verify conflict for duplicated authors")
    public void checkDuplicatedAuthors() {
        Response createdAuthor = authorBO.createAuthor(expAuthor);
        Assert.assertEquals(createdAuthor.getStatusCode(), SC_CREATED,
                "Incorrect status code for creating!");
        Assert.assertEquals(expAuthor, authorBO.getActualAuthor(),
                "Author is not created!");
        Response createdDuplicatedAuthor = authorBO.createAuthor(expAuthor);
        Assert.assertEquals(createdDuplicatedAuthor.getStatusCode(), SC_CONFLICT,
                "Incorrect status code for conflict!");

        Fault conflictFault = authorBO.getError(createdDuplicatedAuthor);
        Assert.assertEquals(conflictFault.getError(), "Conflict",
                "Error message is not correct");
        Assert.assertEquals(conflictFault.getErrorMessage(), "Author with such 'authorId' already exists!",
                "Message is not correct!");
    }

    @Test(description = "Verify unknown authors")
    public void checkUnknownAuthors() {

        Response createdAuthor = authorBO.createAuthor(expAuthor);
        Assert.assertEquals(createdAuthor.getStatusCode(), SC_CREATED,
                "Incorrect status code for creating!");
        Assert.assertEquals(expAuthor, authorBO.getActualAuthor(),
                "Author is not created!");

        Response deletedAuthor = authorBO.deleteAuthor(expAuthorId);
        Assert.assertEquals(deletedAuthor.getStatusCode(), SC_NO_CONTENT,
                "The status code is incorrect after deleting");
        Response deletedAgainAuthor = authorBO.deleteAuthor(expAuthorId);
        Assert.assertEquals(deletedAgainAuthor.getStatusCode(), SC_NOT_FOUND,
                "The status code is incorrect after double delete");

        Fault nonExistedAuthorFault = authorBO.getError(deletedAgainAuthor);
        Assert.assertEquals(nonExistedAuthorFault.getError(), "Not Found",
                "Error message is not correct");
        Assert.assertEquals(nonExistedAuthorFault.getErrorMessage(),
                String.format("Author with 'authorId' = '%s' doesn't exist!", expAuthorId),
                "Message is not correct!");
    }

    @Test(description = "Verify method not allowed")
    public void checkIncorrectURL() {
        Request request = new Request().setTemplateURL("/api/library/author/");

        Response author = authorBO.getResponseAuthor("");
        Fault methodNotAllowed = authorBO.getError(author);
        Assert.assertEquals(methodNotAllowed.getError(), "Method Not Allowed");
        Assert.assertEquals(methodNotAllowed.getErrorMessage(),
                String.format("Method 'GET' mapped by '%s' not supported!", request.getTemplateURL()),
                "Message is not correct!");
    }
}
