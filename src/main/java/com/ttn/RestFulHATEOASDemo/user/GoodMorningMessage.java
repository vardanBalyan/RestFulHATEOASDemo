package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GoodMorningMessage {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/good-morning")
    public String print()
    {
        return "Good Morning!!";
    }

    @GetMapping(path = "/good-morning-international")
    public String goodMorningInternationalization(@RequestHeader(name = "Accept-Language",
            required = false) Locale locale)
    {
        return messageSource.getMessage("good.morning.message",null,locale);
    }
}
