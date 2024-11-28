package com.example.Hotel.Controller;


import com.example.Hotel.Body.Loginbody;
import com.example.Hotel.Body.Respbodyall;
import com.example.Hotel.Models.User;
import com.example.Hotel.Services.Userservicess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserAppConntroller {

    @Autowired
    private Userservicess userservicess;

    @PostMapping("/register")
    public ResponseEntity<Respbodyall> register(@Valid @RequestBody User user){
        //log.info("Request came");
        return ResponseEntity.ok(userservicess.register(user));
    }
    @PostMapping("/login")
    public ResponseEntity<Respbodyall> rh(@RequestBody Loginbody loginbody){
        // log.info("Request came to login");
        return ResponseEntity.ok(userservicess.login(loginbody));
    }
}
