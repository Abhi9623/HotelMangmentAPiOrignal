package com.example.Hotel.Services;



import com.example.Hotel.Models.User;
import com.example.Hotel.Repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class uerService  implements UserDetailsService{

    @Autowired
    private Userrepository userrepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // Initialize bookingList (optional, but recommended for performance)
        // This forces initialization
        return user;
    }

}
