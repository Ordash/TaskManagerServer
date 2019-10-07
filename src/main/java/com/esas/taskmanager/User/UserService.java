package com.esas.taskmanager.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long findUserId(String username) throws UserNotFoundException {
        return userRepository.findIdByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User " + username + "does not exist"));
    }

    public void save(final User user){
        user.setPassword(encoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User can not be found with id: " + id));
    }
}
