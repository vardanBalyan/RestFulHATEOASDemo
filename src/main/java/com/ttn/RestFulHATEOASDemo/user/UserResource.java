package com.ttn.RestFulHATEOASDemo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ApiModel(description = "user resource class")
@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @ApiModelProperty(notes = "returns all the users from the list of users")
    @GetMapping(path = "/user")
    public List<User> getAllUser()
    {
        return service.fetchAllUsers();
    }

    @ApiModelProperty(notes = "adds new user to the list of user")
    @PostMapping(path = "/user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
    {
        User addedUser =  service.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @ApiModelProperty(notes = "deletes a user for the provided specific id")
    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User user = service.delete(id);
        if(user == null)
            throw new UserNotFoundException("User does not exist.");
    }

}
