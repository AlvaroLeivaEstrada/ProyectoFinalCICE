package com.leiva.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leiva.model.Venta;
import com.leiva.repository.VentaRepository;

@Service
public class VentaServiceImp {
	
	@Autowired
	private VentaRepository ventaRepository;
	
	public void guadar(Venta venta) {
		ventaRepository.save(venta);
	}
	public Double sumaVentas(int idaAlmcen){
		Double suma=0.0;
		
		if(idaAlmcen==0) {
			suma= ventaRepository
					.findAll()
					.stream()
					.map(item->item.getProducto().getPrecioUnitario())
					.reduce(0.0,Double::sum);
		}else {
			suma = obtenerVentasPorAlmacen(idaAlmcen)
			.stream()
			.map(item->item.getProducto().getPrecioUnitario())
			.reduce(0.0,Double::sum);
		}

		return suma;
	}
	public List<Venta> obtenerVentasPorAlmacen(int idAlmacen) {
		List<Venta> listado = ventaRepository
				.findAll().stream()
				.filter(venta->venta.getAlmacen().getId()==idAlmacen)
				.collect(Collectors.toList());
		return listado;
	}
	public List<Venta> obtenerVentaPorFecha(int fecha){
		List<Venta> listadoPorFechaList = ventaRepository
				.findAll()
				.stream()
				.filter(venta->venta.getAnoVenta()==fecha)
				.collect(Collectors.toList());
		return listadoPorFechaList;
	}
	public void eliminar(Long idVenta) {
		ventaRepository.deleteById(idVenta);
	}
	public List<Venta> listar(){
		return ventaRepository.findAll();
	}
	

}
