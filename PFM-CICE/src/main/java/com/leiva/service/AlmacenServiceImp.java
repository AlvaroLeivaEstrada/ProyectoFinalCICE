package com.leiva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leiva.model.Almacen;
import com.leiva.repository.AlmacenRepository;

@Service
public class AlmacenServiceImp {
	
	@Autowired
	private AlmacenRepository almacenRepository;
	
	public void guardar(Almacen almacen) {
		almacenRepository.save(almacen);
	}
	public Almacen obtener(Long idAlmacen) {
		Optional<Almacen> optional = almacenRepository.findById(idAlmacen);
		if(optional.isPresent()) return optional.get();
		return optional.get();
	}
	
	public void eliminar(Long idAlmacen) {
		almacenRepository.deleteById(idAlmacen);
	}
	public List<Almacen> listar(){
		return almacenRepository.findAll();
	}
	

}
