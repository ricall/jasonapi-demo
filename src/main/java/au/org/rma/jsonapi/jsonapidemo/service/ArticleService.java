package au.org.rma.jsonapi.jsonapidemo.service;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequest;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequestHolder;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkBuilder;
import au.org.rma.jsonapi.jsonapidemo.model.article.Article;
import au.org.rma.jsonapi.jsonapidemo.repository.ArticleRepository;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Single;

import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkType.*;

@Slf4j
@Component
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    RepositoryService repositoryService;

    public Single<ArticleResponse> articles() {
        log.info("JSON request {}", JsonApiRequestHolder.getJsonApiRequest());
        return articleRepository.forId("1")
                .flatMap(this::convertToArticleResponse);

    }

    private Single<ArticleResponse> convertToArticleResponse(Article article) {
        return Observable.from(article.getRelationships().values())
                .flatMap(relationship -> Observable.from(relationship.getData()))
                .flatMap(this::loadFromRepository)
                .toList()
                .map(included -> ArticleResponse.builder()
                    .data(ImmutableList.of(article))
                    .included(included)
                    .links(JsonApiLinkBuilder.builder()
                        .link(SELF, "http://example.com/articles")
                        .link(NEXT, "http://example.com/articles?page[offset]=2")
                        .link(LAST, "http://example.com/articles?page[offset]=10")
                        .build())
                    .build()
                ).toSingle();
    }

    private Observable<? extends JsonApiReference> loadFromRepository(JsonApiReference reference) {
        JsonApiRequest request = JsonApiRequestHolder.getJsonApiRequest();
        // log.info("JsonApiRequest: {}", request);
        if (request.includeType(reference.getType())) {
            return repositoryService.fromReference(reference).toObservable();
        } else {
            return Observable.empty();
        }
    }

}
