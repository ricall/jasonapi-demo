package au.org.rma.jsonapi.jsonapidemo.repository;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiRelationshipBuilder;
import au.org.rma.jsonapi.jsonapidemo.model.article.Article;
import au.org.rma.jsonapi.jsonapidemo.model.article.ArticleAttributes;
import au.org.rma.jsonapi.jsonapidemo.model.article.Subclass;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;
import rx.Single;

import java.util.concurrent.TimeUnit;

import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkBuilder.singleLink;
import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkType.RELATED;
import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkType.SELF;

@Component
public class ArticleRepository implements Repository<Article> {
    public static final String TYPE = "article";

    public static final String AUTHOR_RELATIONSHIP_TYPE = "author";
    public static final String COMMENTS_RELATIONSHIP_TYPE = "comments";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Single<Article> forId(String id) {
        return Single.just(Article.builder()
                .id(id)
                .attributes(ArticleAttributes.builder()
                        .title("JSON API paints my bikeshed!")
                        .body("Here is the actual article itself....")
                        .subclass(Subclass.builder()
                                .anotherValue("Some Value")
                                .map(ImmutableMap.of("first", "value", "second", "another value"))
                                .build())
                        .build())
                .relationships(JsonApiRelationshipBuilder.builder()
                        .relationship(AUTHOR_RELATIONSHIP_TYPE)
                            .link(SELF, "http://example.com/articles/1/relationships/author")
                            .link(RELATED, "http://example.com/articles/1/author")
                            .reference(PeopleRepository.TYPE, "9")
                        .relationship(COMMENTS_RELATIONSHIP_TYPE)
                            .link(SELF, "http://example.com/articles/1/relationships/comments")
                            .link(RELATED, "http://example.com/articles/1/comments")
                            .reference(CommentRepository.TYPE, "5")
                            .reference(CommentRepository.TYPE, "12")
                        .build())
                .links(singleLink(SELF, "http://example.com/articles/1"))
                .build())
        .delay(100, TimeUnit.MILLISECONDS);
    }
}
