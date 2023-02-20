package com.integradora.gimnasio.security.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.integradora.gimnasio.security.enums.RolEnum;

import jakarta.persistence.Id;

@Document(collection = "usuarios")
public class UserEntity {
    
    @Id
    private int id;
    private String nombreUsuario;
    private String email;
    private String password;
    List<RolEnum> roles;

    public UserEntity(){
    }

    public UserEntity(int id, String nombreUsuario, String email, String password, List<RolEnum> roles) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
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

    public List<RolEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RolEnum> roles) {
        this.roles = roles;
    }
}


