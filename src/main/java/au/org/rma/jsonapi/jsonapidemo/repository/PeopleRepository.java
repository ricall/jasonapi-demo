package au.org.rma.jsonapi.jsonapidemo.repository;

import au.org.rma.jsonapi.jsonapidemo.model.people.People;
import au.org.rma.jsonapi.jsonapidemo.model.people.PeopleAttributes;
import org.springframework.stereotype.Component;
import rx.Single;

import java.util.concurrent.TimeUnit;

import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkBuilder.singleLink;
import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkType.SELF;

@Component
public class PeopleRepository implements Repository<People> {

    public static final String TYPE = "people";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Single<People> forId(String id) {
        return Single.just(People.builder()
                .id("9")
                .attributes(PeopleAttributes.builder()
                        .firstName("Dan")
                        .lastName("Gebhardt")
                        .twitter("dgeb")
                        .build())
                .links(singleLink(SELF,"http://example.com/people/9"))
                .build())
                .delay(300, TimeUnit.MILLISECONDS);
    }
}
