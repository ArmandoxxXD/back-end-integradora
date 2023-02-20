package com.integradora.gimnasio.security.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class CreateUserDto {
    @NotBlank(message  = "Nombre de usuario es obligatorio")
    private String nombreUsuario;
    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Email invalido")
    private String email;
    @NotBlank(message = "Contraseña de usuario es obligatorio")
    private String password;
    @NotEmpty(message = "Elige un rol")
    List<String> roles;

    public CreateUserDto() {
    }

    public CreateUserDto(String nombreUsuario, String email, String password, List<String> roles) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
