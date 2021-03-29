package com.leiva.model.DTO;

public class VentaDTO {
	

	private int unidadesProducto;
	private double euros;
	
	public VentaDTO() {
		
	}
	public VentaDTO(int unidadesProducto, int euros) {
		this.unidadesProducto = unidadesProducto;
		this.euros = euros;
	}
	

	public int getUnidadesProducto() {
		return unidadesProducto;
	}
	public void setUnidadesProducto(int unidadesProducto) {
		this.unidadesProducto = unidadesProducto;
	}
	public double getEuros() {
		return euros;
	}
	public void setEuros(double euros) {
		this.euros = euros;
	}
	@Override
	public String toString() {
		return "VentaDTO [unidadesProducto=" + unidadesProducto + ", euros=" + euros + "]";
	}
	

}
