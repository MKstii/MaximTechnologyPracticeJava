package com.MaximPractice.todos.controller;

import com.MaximPractice.todos.model.DTO.UserLoginDTO;
import com.MaximPractice.todos.model.DTO.UserRegisterDTO;
import com.MaximPractice.todos.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final AuthService authService;

    @GetMapping("register")
    public ResponseEntity<String> Register(UserRegisterDTO userRegister){
        try {
            authService.Register(
                    userRegister.getUsername(),
                    userRegister.getPassword(),
                    userRegister.getPasswordRepeat());
            return ResponseEntity.ok("Вы успешно зарегестрирвались");
        }
        catch (Exception e){
            var msg = e.getMessage();
            return ResponseEntity.badRequest().body(msg);
        }
    }

    @PostMapping("login")
    public ResponseEntity<String> Login(UserLoginDTO userLogin, HttpServletRequest request){
        try {
            authService.Login(userLogin, request);
            return ResponseEntity.ok("Вы успешно вошли");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ошибка входа");
        }
    }

    @PostMapping("logout")
    public ResponseEntity<String> Logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response){
        var logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok("Вы успешно вышли");
    }
}
