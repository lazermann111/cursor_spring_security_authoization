package com.cursor.hw17.service;

import com.cursor.hw17.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    static AccessDeniedException ACCESS_DENIED = new AccessDeniedException("Access denied");

    public UserService(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder) { //todo why @Lazy?
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserDetails login(String username, String password) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> ACCESS_DENIED);
        if (!encoder.matches(password, user.getPassword()))
            throw ACCESS_DENIED;
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
