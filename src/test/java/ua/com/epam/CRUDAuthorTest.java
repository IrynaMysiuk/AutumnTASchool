package ua.com.epam;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.request.Request;

import java.util.List;

import static ua.com.epam.config.URI.GET_ALL_AUTHORS_ARR;
import static ua.com.epam.config.URI.POST_AUTHOR_SINGLE_OBJ;

@Test(description = "")
public class CRUDAuthorTest extends BaseTest {
    private Author expAuthor = testData.authors().getRandomOne();
    private List<Author> authorList = testData.authors().getDefaultAuthors();

    @BeforeMethod
    public void sendAuthors() {
        for(Author a : authorList) {
            client.post(POST_AUTHOR_SINGLE_OBJ, a);
        }
    }

    @Test(description = "post single Author obj")
    public void postAuthor() {
        client.post(POST_AUTHOR_SINGLE_OBJ, expAuthor);

        int statusCode = client.getResponse().getStatusCode();
        String body = client.getResponse().getBody();

        Author actAuthor = g.fromJson(body, Author.class);

        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(expAuthor, actAuthor);
    }

    @Test
    public void getDifferentAuthors() {
        //String params = "?orderType=asc&page=1&pagination=true&size=5&sortBy=authorId";

        Request request=new Request().setOrderType("asc").setPage(1).setPagination(true).setSize(5).setSortBy("authorId");
        client.get(GET_ALL_AUTHORS_ARR + request.toString());
        int statusCode = client.getResponse().getStatusCode();
        String body = client.getResponse().getBody();

        JSONArray authorsArr = new JSONArray(body);
        int len = authorsArr.length();

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(len, 5);
    }

    @AfterMethod
    public void cleanUp() {
        clean.authors();
    }
}
