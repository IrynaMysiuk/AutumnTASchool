package ua.com.epam;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ua.com.epam.business.AuthorBO;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Name;

import static org.apache.http.HttpStatus.*;

@Test(description = "")
public class CRUDAuthorTest extends BaseTest {
    private Author expAuthor = testData.authors().getRandomOne();

    private AuthorBO authorBO = new AuthorBO();

    @Test(description = "post single Author obj")
    public void postAuthor() {
        Response createdAuthor = authorBO.createAuthor(expAuthor);
        Assert.assertEquals(createdAuthor.getStatusCode(), SC_CREATED,
                "Incorrect status code for creating author!");
        Assert.assertEquals(expAuthor, authorBO.getActualAuthor(),
                "Author is not created for creating author");

        Author author = authorBO.getAuthor(expAuthor.getAuthorId().toString());
        Assert.assertEquals(author.getAuthorId(), expAuthor.getAuthorId(),
                "AuthorId is incorrect for creating author!");
    }

    @Test(description = "Put single Author obj")
    public void checkUpdateAuthor() {
        Response createdAuthor = authorBO.createAuthor(expAuthor);
        Assert.assertEquals(createdAuthor.getStatusCode(), SC_CREATED,
                "Incorrect status code for creating author!");
        Assert.assertEquals(expAuthor, authorBO.getActualAuthor(),
                "Author is not created for creating author");

        expAuthor.setAuthorName(new Name("Ivan", "Franko"));
        Response updatedAuthor = authorBO.updateAuthor(expAuthor);
        Assert.assertEquals(updatedAuthor.getStatusCode(), SC_OK,
                "Incorrect status code for updating author!");
        Assert.assertEquals(authorBO.getActualAuthor(), expAuthor,
                "The author is not the same after updating!");

        Author author = authorBO.getAuthor(expAuthor.getAuthorId().toString());
        Assert.assertEquals(author.getAuthorId(), expAuthor.getAuthorId(),
                "Author id is incorrect after updating!");
    }

    @Test(description = "Check delete single author")
    public void checkDeleteAuthor() {
        String expAuthorId = expAuthor.getAuthorId().toString();
        Response createdAuthor = authorBO.createAuthor(expAuthor);
        Assert.assertEquals(createdAuthor.getStatusCode(), SC_CREATED,
                "Incorrect status code for creating!");
        Assert.assertEquals(expAuthor, authorBO.getActualAuthor(),
                "Author is not created!");

        Author author = authorBO.getAuthor(expAuthorId);
        Assert.assertEquals(author.getAuthorId(), expAuthor.getAuthorId(),
                "AuthorId is incorrect for getting!");

        Response deletedAuthor = authorBO.deleteAuthor(expAuthorId);
        Assert.assertEquals(deletedAuthor.getStatusCode(), SC_NO_CONTENT,
                "The status code is incorrect after deleting");
    }

    @Test(description = "check creating several authors")
    public void checkCreateSeveralAuthors() {
        for (Author author : authorList) {
            AuthorBO newAuthorBO = new AuthorBO();
            Response createdAuthor = newAuthorBO.createAuthor(author);
            Assert.assertEquals(createdAuthor.getStatusCode(), SC_CREATED, "Incorrect status code!");
            Author authorResponse = newAuthorBO.getAuthor(author.getAuthorId().toString());
            Assert.assertEquals(authorResponse.getAuthorId(), author.getAuthorId(), "AuthorId is incorrect!");
        }
    }

    @AfterMethod
    public void cleanUp() {
        clean.authors();
    }
}
