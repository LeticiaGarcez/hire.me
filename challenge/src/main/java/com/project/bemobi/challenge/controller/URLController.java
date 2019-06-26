package com.project.bemobi.challenge.controller;


import com.project.bemobi.challenge.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.concurrent.atomic.AtomicLong;

@RestController
public class URLController {

    @Autowired
    private URLService urlService;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/create")
    public Object create(@RequestParam("url") String url, @RequestParam(value = "alias", required = false) String alias) {

        if (alias == null) {
            return this.urlService.shortWithoutAlias(url);

        } else {
            return this.urlService.shortWithAlias(url, alias);
        }
    }

}