package com.app.ecom.controller;

import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
import com.app.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service ;



    @GetMapping("/api/users")
    public List<UserResponse> getAllUser(){
        return service.getAllUser();

    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable int id){
         return service.getUserById(id);

    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user){
       service.createUser(user);
       return "User added successfully ";
    }

    @PutMapping("/api/users/{id}")
    public String updateUser(@RequestBody User user,@PathVariable int id){
       service.updateUser(id,user);
       return "User Updated successfully ";
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable int id){
        service.deleteUser(id);
        return "User deleted successfully ";
    }





}
