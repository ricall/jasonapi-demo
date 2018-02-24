package au.org.rma.jsonapi.jsonapidemo.configuration;

import au.org.rma.jsonapi.jsonapidemo.jackson.JsonFieldPropertyFilter;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequest;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequestHolder;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rx.plugins.RxJavaHooks;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfiguration {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer customizeJackson2ObjectMapperWithJsonFieldPropertyFilter() {
        return builder -> builder.filters(new SimpleFilterProvider()
                .addFilter("json-api", new JsonFieldPropertyFilter()));
    }

    @PostConstruct
    public void ensureSchedulersHaveTheJsonApiRequest() {
        RxJavaHooks.setOnScheduleAction(action -> {
            JsonApiRequest request = JsonApiRequestHolder.getJsonApiRequest();

            return () -> {
                JsonApiRequestHolder.setJsonApiRequest(request);
                action.call();
                JsonApiRequestHolder.clearJsonApiRequest();
            };
        });
    }
}
