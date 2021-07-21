package com.myproject.restfultodolist.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<CollectionModel<EntityModel<User>>> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity findOneUser(@PathVariable int id){
        return userService.findOneUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
}
