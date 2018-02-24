package au.org.rma.jsonapi.jsonapidemo.jackson;

import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequest;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.JsonApiRequestHolder;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkBuilder;
import au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiRelationshipBuilder;
import au.org.rma.jsonapi.jsonapidemo.model.article.Article;
import au.org.rma.jsonapi.jsonapidemo.model.article.ArticleAttributes;
import au.org.rma.jsonapi.jsonapidemo.model.article.Subclass;
import au.org.rma.jsonapi.jsonapidemo.service.ArticleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static au.org.rma.jsonapi.jsonapidemo.jsonapi.util.JsonApiLinkType.SELF;

@Slf4j
public class TestFilteredResponse {

    private ObjectMapper objectMapper;

    private ArticleResponse articleResponse;
    private JsonApiRequest jsonRequest;

    @Before
    public void init() {
        objectMapper = new ObjectMapper();

        Article article = Article.builder()
                .id("12")
                .attributes(ArticleAttributes.builder()
                        .title("A Title")
                        .body("A Body")
                        .subclass(Subclass.builder()
                            .anotherValue("Yet another value")
                            .map(ImmutableMap.of("a", "b", "c", "d"))
                            .build())
                        .build())
                .relationships(JsonApiRelationshipBuilder.builder()
                    .relationship("author")
                        .reference("person", "3")
                        .link(SELF, "/articles/12/relationships/author")
                    .build())
                .build();
        articleResponse = ArticleResponse.builder()
                .data(article)
                .links(JsonApiLinkBuilder.singleLink(SELF, "/test.com"))
                .build();
        jsonRequest = new JsonApiRequest();
        JsonApiRequestHolder.setJsonApiRequest(jsonRequest);
    }

    @After
    public void cleanup() {
        JsonApiRequestHolder.clearJsonApiRequest();
    }

    @Test
    public void testNormalSerialisation() throws Exception {
        String response = objectMapper.writeValueAsString(articleResponse);
        log.info(response);
    }

    @Test
    public void testFilteredSerialisation() throws Exception {
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("json-api", new JsonFieldPropertyFilter());
        jsonRequest.setFields(ImmutableMap.of(
                "article", ImmutableList.of("body", "subclass.map")
        ));
        log.info("JSON: {}", jsonRequest);
        String response = objectMapper.writer(filters).writeValueAsString(articleResponse);

        log.info(response);
    }
}
