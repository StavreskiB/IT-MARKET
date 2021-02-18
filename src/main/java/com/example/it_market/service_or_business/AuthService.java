package com.example.it_market.service_or_business;

import com.example.it_market.model.User;

public interface AuthService {
    User getCurrentUser();
    String getCurrentUserId();
    User signUpUser(String username, String password, String repeatedPassword, String mob_number, String street, String city);
}
