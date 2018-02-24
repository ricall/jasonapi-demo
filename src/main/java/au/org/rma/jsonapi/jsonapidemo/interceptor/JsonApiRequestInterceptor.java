package au.org.rma.jsonapi.jsonapidemo.interceptor;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequest;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequestHolder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class JsonApiRequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JsonApiRequest apiRequest = JsonApiRequest.builder()
                .fields(request.getParameterMap().entrySet().stream()
                        .filter(e -> e.getKey().startsWith("fields["))
                        .filter(e -> e.getKey().endsWith("]"))
                        .collect(Collectors.toMap(this::parseField, this::parseValue)))
                .include(commaDelimited(request.getParameter("include")))
                .sort(commaDelimited(request.getParameter("sort")))
                .filter(commaDelimited(request.getParameter("jackson")))
                .build();
        JsonApiRequestHolder.setJsonApiRequest(apiRequest);
        return true;
    }

    private List<String> commaDelimited(String text) {
        if (text == null) {
            return null;
        }
        return Arrays.asList(text.split("[,]"));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        JsonApiRequestHolder.clearJsonApiRequest();
    }

    private String parseField(Entry<String, String[]> entry) {
        String field = entry.getKey();
        return field.substring(7, field.length() - 1);
    }

    private List<String> parseValue(Entry<String, String[]> entry) {
        return commaDelimited(entry.getValue()[0]);
    }

}
