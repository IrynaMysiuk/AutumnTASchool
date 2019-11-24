package ua.com.epam.service;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import ua.com.epam.config.TemplatesURI;
import ua.com.epam.core.rest.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CleanUpService {
    private static Logger log = Logger.getLogger(CleanUpService.class);

    private RestClient client;

    public CleanUpService(RestClient client) {
        this.client = client;
    }

    public void authors() {
        log.info("Start to get all authors...");

        client.get(TemplatesURI.GET_ALL_AUTHORS_ARR.getURI());

        List<Long> authorIds = getObjectIdsToDelete(client.getResponse().getBody());
        int size = authorIds.size();

        if (size != 0) {
            log.info(size + " authors found!");
            log.info("Start to delete authors...");
            authorIds.forEach(id -> client.delete(String.format(TemplatesURI.DELETE_AUTHOR_SINGLE_OBJ.getURI(), id)));
            return;
        }

        log.info("Nothing to delete! Author table is empty!");
    }

    private List<Long> getObjectIdsToDelete(String json) {
        JSONArray a = new JSONArray(json);

        if (a.length() == 0) return new ArrayList<>();

        return a.toList()
                .stream()
                .map(o -> Long.valueOf(JsonPath.read(o, "$." + ua.com.epam.utils.JsonKeys.AUTHOR_ID).toString()))
                .collect(Collectors.toList());
    }
}
