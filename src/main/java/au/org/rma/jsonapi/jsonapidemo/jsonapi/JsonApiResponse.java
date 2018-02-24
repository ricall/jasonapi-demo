package au.org.rma.jsonapi.jsonapidemo.jsonapi;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiReference;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiRelationship;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
public class JsonApiResponse<T> {

    @JsonProperty(value = "data", index = 4)
    private T data;

    @JsonProperty(value = "relationships", index = 5)
    private Map<String, JsonApiRelationship> relationships;

    @JsonProperty(value = "included", index = 6)
    private Collection<? extends JsonApiReference> included;

    @JsonProperty(value = "links", index = 7)
    @ApiModelProperty(dataType = "au.org.rma.jsonapi.jsonapidemo.jsonapi.util.LinksWithPaginationType", allowEmptyValue = true)
    private Map<String, URI> links;

    public JsonApiResponse(T data,
                           Map<String, JsonApiRelationship> relationships,
                           Collection<? extends JsonApiReference> included,
                           Map<String, URI> links) {
        this.data = data;
        this.relationships = relationships;
        this.included = included;
        this.links = links;
    }

}
