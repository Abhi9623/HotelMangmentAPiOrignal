package com.example.Hotel.Services;


import com.example.Hotel.Body.Loginbody;
import com.example.Hotel.Body.RequestBodyForBookHotel;
import com.example.Hotel.Body.Respbodyall;
import com.example.Hotel.Enumss.Userrole;
import com.example.Hotel.Models.Booking;
import com.example.Hotel.Models.Hotel;
import com.example.Hotel.Models.User;
import com.example.Hotel.Repository.BookingRepository;
import com.example.Hotel.Repository.HotelRepository;
import com.example.Hotel.Repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Userservicess {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Userrepository userrepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    JWTService jwtService;


    @Autowired
    AuthenticationManager authenticationManager;

    public Respbodyall register(User user){
        if(user.getUserRole()==null){
            user.setUserRole(Userrole.CUSTOMER);
        }

        User user1=User.builder().email(user.getEmail()).password(passwordEncoder.encode(user.getPassword()))
                        .firstName(user.getFirstName()).lastName(user.getLastName()).userRole(user.getUserRole()).build();



        userrepository.save(user1);

        String jwt=jwtService.generateToken(user1);

        return  Respbodyall.builder().token(jwt).build();
    }

    public Respbodyall login(Loginbody loginbody){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginbody.getEmail(), loginbody.getPassword()));
        User user=userrepository.findByEmail(loginbody.getEmail());
        String token=jwtService.generateToken(user);

        return Respbodyall.builder().token(token).build();

    }


    public Long BookHotel(RequestBodyForBookHotel requestBodyForBookHotel) {
        // Retrieve the hotel by its ID
        Hotel hotel = hotelRepository.findById(requestBodyForBookHotel.getHotelid())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Retrieve the user by its ID (or handle this another way, e.g., from the logged-in user's session)
        User user = userrepository.findById(requestBodyForBookHotel.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new booking
        Booking booking = new Booking();
        booking.setHotel(hotel);  // Set the hotel
        booking.setUser(user);    // Set the user


        // Save the booking to the repository
        bookingRepository.save(booking);

        // Update the hotel's availability
        hotel.setAvailabilities(hotel.getAvailabilities() - 1);


        hotelRepository.save(hotel);

        return booking.getId();
    }
}
