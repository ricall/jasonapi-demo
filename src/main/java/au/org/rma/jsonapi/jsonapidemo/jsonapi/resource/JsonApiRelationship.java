package au.org.rma.jsonapi.jsonapidemo.jsonapi.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class JsonApiRelationship {

    @ApiModelProperty(dataType = "au.org.rma.jsonapi.jsonapidemo.jsonapi.util.LinksType", allowEmptyValue = true)
    private Map<String, URI> links;

    private List<JsonApiReference> data;

    public JsonApiRelationship() {
        links = new LinkedHashMap<>();
        data = new ArrayList<>();
    }
}