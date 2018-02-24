package au.org.rma.jsonapi.jsonapidemo.jackson;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequest;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequestHolder;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.resource.JsonApiResource;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class JsonFieldPropertyFilter extends SimpleBeanPropertyFilter {

    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        if (shouldInclude(jgen, writer)) {
            writer.serializeAsField(pojo, jgen, provider);
        } else if (!jgen.canOmitFields()) {
            writer.serializeAsOmittedField(pojo, jgen, provider);
        }
    }

    private boolean shouldInclude(JsonGenerator jgen, PropertyWriter writer) {
        JsonApiRequest request = JsonApiRequestHolder.getJsonApiRequest();
        if (request.getFields() == null) {
            return true;
        }

        JsonStreamContext context = jgen.getOutputContext();
        String path = writer.getName();
        while (context != null) {
            context = context.getParent();
            if (context.getCurrentValue() instanceof JsonApiResource) {
                break;
            }
            path = context.getCurrentName() + "." + path;
        }
        String type =((JsonApiResource)context.getCurrentValue()).getType();
        return request.serializeField(type, path);
    }

}
