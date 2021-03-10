package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    //uri version
    @GetMapping("/v1/user")
    public User UserV1() {
        return new User(1,"vardan",23, "abc");
    }

    @GetMapping("/v2/user")
    public EnhancedUser UserV2() {
        return new EnhancedUser(1,"vardan",23, "abc","indian");
    }


//Request Parameter

    @GetMapping(value = "/user/param", params = "version=1")
    public User paramV1() {
        return new User(1,"vardan",23, "abc");
    }

    @GetMapping(value = "/user/param", params = "version=2")
    public EnhancedUser param2() {
        return new EnhancedUser(1,"vardan",23, "abc","indian");
    }

//
//header Versioning


    @GetMapping(value = "/user/header", headers = "X-API-VERSION=1")
    public User headerV1() {
        return new User(1,"vardan",23, "abc");
    }

    @GetMapping(value = "/user/header", headers = "X-API-VERSION=2")
    public EnhancedUser headerV2() {
        return new EnhancedUser(1,"vardan",23, "abc","indian");
    }

    // mime type versioning

    @GetMapping(value = "/user/produce", produces = "application/vnd.company.app-v1+json")
    public User produceV1() {
        return new User(1,"vardan",23, "abc");
    }

    @GetMapping(value = "/user/produce", produces = "application/vnd.company.app-v2+json")
    public EnhancedUser produceV2() {
        return new EnhancedUser(1,"vardan",23, "abc","indian");
    }
}
