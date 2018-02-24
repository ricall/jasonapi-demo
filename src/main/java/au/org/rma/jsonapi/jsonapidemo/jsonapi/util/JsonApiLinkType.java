package au.org.rma.jsonapi.jsonapidemo.jsonapi.util;

public enum JsonApiLinkType {

    SELF("self"),
    FIRST("first"),
    PREV("prev"),
    NEXT("next"),
    LAST("last"),
    RELATED("related");

    JsonApiLinkType(String description) {
        this.description = description;
    }

    private String description;

    @Override
    public String toString() {
        return description;
    }
}
