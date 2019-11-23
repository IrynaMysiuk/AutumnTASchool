package ua.com.epam;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.epam.config.TemplatesURI;
import ua.com.epam.entity.request.Request;

import static org.apache.http.HttpStatus.SC_OK;

public class SearchAuthorsTests extends BaseTest {
    @Test(description = "Verify author size")
    public void checkSizeAuthors() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setOrderType("asc")
                .setPage(1)
                .setPagination(true)
                .setSize(5)
                .setSortBy("authorId");
        client.get(request.toString());
        Assert.assertEquals(client.getResponse().getStatusCode(), SC_OK, "Status code is incorrect");

        JSONArray authorsArr = new JSONArray(client.getResponse().getBody());
        Assert.assertEquals(authorsArr.length(), 5, "The size is incorrect");
    }

    @Test(description = "Verify author page")
    public void checkPageAuthors() {
        Request request = new Request()
                .setTemplateURL(TemplatesURI.GET_ALL_AUTHORS_ARR)
                .setOrderType("asc")
                .setPage(1)
                .setPagination(true)
                .setSize(5)
                .setSortBy("authorId");
        client.get(request.toString());
        Assert.assertEquals(client.getResponse().getStatusCode(), SC_OK, "Status code is incorrect");

        JSONArray authorsArr = new JSONArray(client.getResponse().getBody());
        Assert.assertEquals(authorsArr.length(), 1, "The size is incorrect");
    }

    //orderType Collections.sort(auhorsList)

    // sortBy
}
