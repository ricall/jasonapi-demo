package au.org.rma.jsonapi.jsonapidemo.model.comments;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiResource;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiRelationship;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends JsonApiResource<CommentAttributes> {

    @Builder
    public Comment(String id,
                   CommentAttributes attributes,
                   Map<String, JsonApiRelationship> relationships,
                   Collection<JsonApiReference> included,
                   Map<String, URI> links) {
        super("comment", id, attributes, relationships, included, links);
    }
}
