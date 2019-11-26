package ua.com.epam.entity.request;

import ua.com.epam.config.TemplatesURI;

import java.util.HashMap;
import java.util.Map;

public class Request {
    public static final String DELIMITER = "&";
    public static final String FIRST_DELIMITER = "?";
    private Map<String, String> params;
    private String templateUri;


    public Request() {
        params = new HashMap();
    }


    public Request setOrderType(String orderType) {
        params.put("orderType", orderType);
        return this;
    }

    public Request setPage(String page) {
        params.put("page", page);
        return this;
    }

    public Request setPagination(String pagination) {
        params.put("pagination", pagination);
        return this;
    }

    public Request setSize(String size) {
        params.put("size", size);
        return this;
    }

    public Request setSortBy(String sortBy) {
        params.put("sortBy", sortBy);
        return this;
    }

    public Request setPage(Integer page) {
        params.put("page", page.toString());
        return this;
    }

    public Request setPagination(Boolean pagination) {
        params.put("pagination", pagination.toString());
        return this;
    }

    public Request setSize(Integer size) {
        params.put("size", size.toString());
        return this;
    }

    public Request setQuery(String query) {
        params.put("query", query);
        return this;
    }

    public Request setTemplateURL(TemplatesURI uri) {
        this.templateUri = uri.getURI();
        return this;
    }

    public Request setTemplateURL(String uri) {
        this.templateUri = uri;
        return this;
    }

    public String getTemplateURL() {
        return templateUri;
    }


    @Override
    public String toString() {
        StringBuilder request = new StringBuilder();
        for (Map.Entry param : params.entrySet())
            request.append(param.getKey()).append("=").append(param.getValue()).append(DELIMITER);
        return String.format("%s%s%s", templateUri, FIRST_DELIMITER, request.toString())
                .replaceFirst("[?&]$", "");
    }
}
