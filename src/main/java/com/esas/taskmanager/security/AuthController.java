package com.esas.taskmanager.security;

import com.esas.taskmanager.User.User;
import com.esas.taskmanager.User.UserDTO;
import com.esas.taskmanager.User.UserRepository;
import com.esas.taskmanager.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public User authenticateUser(@RequestBody UserDTO userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user != null){
            if(encoder().matches(userDto.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new BadCredentialsException("Username or password is incorrect");
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse badCredentialsExceptionHandler(BadCredentialsException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
