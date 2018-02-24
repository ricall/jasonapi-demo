package au.org.rma.jsonapi.jsonapidemo.repository;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiRelationshipBuilder;
import au.org.rma.jsonapi.jsonapidemo.model.comments.Comment;
import au.org.rma.jsonapi.jsonapidemo.model.comments.CommentAttributes;
import org.springframework.stereotype.Component;
import rx.Single;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkType.SELF;

@Component
public class CommentRepository implements Repository<Comment> {

    public static String TYPE = "comment";
    public static String AUTHOR_RELATIONSHIP = "author";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Single<Comment> forId(String id) {
        Comment response = null;
        if (id.equals("5")) {
            response = Comment.builder()
                    .id("5")
                    .attributes(CommentAttributes.builder()
                        .body("First!")
                        .created(new Date())
                        .stars(5)
                        .build())
                    .relationships(JsonApiRelationshipBuilder.builder()
                        .relationship(AUTHOR_RELATIONSHIP)
                            .link(SELF, "http://example.com/comments/5")
                            .reference(PeopleRepository.TYPE, "2")
                        .build())
                    .build();
        }
        if (id.equals("12")) {
            response = Comment.builder()
                    .id("12")
                    .attributes(CommentAttributes.builder()
                            .body("I like XML better")
                            .lastModified(new Date())
                            .stars(1)
                            .build())
                    .relationships(JsonApiRelationshipBuilder.builder()
                            .relationship(AUTHOR_RELATIONSHIP)
                                .link(SELF, "http://example.com/comments/12")
                                .reference(PeopleRepository.TYPE, "9")
                            .build())
                    .build();
        }
        return Single.just(response)
                .delay(200, TimeUnit.MILLISECONDS);
    }
}
