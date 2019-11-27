package ua.com.epam;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ua.com.epam.entity.Fault;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.request.Request;

import static org.apache.http.HttpStatus.*;

public class NegativeCRUDTests extends BaseTest {


    @Test(description = "Verify conflict for duplicated authors")
    public void checkDuplicatedAuthors() {
        Response createdAuthor = authorService.createAuthor(expAuthor);
        validation.validateCreateAuthor(SC_CREATED, expAuthor, createdAuthor);

        Response createdDuplicateAuthor = authorService.createAuthor(expAuthor);
        validation.validateCreateAuthor(SC_CONFLICT, null, createdDuplicateAuthor);

        Fault conflictFault = authorService.getError(createdDuplicateAuthor);
        validation.validationConflictFault(conflictFault);
    }

    @Test(description = "Verify unknown authors")
    public void checkUnExistedAuthors() {
        Response createdAuthor = authorService.createAuthor(expAuthor);
        validation.validateCreateAuthor(SC_CREATED, expAuthor, createdAuthor);

        Response deletedAuthor = authorService.deleteAuthor(expAuthorId);
        validation.validateDeleteAuthor(SC_NO_CONTENT,deletedAuthor);

        Response deletedAgainAuthor = authorService.deleteAuthor(expAuthorId);
        validation.validateDeleteAuthor(SC_NOT_FOUND,deletedAgainAuthor);

        Fault nonExistedAuthorFault = authorService.getError(deletedAgainAuthor);
        validation.validationNotFoundFault(nonExistedAuthorFault,expAuthorId);
    }

    @Test(description = "Verify method not allowed")
    public void checkUnknownURL() {
        Request request = new Request().setTemplateURL("/api/library/author/");

        Response author = authorService.getResponseAuthor("");
        Fault methodNotAllowed = authorService.getError(author);
        validation.validationNotAllowedFault(methodNotAllowed,request.getTemplateURL());
    }

    @AfterMethod
    public void cleanUp() {
        clean.authors();
    }
}
