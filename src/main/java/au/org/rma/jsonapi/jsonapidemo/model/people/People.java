package au.org.rma.jsonapi.jsonapidemo.model.people;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiResource;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiRelationship;
import au.org.rma.jsonapi.jsonapidemo.repository.PeopleRepository;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class People extends JsonApiResource<PeopleAttributes> {

    @Builder
    public People(String id,
            PeopleAttributes attributes,
            Map<String, JsonApiRelationship> relationships,
            Collection<JsonApiReference> included,
            Map<String, URI> links) {
        super(PeopleRepository.TYPE, id, attributes, relationships, included, links);
    }
}
