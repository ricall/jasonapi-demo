package au.org.rma.jsonapi.jsonapidemo.model.comments;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@JsonFilter("json-api")
public class CommentAttributes {
    private String body;
    private Date created;
    private Date lastModified;
    private int stars;
}
