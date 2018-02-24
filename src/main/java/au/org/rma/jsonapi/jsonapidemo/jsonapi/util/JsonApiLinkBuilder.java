package au.org.rma.jsonapi.jsonapidemo.jsonapi.util;

import com.google.common.collect.ImmutableMap;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonApiLinkBuilder {
    private Map<String, URI> links;

    private JsonApiLinkBuilder() {
        links = new LinkedHashMap<>();
    }

    public static JsonApiLinkBuilder builder() {
        return new JsonApiLinkBuilder();
    }

    public static Map<String, URI> singleLink(JsonApiLinkType linkType, String uri) {
        return singleLink(linkType.toString(), URI.create(uri));
    }

    private static Map<String, URI> singleLink(String link, URI uri) {
        return ImmutableMap.of(link, uri);
    }

    public JsonApiLinkBuilder link(JsonApiLinkType linkType, String uri) {
        return link(linkType.toString(), URI.create(uri));
    }

    public JsonApiLinkBuilder link(String link, URI uri) {
        links.put(link, uri);
        return this;
    }

    public Map<String, URI> build() {
        return links;
    }
}
