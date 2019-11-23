package ua.com.epam;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.epam.entity.author.Author;

import static ua.com.epam.config.URI.POST_AUTHOR_SINGLE_OBJ;

public class FirstTest extends BaseTest {
    private double expAuthor;

    @Test
   public void postAuthor() {
       client.post(POST_AUTHOR_SINGLE_OBJ, expAuthor);

       int statusCode = client.getResponse().getStatusCode();
       String body = client.getResponse().getBody();

       Author actAuthor = g.fromJson(body, Author.class);

       Assert.assertEquals(statusCode, 201);
       Assert.assertEquals(expAuthor, actAuthor);
   }

}
