package com.example.Hotel.Services;

import com.example.Hotel.Models.Booking;
import com.example.Hotel.Models.Hotel;
import com.example.Hotel.Repository.BookingRepository;
import com.example.Hotel.Repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminServices {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;


    public Hotel PostHote(Hotel hotel) {
        Hotel hotel1 = Hotel.builder()
                .hotelName(hotel.getHotelName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .availabilities(hotel.getAvailabilities())
                .build();

        hotelRepository.save(hotel1);
        return hotel1;
    }


   @Transactional
    public String CancelBooking(Long bookingId) {
        // Step 1: Check if the booking exists
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (!optionalBooking.isPresent()) {
            return "Booking not found!";
        }

        // Step 2: Get the booking and related hotel
        Booking booking = optionalBooking.get();
        Hotel hotel = booking.getHotel();

        // Step 3: If a hotel is associated, update its availability
        if (hotel != null) {
            // Assuming that you want to increment the hotel availability after canceling the booking
            hotel.setAvailabilities(hotel.getAvailabilities() + 1); // Update as needed (e.g., increment or decrement)
            hotelRepository.save(hotel);  // Save the hotel updates
        }

        // Step 4: Delete the booking
        bookingRepository.deleteById(bookingId);

        // Step 5: Return success message
        return "Booking canceled successfully.";
    }


}


