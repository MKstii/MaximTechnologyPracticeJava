package com.MaximPractice.todos.model.DTO;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String passwordRepeat;
}
