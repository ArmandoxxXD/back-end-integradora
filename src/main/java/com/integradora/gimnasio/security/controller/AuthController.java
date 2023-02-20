package com.integradora.gimnasio.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integradora.gimnasio.dto.Mensaje;
import com.integradora.gimnasio.exceptions.AttributeException;
import com.integradora.gimnasio.security.dto.CreateUserDto;
import com.integradora.gimnasio.security.dto.JwtTokenDto;
import com.integradora.gimnasio.security.dto.LoginUserDto;
import com.integradora.gimnasio.security.entity.UserEntity;
import com.integradora.gimnasio.security.service.UserEntityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserEntityService userEntityService;

    @PostMapping("/create")
    public ResponseEntity<Mensaje> create(@Valid @RequestBody CreateUserDto dto) throws AttributeException{
        UserEntity userEntity= userEntityService.create(dto);
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, "Usuario " + userEntity.getNombreUsuario() + " creado"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@Valid @RequestBody LoginUserDto dto) throws AttributeException{
        JwtTokenDto jwtTokenDto= userEntityService.login(dto);
        return ResponseEntity.ok(jwtTokenDto);
    }
    
}
