package au.org.rma.jsonapi.jsonapidemo.jsonapi.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinksWithPaginationType {
    private String self;
    private String related;
    private String first;
    private String prev;
    private String next;
    private String last;
}
