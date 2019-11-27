package ua.com.epam;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Name;
import ua.com.epam.validation.Validation;

import static org.apache.http.HttpStatus.*;


public class CRUDAuthorTest extends BaseTest {


    @Test(description = "post single Author obj")
    public void postAuthor() {

        Response createdAuthor = authorService.createAuthor(expAuthor);
        validation.validateCreateAuthor(SC_CREATED, expAuthor, createdAuthor);

        Author author = authorService.getAuthor(expAuthor.getAuthorId().toString());
        validation.validateAuthorId(expAuthor, author);

    }

    @Test(description = "Put single Author obj")
    public void checkUpdateAuthor() {
        Response createdAuthor = authorService.createAuthor(expAuthor);
        validation.validateCreateAuthor(SC_CREATED, expAuthor, createdAuthor);

        expAuthor.setAuthorName(new Name("Ivan", "Franko"));
        Response updatedAuthor = authorService.updateAuthor(expAuthor);
        validation.validateUpdateAuthor(SC_OK, expAuthor, updatedAuthor);
        Author author = authorService.getAuthor(expAuthor.getAuthorId().toString());
        validation.validateAuthorName(expAuthor, author);
    }

    @Test(description = "Check delete single author")
    public void checkDeleteAuthor() {
        String expAuthorId = expAuthor.getAuthorId().toString();
        Response createdAuthor = authorService.createAuthor(expAuthor);
        validation.validateCreateAuthor(SC_CREATED, expAuthor, createdAuthor);
        Author author = authorService.getAuthor(expAuthorId);

        validation.validateAuthorId(expAuthor, author);
        Response deletedAuthor = authorService.deleteAuthor(expAuthorId);
        validation.validateDeleteAuthor(SC_NO_CONTENT, deletedAuthor);
    }

    @Test(description = "check creating several authors")
    public void checkCreateSeveralAuthors() {
        for (Author expAuthor : expAuthorList) {
            Response createdAuthor = authorService.createAuthor(expAuthor);
            validation.validateCreateAuthor(SC_CREATED, expAuthor, createdAuthor);
            Author authorResponse = authorService.getAuthor(expAuthor.getAuthorId().toString());
            validation.validateAuthorId(expAuthor, authorResponse);
        }
    }

    @AfterMethod
    public void cleanUp() {
        clean.authors();
    }
}
