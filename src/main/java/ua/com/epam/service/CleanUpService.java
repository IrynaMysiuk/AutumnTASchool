package ua.com.epam.service;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import ua.com.epam.core.rest.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.epam.config.URI.DELETE_AUTHOR_SINGLE_OBJ;
import static ua.com.epam.config.URI.GET_ALL_AUTHORS_ARR;
import static ua.com.epam.utils.JsonKeys.*;

public class CleanUpService {
    private static Logger log = Logger.getLogger(CleanUpService.class);

    private RestClient client;

    public CleanUpService(RestClient client) {
        this.client = client;
    }

    public void authors() {
        log.info("Start to get all authors...");

        client.get(GET_ALL_AUTHORS_ARR);

        List<Long> authorIds = getObjectIdsToDelete(AUTHOR_ID, client.getResponse().getBody());
        int size = authorIds.size();

        if (size != 0) {
            log.info(size + " authors found!");
            log.info("Start to delete authors...");
            authorIds.forEach(id -> client.delete(String.format(DELETE_AUTHOR_SINGLE_OBJ, id)));
            return;
        }

        log.info("Nothing to delete! Author table is empty!");
    }

    private List<Long> getObjectIdsToDelete(String keyName, String json) {
        JSONArray a = new JSONArray(json);

        if (a.length() == 0) return new ArrayList<>();

        return a.toList()
                .stream()
                .map(o -> Long.valueOf(JsonPath.read(o, "$." + keyName).toString()))
                .collect(Collectors.toList());
    }
}
