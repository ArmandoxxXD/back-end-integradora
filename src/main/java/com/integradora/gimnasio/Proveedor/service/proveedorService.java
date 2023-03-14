package com.integradora.gimnasio.Proveedor.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.integradora.gimnasio.Productos.entity.Producto;
import com.integradora.gimnasio.Proveedor.dto.proveedorDto;
import com.integradora.gimnasio.Proveedor.entity.proveedor;
import com.integradora.gimnasio.Proveedor.repository.proveedorRepository;
import com.integradora.gimnasio.exceptions.AttributeException;
import com.integradora.gimnasio.exceptions.ResourceNotFoundException;

@Service
public class proveedorService {
    
    @Autowired
    proveedorRepository proveedorRepository;

    //Poder hacer consultas MongoDB
    @Autowired
    private MongoTemplate mongoTemplate;
    

    public List<Object> getProvedorProducts(int idProvedor,String provedor){
        List<Object> entidadesAB= new ArrayList<>();
        Query queryA= new Query();
        Query queryB= new Query();

        queryA.addCriteria(Criteria.where("_id").is(idProvedor));
        queryB.addCriteria(Criteria.where("nombreProvedor").is(provedor));

        List<proveedor> listaEntidadA = mongoTemplate.find(queryA, proveedor.class);
        List<Producto> listaEntidadB = mongoTemplate.find(queryB, Producto.class);

        entidadesAB.addAll(listaEntidadA);
        entidadesAB.addAll(listaEntidadB);
        return entidadesAB;
    }

    public List<proveedor> getAll(){
        return proveedorRepository.findAll();
    }

    public proveedor getOne(int id) throws ResourceNotFoundException{
        return proveedorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
    }

    public proveedor save(proveedorDto dto) throws AttributeException{
        if(proveedorRepository.existsByNombreProvedor(dto.getNombreProvedor()))
            throw new AttributeException("Ya existe ese nombre de proveedor");
        int id=autoIncrement();
        proveedor pro = new proveedor(
            id,
            dto.getNombreProvedor(),
            dto.getTelefono(),
            dto.getEmail(),
            dto.getLogo(),
            dto.getPais(),
            dto.getEstado(),
            dto.getMunicipio(),
            dto.getCalle()
        );
        return proveedorRepository.save(pro);    
    }

    public proveedor update(int id,proveedorDto dto) throws ResourceNotFoundException,AttributeException {
        proveedor pro = proveedorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found"));
        if(proveedorRepository.existsByNombreProvedor(dto.getNombreProvedor()) && proveedorRepository.findByNombreProvedor(dto.getNombreProvedor()).get().getId() != id)
            throw new AttributeException("Ya existe ese nombre de proveedor"); 
        pro.setNombreProvedor(dto.getNombreProvedor());
        pro.setTelefono(dto.getTelefono());
        pro.setEmail(dto.getEmail());
        pro.setLogo(dto.getLogo());
        pro.setPais(dto.getPais());
        pro.setEstado(dto.getEstado());
        pro.setMunicipio(dto.getMunicipio());
        pro.setCalle(dto.getCalle());
        return proveedorRepository.save(pro);
    } 

    public proveedor delete(int id) throws ResourceNotFoundException{
        proveedor pro= proveedorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found")); 
        proveedorRepository.delete(pro);
        return pro;
    }

    //Incrementar el id de uno en uno
    private int autoIncrement(){
        List<proveedor> proveedors= proveedorRepository.findAll();
        return proveedors.isEmpty()? 1:proveedors.stream().max(Comparator.comparing(proveedor::getId)).get().getId()+1;
    }
}
