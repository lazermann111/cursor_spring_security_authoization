package com.cursor.hw17;

import com.cursor.hw17.entity.User;
import com.cursor.hw17.entity.UserPermission;
import com.cursor.hw17.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Set;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Hw17Application {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public Hw17Application(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Hw17Application.class, args);
    }


    @PostConstruct
    public void addUsers() {
        var user = new User();
        user.setUsername("Igor");
        user.setPassword(encoder.encode("123"));
        user.setPermissions(Set.of(UserPermission.ROLE_WRITE, UserPermission.ROLE_READ));
        userRepository.save(user);
    }
}
