package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodMorningMessage {

    @GetMapping(path = "/good-morning")
    public String print()
    {
        return "Good Morning!!";
    }
}
