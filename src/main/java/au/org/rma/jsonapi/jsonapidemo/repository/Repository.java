package au.org.rma.jsonapi.jsonapidemo.repository;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import rx.Single;

public interface Repository<T extends JsonApiReference> {

    String getType();

    Single<T> forId(String id);
}
