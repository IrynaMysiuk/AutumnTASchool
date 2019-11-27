package ua.com.epam.validation;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ua.com.epam.entity.Fault;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.epam.utils.helpers.Constants.TEST_EXPECTED_SIZE;

public class Validation {
    private AuthorService authorService;

    public Validation(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void validateCreateAuthor(int expStatusCode, Author expAuthor, Response response) {

        Assert.assertEquals(response.getStatusCode(), expStatusCode,
                "Incorrect status code for creating author!");
        if (expAuthor != null)
            Assert.assertEquals(authorService.getActualAuthor(), expAuthor,
                    "Author is not created for creating author");
    }

    public void validateUpdateAuthor(int expStatusCode, Author expAuthor, Response response) {
        Assert.assertEquals(response.getStatusCode(), expStatusCode,
                "Incorrect status code for updating author!");
        Assert.assertEquals(authorService.getActualAuthor(), expAuthor,
                "The author is not the same after updating!");

    }

    public void validateDeleteAuthor(int expStatusCode, Response response) {
        Assert.assertEquals(response.getStatusCode(), expStatusCode,
                "The status code is incorrect after deleting");
    }

    public void validateAuthorId(Author expAuthor, Author ectAuthor) {
        Assert.assertEquals(ectAuthor.getAuthorId(), expAuthor.getAuthorId(),
                "AuthorId is incorrect!");
    }

    public void validateAuthorName(Author expAuthor, Author ectAuthor) {
        Assert.assertEquals(ectAuthor.getAuthorName(), expAuthor.getAuthorName(),
                "Author name is incorrect!");
    }

    public void validateAuthorSize(int actSize, int expSize) {
        Assert.assertEquals(actSize, expSize, "The size is incorrect");
    }

    public void validateAuthorExpectedSize(int authorsSize) {
        Assert.assertTrue(authorsSize > TEST_EXPECTED_SIZE, "The size is incorrect");
    }

    public void validateAuthorSizeOnPage(int actSize, int expSize, int page) {
        Assert.assertTrue(actSize <= expSize,
                "The size is incorrect on page " + page);
    }

    public void validateSortingByFirstName(List<String> actFirstNames, List<String> expectedFirstNames, String orderBy) {
        SoftAssert softAssert = new SoftAssert();
        for (int counter = 0; counter < actFirstNames.size(); counter++) {
            softAssert.assertEquals(actFirstNames.get(counter), expectedFirstNames.get(counter),
                    " Author list is not sorted  by first name for " + orderBy);
        }
        softAssert.assertAll();
    }

    public void validationSortingBySecondName(List<String> secondNames) {
        Assert.assertEquals(secondNames, secondNames.stream().sorted().collect(Collectors.toList()),
                " Author list is not sorted by second name");
    }

    public void validationConflictFault(Fault conflictFault) {
        Assert.assertEquals(conflictFault.getError(), "Conflict",
                "Error message is not correct");
        Assert.assertEquals(conflictFault.getErrorMessage(), "Author with such 'authorId' already exists!",
                "Message is not correct!");
    }
    public void validationNotFoundFault(Fault nonExistedAuthorFault,String expAuthorId){
        Assert.assertEquals(nonExistedAuthorFault.getError(), "Not Found",
                "Error message is not correct");
        Assert.assertEquals(nonExistedAuthorFault.getErrorMessage(),
                String.format("Author with 'authorId' = '%s' doesn't exist!", expAuthorId),
                "Message is not correct!");
    }
    public void validationNotAllowedFault(Fault methodNotAllowed, String url){
        Assert.assertEquals(methodNotAllowed.getError(), "Method Not Allowed");
        Assert.assertEquals(methodNotAllowed.getErrorMessage(),
                String.format("Method 'GET' mapped by '%s' not supported!", url),
                "Message is not correct!");
    }
    public void validationQueryFirstName(List<String>firstNames,String expQuery){
        Assert.assertFalse(firstNames.contains(expQuery), "FirstName contains incorrect query! ");
    }
    public void validationIncorrectQuery(List<Author> authors){
        Assert.assertEquals(authors.size(), 0, "FirstName contains incorrect query! ");
    }

}
