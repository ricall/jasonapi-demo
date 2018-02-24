package au.org.rma.jsonapi.jsonapidemo.jsonapi.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinksType {
    private String self;
    private String related;
}
