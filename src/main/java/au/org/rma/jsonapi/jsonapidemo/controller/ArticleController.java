
package au.org.rma.jsonapi.jsonapidemo.controller;

import au.org.rma.jsonapi.jsonapidemo.service.ArticleResponse;
import au.org.rma.jsonapi.jsonapidemo.service.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService service;

    @ApiOperation(value = "Get a list of articles", response = ArticleResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "include", paramType = "query", type = "string",  defaultValue = "comment,people"),
            @ApiImplicitParam(name = "fields[article]", paramType = "query", type = "string",  defaultValue = "title,body"),
            @ApiImplicitParam(name = "fields[comment]", paramType = "query", type = "string", defaultValue = "body,created"),
            @ApiImplicitParam(name = "fields[people]", paramType = "query", type = "string", defaultValue = "first-name,last-name")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    @RequestMapping(value = "/article", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public Single<ResponseEntity<ArticleResponse>> getArticles() {
        return service.articles()
                .map(ResponseEntity::ok);
    }
}