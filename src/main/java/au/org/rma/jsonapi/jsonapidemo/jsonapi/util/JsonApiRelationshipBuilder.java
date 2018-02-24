package au.org.rma.jsonapi.jsonapidemo.jsonapi.util;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiRelationship;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JsonApiRelationshipBuilder {
    private Map<String, JsonApiRelationship> relationships;
    private JsonApiRelationship currentRelationship;

    public JsonApiRelationshipBuilder() {
        relationships = new HashMap<>();
    }

    public static JsonApiRelationshipBuilder builder() {
        return new JsonApiRelationshipBuilder();
    }

    public JsonApiRelationshipBuilder relationship(String relationship) {
        currentRelationship = new JsonApiRelationship();
        relationships.put(relationship, currentRelationship);

        return this;
    }

    public JsonApiRelationshipBuilder link(JsonApiLinkType linkType, String uri) {
        return link(linkType.toString(), URI.create(uri));
    }

    public JsonApiRelationshipBuilder link(String link, URI uri) {
        currentRelationship.getLinks().put(link, uri);

        return this;
    }

    public JsonApiRelationshipBuilder reference(String type, String id) {
        currentRelationship.getData().add(JsonApiReference.referenceBuilder()
                .type(type)
                .id(id)
                .build());

        return this;
    }

    public Map<String, JsonApiRelationship> build() {
        return relationships;
    }
}
