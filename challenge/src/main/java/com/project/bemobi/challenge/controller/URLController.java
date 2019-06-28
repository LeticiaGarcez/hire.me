package com.project.bemobi.challenge.controller;


import com.project.bemobi.challenge.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class URLController {

    @Autowired
    private URLService urlService;

    @RequestMapping("/create")
    public Object create(@RequestParam("url") String url, @RequestParam(value = "alias", required = false) String alias) {

        if (alias == null) {
            return this.urlService.shortWithoutAlias(url);

        } else {
            return this.urlService.shortWithAlias(url, alias);
        }
    }

    @RequestMapping("/search")
    public Object search(@RequestParam("alias") String alias) {

        return this.urlService.searchByAlias(alias);

    }

    @RequestMapping("/topUrl")
    public List topUrl() {

        return this.urlService.top10UrlAcessed();

    }

}