package au.org.rma.jsonapi.jsonapidemo.model.article;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@JsonFilter("json-api")
public class Subclass {
    private String anotherValue;

    private Map<String, String> map;
}