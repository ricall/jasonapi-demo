package au.org.rma.jsonapi.jsonapidemo.configuration;

import au.org.rma.jsonapi.jsonapidemo.interceptor.JsonApiRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JsonApiRequestInterceptor());
    }
}
