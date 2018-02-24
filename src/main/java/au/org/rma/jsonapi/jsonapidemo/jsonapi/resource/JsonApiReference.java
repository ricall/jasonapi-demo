package au.org.rma.jsonapi.jsonapidemo.jsonapi.resource;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "referenceBuilder")
public class JsonApiReference {

    @JsonProperty(value = "type", index = 1)
    private String type;

    @JsonProperty(value = "id", index = 2)
    private String id;
}
