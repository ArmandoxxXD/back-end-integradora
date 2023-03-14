package com.integradora.gimnasio.Proveedor.controller;



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

import com.integradora.gimnasio.Productos.service.productoService;
import com.integradora.gimnasio.Proveedor.dto.proveedorDto;
import com.integradora.gimnasio.Proveedor.entity.proveedor;
import com.integradora.gimnasio.Proveedor.service.proveedorService;
import com.integradora.gimnasio.dto.Mensaje;
import com.integradora.gimnasio.exceptions.AttributeException;
import com.integradora.gimnasio.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Proveedor")
@CrossOrigin
public class proveedorController {

    @Autowired
    proveedorService proveedorService;

    @Autowired
    productoService productoService;

    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMIENTO','INSTRUCTOR','USER')")
    @GetMapping
    public ResponseEntity<List<proveedor>> getAll(){
        return ResponseEntity.ok(proveedorService.getAll());
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMIENTO','INSTRUCTOR','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<proveedor> getOne(@PathVariable("id") int id) throws ResourceNotFoundException{
        return ResponseEntity.ok(proveedorService.getOne(id));
    }

    //Obtener los productos de un proveedor 
    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMIENTO','INSTRUCTOR','USER')")
    @GetMapping("/{id}/{nombreProvedor}")
    public ResponseEntity<List<Object>>getOneAndProducts(@PathVariable("id") int id,@PathVariable("nombreProvedor") String nombreProvedor) throws ResourceNotFoundException{
        return ResponseEntity.ok(proveedorService.getProvedorProducts(id,nombreProvedor));
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Mensaje> save(@RequestBody proveedorDto dto) throws AttributeException{
        proveedor pro= proveedorService.save(dto);
        String message="proveedor " +pro.getNombreProvedor()+ " guardado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id ,@Valid @RequestBody proveedorDto dto) throws ResourceNotFoundException,AttributeException{
        proveedor pro= proveedorService.update(id,dto);
        String message="proveedor " +pro.getNombreProvedor()+ " actualizado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, message));
    } 

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id)throws ResourceNotFoundException{
        proveedor pro= proveedorService.delete(id);
        String message="proveedor " +pro.getNombreProvedor()+ " eliminado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, message));
    }

}
