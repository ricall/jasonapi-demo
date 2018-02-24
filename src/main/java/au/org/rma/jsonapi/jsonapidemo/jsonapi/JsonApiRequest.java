package au.org.rma.jsonapi.jsonapidemo.jsonapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonApiRequest {
    private List<String> include;
    private Map<String, List<String>> fields;
    private List<String> sort;
    private List<String> filter;

    public boolean includeType(String type) {
        return include ==  null || include.contains(type);
    }

    public boolean serializeField(String type, String name) {
//        log.info("Checking {}:{}", type, name);
        if (fields == null) {
            return true;
        }
        List<String> fieldList = fields.get(type);
        String partialPath = name + ".";

        return fieldList == null
                || fieldList.contains(name)
                || fieldList.stream()
                    .anyMatch(field -> matchField(field, partialPath));
    }

    private boolean matchField(String field, String path) {
        if (field.startsWith(path)) {
            return true;
        } else if (field.endsWith("*")) {
            return path.startsWith(field.substring(0, field.length() - 1));
        }
        return false;
    }
}
