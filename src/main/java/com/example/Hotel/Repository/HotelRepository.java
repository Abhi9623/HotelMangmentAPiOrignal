package com.example.Hotel.Repository;

import com.example.Hotel.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
