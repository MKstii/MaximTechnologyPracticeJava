package com.MaximPractice.todos.config;

import com.MaximPractice.todos.model.UserTodo;
import com.MaximPractice.todos.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userDB = userRepository.findByUsername(username);
        if (userDB == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(username)
                .password(userDB.getPassword())
                .build();
    }

    public void CreateUser(String username, String password) {
        var userDB = new UserTodo();
        userDB.setUsername(username);
        userDB.setPassword(passwordEncoder.encode(password));
        userRepository.save(userDB);
    }
}
