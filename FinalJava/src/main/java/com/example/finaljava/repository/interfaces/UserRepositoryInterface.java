package com.example.finaljava.repository.interfaces;

import com.example.finaljava.models.TelephoneNumber;
import com.example.finaljava.models.User;

public interface UserRepositoryInterface {
    User findByLogin(String login);
}
