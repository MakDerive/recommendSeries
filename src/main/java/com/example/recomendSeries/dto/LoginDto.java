package com.example.recomendSeries.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Некорректный email")
    private String email;
    
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;
    
}