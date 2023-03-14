package com.integradora.gimnasio.Productos.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integradora.gimnasio.Productos.dto.ProductoDto;
import com.integradora.gimnasio.Productos.entity.Producto;
import com.integradora.gimnasio.Productos.repository.productoRepository;
import com.integradora.gimnasio.Proveedor.repository.proveedorRepository;
import com.integradora.gimnasio.exceptions.AttributeException;
import com.integradora.gimnasio.exceptions.ResourceNotFoundException;

@Service
public class productoService {
    
    @Autowired
    productoRepository productoRepository;

    @Autowired
    proveedorRepository proveedorRepository;

    public List<Producto> getAll(){
        return productoRepository.findAll();
    }

    public List<Producto> getAllByProveedor(String Prove){
        return productoRepository.findByNombreProvedor(Prove);
    }

    public List<Producto> getAllByCategoria(String Categoria){
        return productoRepository.findByCategoria(Categoria);
    }

    public Producto getOne(int id) throws ResourceNotFoundException{
        return productoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
    }

    public Producto save(ProductoDto dto) throws AttributeException{
        if(productoRepository.existsByNombreProducto(dto.getNombreProducto()))
            throw new AttributeException("Ya existe ese nombre de producto");
        int id=autoIncrement();
        Producto pro = new Producto(
            id,
            dto.getNombreProducto(),
            dto.getImagen(),
            dto.getCantidad(),
            dto.getPrecio(),
            dto.isExistencia(),
            dto.isPorAgotarse(),
            dto.getNombreProvedor(),
            dto.getCategoria()
        );
        return productoRepository.save(pro);    
    }

    public Producto update(int id,ProductoDto dto) throws ResourceNotFoundException,AttributeException {
        Producto pro = productoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found"));
        if(productoRepository.existsByNombreProducto(dto.getNombreProducto()) && productoRepository.findByNombreProducto(dto.getNombreProducto()).get().getId() != id)
            throw new AttributeException("Ya existe ese nombre de producto"); 
        pro.setNombreProducto(dto.getNombreProducto());
        pro.setImagen(dto.getImagen());
        pro.setCantidad(dto.getCantidad());
        pro.setPrecio(dto.getPrecio());
        pro.setExistencia(dto.isExistencia());
        pro.setPorAgotarse(dto.isPorAgotarse());
        pro.setNombreProvedor(dto.getNombreProvedor());
        pro.setCategoria(dto.getCategoria());
        return productoRepository.save(pro);
    } 

    public Producto delete(int id) throws ResourceNotFoundException{
        Producto pro= productoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found")); 
        productoRepository.delete(pro);
        return pro;
    }

    //Incrementar el id de uno en uno
    private int autoIncrement(){
        List<Producto> products= productoRepository.findAll();
        return products.isEmpty()? 1:products.stream().max(Comparator.comparing(Producto::getId)).get().getId()+1;
    }

}
