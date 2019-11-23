package ua.com.epam.entity.request;

import ua.com.epam.config.TemplatesURI;

public class Request {
    public static final String DELIMITER = "&";
    public static final String FIRST_DELIMITER = "?";
    private StringBuilder request;
    private String templateUri;

    public Request() {
        request = new StringBuilder();
    }


    public Request setOrderType(String orderType) {
        request.append("orderType=").append(orderType).append(DELIMITER);
        return this;
    }

    public Request setPage(String page) {
        request.append("page=").append(page).append(DELIMITER);
        return this;
    }

    public Request setPagination(String pagination) {
        request.append("pagination=").append(pagination).append(DELIMITER);
        return this;
    }

    public Request setSize(String size) {
        request.append("size=").append(size).append(DELIMITER);
        return this;
    }

    public Request setSortBy(String sortBy) {
        request.append("sortBy=").append(sortBy).append(DELIMITER);
        return this;
    }

    public Request setPage(Integer page) {
        request.append("page=").append(page).append(DELIMITER);
        return this;
    }

    public Request setPagination(Boolean pagination) {
        request.append("pagination=").append(pagination).append(DELIMITER);
        return this;
    }

    public Request setSize(Integer size) {
        request.append("size=").append(size).append(DELIMITER);
        return this;
    }

    public Request setTemplateURL(TemplatesURI uri) {
        this.templateUri = uri.getURI().concat(FIRST_DELIMITER);
        return this;
    }

    @Override
    public String toString() {
        return templateUri + request.toString();
    }
}
