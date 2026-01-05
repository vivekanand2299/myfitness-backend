package com.example.myfitness.Service;

import com.example.myfitness.Repository.UserPreferencesRepository;
import com.example.myfitness.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPreferencesRepository userPreferencesRepository;


}
