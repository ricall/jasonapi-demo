package au.org.rma.jsonapi.jsonapidemo.service;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiResponse;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiRelationship;
import au.org.rma.jsonapi.jsonapidemo.model.article.Article;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleResponse extends JsonApiResponse<List<Article>> {
    @Builder
    public ArticleResponse(List<Article> data,
                           Map<String, JsonApiRelationship> relationships,
                           Collection<? extends JsonApiReference> included,
                           Map<String, URI> links) {
        super(data, relationships, included, links);
    }
}
