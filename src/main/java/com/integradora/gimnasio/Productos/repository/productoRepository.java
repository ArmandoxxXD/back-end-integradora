package com.integradora.gimnasio.Productos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.integradora.gimnasio.Productos.entity.Producto;

@Repository
public interface productoRepository extends MongoRepository<Producto,Integer>{
    boolean existsByNombreProducto(String nombreProducto);
    Optional<Producto> findByNombreProducto(String nombreProducto);
    boolean existsByNombreProvedor(String nombreProvedor);
    List<Producto> findByNombreProvedor(String nombreProvedor);
    List<Producto> findByCategoria(String categoria);
}
