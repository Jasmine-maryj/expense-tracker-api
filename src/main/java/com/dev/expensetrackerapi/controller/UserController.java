package com.dev.expensetrackerapi.controller;

import com.dev.expensetrackerapi.config.Constants;
import com.dev.expensetrackerapi.entity.User;
import com.dev.expensetrackerapi.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUSer(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.validateUser(email, password);

//        Map<String, String> map = new HashMap<>();
//        map.put("message", "loggedIn successfully");

        return new ResponseEntity<>(generateToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.registerUser(firstName, lastName, email, password);

//        Map<String, String> map = new HashMap<>();
//        map.put("message", "Registered successfully");
        
        return new ResponseEntity<>(generateToken(user), HttpStatus.OK);

    }

    private Map<String, String> generateToken(User user){
        long time = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(time))
                .setExpiration(new Date(time + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        return map;
    }
}
