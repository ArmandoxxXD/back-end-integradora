package com.integradora.gimnasio.Productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integradora.gimnasio.Productos.dto.ProductoDto;
import com.integradora.gimnasio.Productos.entity.Producto;
import com.integradora.gimnasio.Productos.service.productoService;
import com.integradora.gimnasio.dto.Mensaje;
import com.integradora.gimnasio.exceptions.AttributeException;
import com.integradora.gimnasio.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Producto")
@CrossOrigin
public class productoController {
    
    @Autowired
    productoService productoService;

   
    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMIENTO','INSTRUCTOR','USER')")
    @GetMapping
    public ResponseEntity<List<Producto>> getAll(){
        return ResponseEntity.ok(productoService.getAll());
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMIENTO','INSTRUCTOR','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getOne(@PathVariable("id") int id) throws ResourceNotFoundException{
        return ResponseEntity.ok(productoService.getOne(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMIENTO','INSTRUCTOR','USER')")
    @GetMapping("/Provedor/{nombreProvedor}")
    public ResponseEntity<List<Producto>> getAllByProveedor(@PathVariable("nombreProvedor") String Provedor){
        return ResponseEntity.ok(productoService.getAllByProveedor(Provedor));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMIENTO','INSTRUCTOR','USER')")
    @GetMapping("/Categoria/{categoria}")
    public ResponseEntity<List<Producto>> getAllByCategoria(@PathVariable("categoria") String Categoria){
        return ResponseEntity.ok(productoService.getAllByCategoria(Categoria));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Mensaje> save(@Valid @RequestBody ProductoDto dto) throws AttributeException{
        Producto pro= productoService.save(dto);
        String message="producto " +pro.getNombreProducto()+ " guardado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id ,@Valid @RequestBody ProductoDto dto) throws ResourceNotFoundException,AttributeException{
        Producto pro= productoService.update(id,dto);
        String message="producto " +pro.getNombreProducto()+ " actualizado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, message));
    } 

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id)throws ResourceNotFoundException{
        Producto pro= productoService.delete(id);
        String message="producto " +pro.getNombreProducto()+ " eliminado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, message));
    }
}
