package au.org.rma.jsonapi.jsonapidemo.model.article;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonFilter("json-api")
public class ArticleAttributes {
    private String title;

    private String body;

    private Subclass subclass;
}
