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

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name ="producto")
public class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	private String nombre;
	private double precioUnitario;
	
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<ProductoAlmacen> producto;
	
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Venta> ventas;
	
	public Producto() {
		producto=new ArrayList<>();
		ventas = new ArrayList<>();
	}
	
	
	public Producto(int id, String nombre, Double precioUnitario) {
	
		this.id = id;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		producto=new ArrayList<>();
		ventas = new ArrayList<>();
		
	}
	public void addProducto(ProductoAlmacen productoAlmacen) {
		producto.add(productoAlmacen);
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


	public Double getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	@Override
	public String toString() {
		return "Producto =" + nombre + ", precioUnitario=" + precioUnitario ;
	}
	
	
	
}
