package com.integradora.gimnasio.controller;

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

import com.integradora.gimnasio.dto.ClienteDto;
import com.integradora.gimnasio.dto.Mensaje2;
import com.integradora.gimnasio.entity.Cliente;
import com.integradora.gimnasio.service.ClienteService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMEINTO','INSTRUCTOR','USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<Cliente>> list(){
        List<Cliente>list = clienteService.list();
        return new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMEINTO','INSTRUCTOR','USER')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") int id){
        if(!clienteService.existsById(id))
            return new ResponseEntity(new Mensaje2("no existe"), HttpStatus.NOT_FOUND);
        Cliente cliente=clienteService.getOne(id).get();
        return new ResponseEntity(cliente, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANTENIMEINTO','INSTRUCTOR','USER')")
    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Cliente> getByNombre(@PathVariable("nombre") String nombre){
        if(!clienteService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje2("no existe"), HttpStatus.NOT_FOUND);
        Cliente cliente=clienteService.getByNombre(nombre).get();
        return new ResponseEntity(cliente, HttpStatus.OK);  
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){
        if(StringUtils.isBlank(clienteDto.getNombre()))
            return new ResponseEntity(new Mensaje2("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(clienteService.existsByNombre(clienteDto.getNombre()))
            return new ResponseEntity(new Mensaje2("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Cliente cliente= new Cliente(clienteDto.getNombre());
        clienteService.save(cliente);
        return new ResponseEntity(new Mensaje2("Cliente Guardado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ClienteDto clienteDto){
        if(!clienteService.existsById(id))
            return new ResponseEntity(new Mensaje2("no existe"), HttpStatus.NOT_FOUND);
        if(clienteService.existsByNombre(clienteDto.getNombre()) && clienteService.getByNombre(clienteDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje2("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(clienteDto.getNombre()))
            return new ResponseEntity(new Mensaje2("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        Cliente cliente= clienteService.getOne(id).get();
        cliente.setNombre(clienteDto.getNombre());
        clienteService.save(cliente);
        return new ResponseEntity(new Mensaje2("Cliente Actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!clienteService.existsById(id))
            return new ResponseEntity(new Mensaje2("no existe"), HttpStatus.NOT_FOUND);
        clienteService.delete(id);
        return new ResponseEntity(new Mensaje2("Cliente Eliminado"), HttpStatus.OK);
    }
}
