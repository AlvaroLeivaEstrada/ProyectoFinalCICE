package com.leiva.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="producto_almacen")
public class ProductoAlmacen implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@ManyToOne
	@JsonBackReference
	private Almacen almacen;
	
	@ManyToOne
	//@JoinColumn(name = "producto_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "ProductoFK"))
	@JsonBackReference
	private Producto producto;
	
	private int stock;
	
	
	public ProductoAlmacen() {
		
	}
	public ProductoAlmacen(Almacen almacen,Producto producto, int cantidad) {
		this.almacen=almacen;
		this.producto=producto;
		stock=cantidad;
	}
	
	public Almacen getIdAlmacen() {
		return almacen;
	}
	public void setIdAlmacen(Almacen idAlmacen) {
		this.almacen = idAlmacen;
	}
	public Producto getIdProducto() {
		return producto;
	}
	public void setIdProducto(Producto idProducto) {
		this.producto = idProducto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void incrStock(int cantidad) {
		this.stock= stock+cantidad;
	}
	public void decrStock() {
		this.stock--;
	}


	@Override
	public String toString() {
		return "Almacen=" + almacen + ", Producto=" + producto + ", Stock=" + stock ;
	}
	
	
	

}
