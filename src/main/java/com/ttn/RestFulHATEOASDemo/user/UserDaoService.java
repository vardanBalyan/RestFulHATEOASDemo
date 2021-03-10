package com.ttn.RestFulHATEOASDemo.user;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* provide service for the user class*/
@Component
public class UserDaoService {

    private List <User> userList = new ArrayList<>();
    private int userCount=3;
    UserDaoService()
    {
        userList.add(new User(1,"vardan",23, "abc"));
        userList.add(new User(2,"shubham",22, "abc"));
        userList.add(new User(3,"abhay",26, "abc"));
    }


    public List<User> fetchAllUsers()
    {
        return userList;
    }

    public User saveUser(User user)
    {
        user.setId(++userCount);
        userList.add(user);
        return user;
    }

    public User delete(int id)
    {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (user.getId() == id) {
                userIterator.remove();
                return user;
            }
        }
        return null;
    }

    public User returnOneUser(int id)
    {
        for (User user: userList) {
            if(user.getId() == id)
                return user;
        }
        return null;
    }
}
