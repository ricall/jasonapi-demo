package au.org.rma.jsonapi.jsonapidemo.model.article;

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
public class Article extends JsonApiResource<ArticleAttributes> {
    @Builder
    public Article(String id,
               ArticleAttributes attributes,
               Map<String, JsonApiRelationship> relationships,
               Collection<JsonApiReference> incuded,
               Map<String, URI> links) {
        super("article", id, attributes, relationships, incuded, links);
    }
}
