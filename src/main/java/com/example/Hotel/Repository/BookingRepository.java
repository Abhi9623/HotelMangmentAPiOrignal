package com.example.Hotel.Repository;

import com.example.Hotel.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository  extends JpaRepository<Booking,Long> {
}
