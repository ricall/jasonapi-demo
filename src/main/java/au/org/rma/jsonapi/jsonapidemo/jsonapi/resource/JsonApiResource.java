package au.org.rma.jsonapi.jsonapidemo.jsonapi.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JsonApiResource<T> extends JsonApiReference {

    @JsonProperty(value = "attributes", index  = 3)
    private T attributes;

    @JsonProperty(value = "relationships", index = 5)
    private Map<String, JsonApiRelationship> relationships;

    @JsonProperty(value = "included", index = 6)
    private Collection<? extends JsonApiReference> included;

    @JsonProperty(value = "links", index = 7)
    @ApiModelProperty(dataType = "au.org.rma.jsonapi.jsonapidemo.jsonapi.util.LinksType", allowEmptyValue = true)
    private Map<String, URI> links;

    public JsonApiResource(String type,
                           String id,
                           T attributes,
                           Map<String, JsonApiRelationship> relationships,
                           Collection<? extends JsonApiReference> included,
                           Map<String, URI> links) {
        super(type, id);
        this.attributes = attributes;
        this.relationships = relationships;
        this.included = included;
        this.links = links;
    }

}
