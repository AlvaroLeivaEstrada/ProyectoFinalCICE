package com.leiva.service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.leiva.model.Almacen;
import com.leiva.model.Producto;
import com.leiva.model.ProductoAlmacen;
import com.leiva.repository.ProductoAlmacenRepository;

@Service
public class ProductoAlmacenServiceImpl {

	@Autowired
	private ProductoAlmacenRepository productoAlmacenRepostory;

	public void guadar(ProductoAlmacen productoAlmacen) {
		productoAlmacenRepostory.save(productoAlmacen);
	}

	public List<Producto> obtenerProdcutos(Long idAlmacen) {

		List<ProductoAlmacen> lista = productoAlmacenRepostory.findAll().stream()
				.filter(productoAlmacen -> productoAlmacen.getIdAlmacen().getId() == idAlmacen)
				.collect(Collectors.toList());
		List<Producto> productos = lista.stream().map(producto -> producto.getIdProducto())
				.collect(Collectors.toList());

		return productos;

	}
	public List<ProductoAlmacen> obtenerProductosDeAlmacenEnRotura(Long idAlmacen,int cantidad){
		return productoAlmacenRepostory
				.findAll()
				.stream()
				.filter(item->item.getIdAlmacen().getId()==idAlmacen && item.getStock()<cantidad)
				.collect(Collectors.toList());
				
	}

	public ProductoAlmacen obtenerProductoAlmacen(Long idAlmacen,Long idProducto) {
		
		List<ProductoAlmacen> lista = productoAlmacenRepostory
				.findAll()
				.stream()
				.filter(e -> e.getIdAlmacen().getId() == idAlmacen && e.getIdProducto().getId() == idProducto)
				.collect(Collectors.toList());
		ProductoAlmacen productoAlmacen = (lista.isEmpty())?null:lista.get(0);
		return productoAlmacen;
	}

	public void eliminar(Long idProductoAlmacen) {
		productoAlmacenRepostory.deleteById(idProductoAlmacen);
	}
	
	public List<ProductoAlmacen> encontrarAlmacenesConStockMenor(int cantidad){
		List<ProductoAlmacen> listadoAlmacenes= productoAlmacenRepostory
				.findAll()
				.stream()
				.filter(item->item.getStock()<cantidad)
				.collect(Collectors.toList());
		return listadoAlmacenes;
		
	}
	public Map<Almacen, Long> roturaStock() {
		
		Map<Almacen, Long> productosPeligro= encontrarAlmacenesConStockMenor(5)
				.stream()
				.collect(Collectors
						.groupingBy(ProductoAlmacen::getIdAlmacen,Collectors.counting()));
		

		return productosPeligro;
	}

	public List<ProductoAlmacen> listar() {
		return productoAlmacenRepostory.findAll();
	}

}
