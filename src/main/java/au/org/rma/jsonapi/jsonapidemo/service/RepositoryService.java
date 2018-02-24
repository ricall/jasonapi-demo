package au.org.rma.jsonapi.jsonapidemo.service;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import au.org.rma.jsonapi.jsonapidemo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Single;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RepositoryService {
    private Map<String, Repository<? extends JsonApiReference>> repositories;

    @Autowired
    public RepositoryService(Collection<Repository<? extends JsonApiReference>> repositories) {
        this.repositories = repositories.stream()
                .collect(Collectors.toMap(Repository::getType, Function.identity()));
    }

    public <T extends JsonApiReference> Single<T> fromReference(JsonApiReference reference) {
        Repository<T> repository = (Repository<T>)repositories.get(reference.getType());

        return repository.forId(reference.getId());
    }
}
