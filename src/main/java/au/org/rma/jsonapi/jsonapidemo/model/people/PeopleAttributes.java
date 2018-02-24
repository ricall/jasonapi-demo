package au.org.rma.jsonapi.jsonapidemo.model.people;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonFilter("json-api")
public class PeopleAttributes {

    @JsonProperty(value = "first-name", index = 1)
    private String firstName;

    @JsonProperty(value = "last-name", index = 2)
    private String lastName;

    @JsonProperty(value = "twitter", index = 3)
    private String twitter;

    public String getName() {
        return firstName + " " + lastName;
    }
}
