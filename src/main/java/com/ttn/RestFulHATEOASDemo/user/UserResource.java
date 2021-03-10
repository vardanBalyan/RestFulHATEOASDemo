package com.ttn.RestFulHATEOASDemo.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = {"UserResource"})
@SwaggerDefinition(tags = {
        @Tag(name = "UserResource", description = "Write description here")
})
@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @ApiOperation(value = "get mapping to retrieve all users")
    @GetMapping(path = "/user")
    public List<User> getAllUser()
    {
        return service.fetchAllUsers();
    }

    @ApiOperation(value = "post mapping to create a user")
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

    /*
    * saves new user and returns non-critical information of user*/
    @ApiOperation(value = "post mapping to create a user and display non-critical info")
    @PostMapping(path = "/user-save")
    public MappingJacksonValue saveUserAndDisplayInfo(@RequestBody User user)
    {
        User savedUser = service.saveUser(user);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","age");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(savedUser);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    @ApiOperation(value = "delete mapping to delete a specific user")
    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User user = service.delete(id);
        if(user == null)
            throw new UserNotFoundException("User does not exist.");
    }

    @GetMapping(path = "/user/{id}")
    public EntityModel<User> getSpecificUser(@PathVariable int id)
    {
        User user = service.returnOneUser(id);
        if (user == null)
            throw new UserNotFoundException("user does not exist");

        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUser());
        resource.add(linkTo.withRel("all-Users"));
        return resource;
    }

}
