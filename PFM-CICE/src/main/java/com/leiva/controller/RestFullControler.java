package com.leiva.controller;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leiva.model.Almacen;
import com.leiva.model.Producto;
import com.leiva.model.Venta;
import com.leiva.model.DTO.VentaDTO;
import com.leiva.service.VentaServiceImp;

@RestController
@RequestMapping("/API")
public class RestFullControler {
	private final int maxProductos=10;
	
	@Autowired
	VentaServiceImp ventaServiceImp;
	
	
	@GetMapping(value = "/totalVentas")
	public ResponseEntity<VentaDTO> ventasTotales() {
		VentaDTO ventaDTO = new VentaDTO();
		ventaDTO.setUnidadesProducto(ventaServiceImp.listar().size());
		ventaDTO.setEuros(ventaServiceImp.sumaVentas(0));
		return ResponseEntity.ok(ventaDTO);
	}
	@GetMapping(value = "/ventasPorAlmacen")
	public ResponseEntity<Map<Almacen, DoubleSummaryStatistics>> vetasPorAlmacen(){
		
		Map<Almacen, DoubleSummaryStatistics> ventasPorAlmacenMap=
		ventaServiceImp
		.listar()
		.stream()
		.collect(Collectors
				.groupingBy(Venta::getAlmacen,Collectors.summarizingDouble(item->item.getProducto().getPrecioUnitario())));
		
		return ResponseEntity.ok(ventasPorAlmacenMap);
	}
	
	@GetMapping(value = "/ventasPorFecha")
	public ResponseEntity<Map<Integer, DoubleSummaryStatistics>> ventasPorAno(){
		Map<Integer, DoubleSummaryStatistics> ventasPorAno = ventaServiceImp
				.listar()
				.stream()
				.collect(Collectors.groupingBy(Venta::getAnoVenta,Collectors.summarizingDouble(item->item.getProducto().getPrecioUnitario())));
		return ResponseEntity.ok(ventasPorAno);
	}
	
	@GetMapping(value = "/productosMasVendidos")
	public ResponseEntity<Map<Producto,Long>> productosMasVendidos(){
		Map<Producto, Long> productos = ventaServiceImp
				.listar()
				.stream()
				.collect(Collectors.groupingBy(Venta::getProducto,Collectors.counting()))
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(maxProductos)
				.collect(Collectors.toMap(Map.Entry :: getKey,Map.Entry::getValue));
	
		return ResponseEntity.ok(productos);
	}
}
