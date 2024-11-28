package com.example.Hotel.Controller;


import com.example.Hotel.Body.RequestBodyForBookHotel;
import com.example.Hotel.Models.Booking;
import com.example.Hotel.Services.Userservicess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingServiceController {

    @Autowired
    private Userservicess userservicess;

    @PostMapping("/book-hotel")
    public Long BookHotel(@RequestBody RequestBodyForBookHotel requestBodyForBookHotel){
       return userservicess.BookHotel(requestBodyForBookHotel);
    }
}
