package raut.shubham.electroroute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raut.shubham.electroroute.entity.User;
import raut.shubham.electroroute.service.UserService;

import java.util.Optional;

@RestController

public class UserControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello World!!";
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);
        }else {
            User dbUser = userOptional.get();
            if(user.getBrand()!= null) dbUser.setBrand(user.getBrand());
            if(user.getModel()!= null) dbUser.setModel(user.getModel());
            userService.saveUser(dbUser);
            return new ResponseEntity<>("User Updated", HttpStatus.OK);
        }
    }

}