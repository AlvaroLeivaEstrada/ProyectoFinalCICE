package com.leiva.model.DTO;

public class ProductoAlmacenDTO {
	
	private Long almacen;
	private Long producto;
	private int stock;
	
	public ProductoAlmacenDTO() {
		
	}
	
	public ProductoAlmacenDTO(Long almacen, Long producto, int stock) {
		this.almacen = almacen;
		this.producto = producto;
		this.stock = stock;
	}
	
	public Long getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Long almacen) {
		this.almacen = almacen;
	}

	public Long getProducto() {
		return producto;
	}

	public void setProducto(Long producto) {
		this.producto = producto;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

}
