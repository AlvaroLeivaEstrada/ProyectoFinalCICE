package com.leiva.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="almacen")
public class Almacen implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	@NonNull
	private String nombre;
	@NonNull
	private String ubicacion;
	
	@OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<ProductoAlmacen> almacen;
	
	@OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Venta> ventas;
	
	
	public Almacen() {
		almacen= new ArrayList<ProductoAlmacen>();
		ventas = new ArrayList<Venta>();
	}
	
	public Almacen(int id, String nombre, String ubicacion) {
		
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		almacen= new ArrayList<ProductoAlmacen>();
		ventas = new ArrayList<Venta>();
	}
	
	public void addProducto(ProductoAlmacen productoAlmacen) {
		almacen.add(productoAlmacen);
	}
	public void addVentas(Venta venta) {
		ventas.add(venta);
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return  nombre ;
	}
	
	

}
