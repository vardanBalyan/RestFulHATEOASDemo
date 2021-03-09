package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloUserMessage {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-user")
    public String helloUser()
    {
        //@RequestHeader(name = "Accept-Language",required = false) Locale locale
        return messageSource.getMessage("hello.user.message",null,
                LocaleContextHolder.getLocale());
    }
}
