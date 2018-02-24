package au.org.rma.jsonapi.jsonapidemo.jsonapi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonApiRequestHolder {

    private static ThreadLocal<JsonApiRequest> INSTANCE = new ThreadLocal<>();

    public static JsonApiRequest getJsonApiRequest() {
        return INSTANCE.get();
    }

    public static void setJsonApiRequest(JsonApiRequest jsonRequest) {
        //log.info("JsonApiRequest: {}", jsonRequest);
        INSTANCE.set(jsonRequest);
    }

    public static void clearJsonApiRequest() {
        INSTANCE.remove();
    }
}
