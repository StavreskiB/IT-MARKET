package com.example.it_market.service_or_business;

import com.example.it_market.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findById(String userId);
    User registerUser(User user);

    void deleteByUsername(String username);
}
