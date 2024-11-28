package com.example.Hotel.Repository;

import com.example.Hotel.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository  extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
