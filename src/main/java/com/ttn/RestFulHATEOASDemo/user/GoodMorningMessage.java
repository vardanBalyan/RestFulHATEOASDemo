package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

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

    /*
    * returns good morning string for the language passed in header*/
    @GetMapping(path = "/good-morning-international")
    public String goodMorningInternationalization()
    {
        //@RequestHeader(name = "Accept-Language",required = false) Locale locale
        return messageSource.getMessage("good.morning.message",null,
                LocaleContextHolder.getLocale());
    }
}
