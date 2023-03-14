package com.integradora.gimnasio.Proveedor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.integradora.gimnasio.Proveedor.entity.proveedor;

@Repository
public interface proveedorRepository extends MongoRepository<proveedor, Integer>{
    boolean existsByNombreProvedor(String nombreProveedor);
    Optional<proveedor> findByNombreProvedor(String nombreProveedor);
}
