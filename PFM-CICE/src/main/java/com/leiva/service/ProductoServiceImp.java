package com.leiva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leiva.model.Producto;
import com.leiva.repository.ProductoRepository;

@Service
public class ProductoServiceImp {
	
	@Autowired
	private ProductoRepository productoRespository;
	
	public void guardar(Producto producto) {
		productoRespository.save(producto);
	}
	public Producto obtener(Long idProductoLong) {
		Optional<Producto> optional = productoRespository.findById(idProductoLong);
		if(optional.isPresent()) return optional.get();
		return optional.get();
	}
	public void eliminar(Long idProducto) {
		productoRespository.deleteById(idProducto);
	}
	public List<Producto> listar(){
		return productoRespository.findAll();
	}
	
	

}
