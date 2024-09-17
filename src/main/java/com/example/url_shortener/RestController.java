package com.example.url_shortener;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final URLService service;

    public RestController(URLService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addURL(@RequestBody URL url) {
        System.out.println(url.getOriginalUrl());
        return service.addURL(url.getOriginalUrl());
    }

    @GetMapping("/{link}")
    public RedirectView getUrl(@PathVariable String link) {
        URL url = service.getURL(link).orElse(new URL());
        return new RedirectView(url.getOriginalUrl());
    }
}
