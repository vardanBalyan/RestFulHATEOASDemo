package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/user")
    public List<User> getAllUser()
    {
        return service.fetchAllUsers();
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Object> createUser(@RequestBody User user)
    {
        User addedUser =  service.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User user = service.delete(id);
        if(user == null)
            throw new UserNotFoundException("User does not exist.");
    }

}
