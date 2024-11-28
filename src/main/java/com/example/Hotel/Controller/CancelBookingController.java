package com.example.Hotel.Controller;


import com.example.Hotel.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cancel-hotel")
public class CancelBookingController {

    @Autowired
    private AdminServices adminServices;

    @DeleteMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('HOTELMANAGER')")
    public String CancelBooking(@PathVariable("bookingId") Long bookingId) {
        // Your cancel booking logic here
        return "Booking cancelled with ID: " + bookingId;
    }

}
