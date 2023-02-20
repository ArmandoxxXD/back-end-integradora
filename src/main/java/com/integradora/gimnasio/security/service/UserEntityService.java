package com.integradora.gimnasio.security.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.integradora.gimnasio.security.dto.CreateUserDto;
import com.integradora.gimnasio.security.dto.JwtTokenDto;
import com.integradora.gimnasio.security.dto.LoginUserDto;
import com.integradora.gimnasio.security.entity.UserEntity;
import com.integradora.gimnasio.security.enums.RolEnum;
import com.integradora.gimnasio.security.jwt.JwtProvider;
import com.integradora.gimnasio.security.repository.UserEntityRepository;
import com.integradora.gimnasio.exceptions.AttributeException;

@Service
public class UserEntityService {

    @Autowired
    UserEntityRepository userEntityRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public UserEntity create(CreateUserDto dto) throws AttributeException{
        if(userEntityRepository.existsByNombreUsuario(dto.getNombreUsuario()))
            throw new AttributeException("El nombre de usuario ya existe"); 
        if(userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("El email ya existe");         
        int id = autoIncrement();
        List<RolEnum> roles=dto.getRoles().stream().map(rol -> RolEnum.valueOf(rol)).collect(Collectors.toList());
        String password= passwordEncoder.encode(dto.getPassword());
        UserEntity userEntity= new UserEntity(id,dto.getNombreUsuario(),dto.getEmail(), password,roles);
        return userEntityRepository.save(userEntity);
    }

    public JwtTokenDto login(LoginUserDto dto){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getNombreUsuario(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }

    private int autoIncrement(){
        List<UserEntity> users= userEntityRepository.findAll();
        return users.isEmpty()? 1:users.stream().max(Comparator.comparing(UserEntity::getId)).get().getId()+1;
    }

}
