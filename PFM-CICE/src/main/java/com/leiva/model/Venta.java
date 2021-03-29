package com.leiva.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ventas")
public class Venta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long idVenta;
	@ManyToOne
	@JsonBackReference
	private Producto producto;
	
	@ManyToOne
	@JsonBackReference
	private Almacen almacen;
	
	@Min(value = 2015, message = "Not allowed")
	@Max(value = 2021,message = "Not allowed")
	private int anoVenta;
	
	public Venta() {
		
	}
	
	
	public Venta(Producto idProducto, Almacen idAlmacen, int anoVenta) {
		
		this.producto = idProducto;
		this.almacen = idAlmacen;
		this.anoVenta = anoVenta;
	}


	public long getIdVenta() {
		return idVenta;
	}


	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Almacen getAlmacen() {
		return almacen;
	}


	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}


	public int getAnoVenta() {
		return anoVenta;
	}


	public void setAnoVenta(int anoVenta) {
		this.anoVenta = anoVenta;
	}


	
	
	

}
