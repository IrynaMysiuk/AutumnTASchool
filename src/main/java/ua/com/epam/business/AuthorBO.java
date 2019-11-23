package ua.com.epam.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.com.epam.config.TemplatesURI;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.utils.helpers.LocalDateAdapter;

import java.time.LocalDate;

public class AuthorBO {
    private RestClient client;
    private Gson g;
    public AuthorBO(){
        client=new RestClient();
        g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    }

    public Author getActualAuthor() {
        return g.fromJson(client.getResponse().getBody(), Author.class);
    }

    public Response createAuthor(Author expAuthor) {
        client.post(TemplatesURI.POST_AUTHOR_SINGLE_OBJ.getURI(), expAuthor);
        return client.getResponse();
    }

    public Response updateAuthor(Author expAuthor) {
        client.put(TemplatesURI.PUT_AUTHOR_SINGLE_OBJ.setId(expAuthor.getAuthorId().toString()).getURI(), expAuthor);
        return client.getResponse();
    }

    public Response deleteAuthor(String id) {
        client.delete(TemplatesURI.GET_AUTHOR_SINGLE_OBJ.setId(id).getURI());
        return client.getResponse();
    }

    public Author getAuthor(String id) {
        client.get(TemplatesURI.GET_AUTHOR_SINGLE_OBJ.clearUri().setId(id).getURI());
        return g.fromJson(client.getResponse().getBody(), Author.class);
    }
}
