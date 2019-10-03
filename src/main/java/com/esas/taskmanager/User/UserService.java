package com.esas.taskmanager.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long findUserId(String username) throws UserNotFound {
        return userRepository.findIdByUsername(username).orElseThrow(() -> new UserNotFound("User " + username + "does not exist"));
    }
}
