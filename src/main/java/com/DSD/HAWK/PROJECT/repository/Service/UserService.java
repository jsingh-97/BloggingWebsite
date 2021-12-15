package com.DSD.HAWK.PROJECT.repository.Service;

import com.DSD.HAWK.PROJECT.model.UserData;
import com.DSD.HAWK.PROJECT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserData findByEmail(String email) {
        return userRepository.findById(email).get();
    }

    public Boolean emailExists(String email) {
        return userRepository.existsById(email);
    }

    public void registerUser(UserData userData) {
        userRepository.save(userData);
    }

    public void deleteByEmail(String email) {
        userRepository.deleteById(email);
    }

    public void verifyUser(String email) {
        userRepository.verifyUser(email);
    }
}
