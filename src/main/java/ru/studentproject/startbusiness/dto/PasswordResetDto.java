package ru.studentproject.startbusiness.dto;

import jakarta.validation.constraints.NotEmpty;
import ru.studentproject.startbusiness.config.FieldMatch;

@FieldMatch(first = "password", second = "confirmPassword", message ="Пароли не совпадают" )
public class PasswordResetDto {
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
