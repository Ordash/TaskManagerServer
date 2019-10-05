package com.esas.taskmanager.User;

import com.esas.taskmanager.Task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.esas.taskmanager.security.SecurityConfig.encoder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Task> tasks;

    public User(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = encoder().encode(password);
    }
}
