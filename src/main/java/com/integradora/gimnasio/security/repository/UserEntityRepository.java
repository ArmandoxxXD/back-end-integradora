package com.integradora.gimnasio.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.integradora.gimnasio.security.entity.UserEntity;

@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, Integer>{
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByNombreUsuarioOrEmail(String nombreUsuario,String email);
}
