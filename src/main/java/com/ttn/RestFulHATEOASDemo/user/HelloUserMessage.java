package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloUserMessage {

    @Autowired
    private MessageSource messageSource;

    /*
    * return hello username in native language for the value passed in header*/
    @GetMapping(path = "/hello-user/{name}")
    public String helloUser(@RequestHeader( name = "Accept-Language",required = false)
                                        Locale locale, @PathVariable String name)
    {
        final String[] params = new String[]{name};
        return messageSource.getMessage("hello.user.message",params,locale);
    }
}
