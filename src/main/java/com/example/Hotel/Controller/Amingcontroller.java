package com.example.Hotel.Controller;


import com.example.Hotel.Models.Hotel;
import com.example.Hotel.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Amingcontroller {

    @Autowired
    private AdminServices adminServices;



    @PostMapping("/posthotel")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Hotel CreateHotel( @RequestBody Hotel hotel){
        return adminServices.PostHote(hotel);
    }


}
