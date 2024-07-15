package com.MaximPractice.todos.service;

import com.MaximPractice.todos.config.CustomUserDetailsService;
import com.MaximPractice.todos.model.DTO.UserLoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    public void Register(String username, String password, String passwordRepeat) throws Exception {
        if (!password.equals(passwordRepeat)) {
            throw new Exception("Пароли не совпадают");
        }
        //TODO Переделать на работу с бд
        userDetailsService.CreateUser(username, password);
//        UserDetails newUser = User.withUsername(username)
//                .password("{noop}" + password)
//                .roles("USER")
//                .build();
//        ((InMemoryUserDetailsManager) userDetailsService).createUser(newUser);
    }

    public void Login(UserLoginDTO userLogin, HttpServletRequest request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}
