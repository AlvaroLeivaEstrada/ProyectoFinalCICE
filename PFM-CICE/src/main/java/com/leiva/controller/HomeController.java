package com.leiva.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.leiva.model.Almacen;
import com.leiva.model.Producto;
import com.leiva.model.ProductoAlmacen;
import com.leiva.model.Venta;
import com.leiva.model.DTO.ProductoAlmacenDTO;
import com.leiva.service.AlmacenServiceImp;
import com.leiva.service.ProductoAlmacenServiceImpl;
import com.leiva.service.ProductoServiceImp;
import com.leiva.service.VentaServiceImp;
import com.leiva.util.Utilitie;


@Controller
public class HomeController {
	
	@Autowired
	private AlmacenServiceImp almacenService;
	
	@Autowired
	private ProductoServiceImp productoServImp;
	
	@Autowired
	private ProductoAlmacenServiceImpl productoAlmacenSer;
	
	@Autowired
	private VentaServiceImp ventaServiceImp;
	
	
	 /**
	 * Metodo que muestra la lista de almacenes en el sistema.
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String listAll(Model model) {
		List<Almacen> listado= almacenService.listar();
		model.addAttribute("almacenes",listado);
		return "listar";
	}
	 /**
		 * Metodo que muestra la lista de productos que tiene un almacen.
		 * @param model
		 * @param id
		 * @return
		 */
	@RequestMapping(value = {"/listarProducto"},method = RequestMethod.GET)
	public String productos(
			@RequestParam("id")Long id,
			Model model) {
		model.addAttribute("productos",productoAlmacenSer.obtenerProdcutos(id));
		return "producto";
	}
	
	 /**
		 * Metodo que realiza la compra que hace un usuario en un almacen
		 * @param model
		 * @param idProducto
		 * @param idAlmacen
		 * @return
		 */
	@RequestMapping(value = {"/venta"},method = RequestMethod.GET)
	public String compra(
			@RequestParam("id")Long id,
			@RequestParam("almacen")Long idAlmacen,
			Model model) {
		int fecha = Utilitie.getAleatorio(2015, 2020);
		Almacen almacen = almacenService.obtener(idAlmacen);
		Producto producto = productoServImp.obtener(id);
		Venta venta= new Venta(producto,almacen,fecha);
		ventaServiceImp.guadar(venta);
		return "compraExitosa";
	}
	 /**
		 * Metodo que muestra el total de ventas realizadas por almacen
		 * @param model
		 * @param idAlmacen
		 * @return
		 */
	@RequestMapping(value = {"/obtenerVentas"},method = RequestMethod.GET)
	public String ventas(
			@RequestParam("idAlmacen")int id,
			Model model) {
		model.addAttribute("ventas",ventaServiceImp.obtenerVentasPorAlmacen(id));
		
		return "venta";
	}
	 /**
		 * Metodo que realiza la busquedad de las ventas realizadas en un año especifico.
		 * @param model
		 * @param venta
		 * @return
		 */
	@RequestMapping(value = {"/busquedad"},method = RequestMethod.POST)
	public String busquedad(@Valid @ModelAttribute("venta") Venta venta
			,Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			return "ErrorBusqueda";
		} else {
			List<Venta> listadoPorFecha= ventaServiceImp.obtenerVentaPorFecha(venta.getAnoVenta());
			model.addAttribute("ventas",listadoPorFecha);
			return "venta";
		}

	}
	 /**
		 * Metodo que realiza la lista de todos los productos disponibles para adquisicion de productos para un almacen
		 * @param model
		 * @param id
		 * @return
		 */
	@RequestMapping(value = {"/adquirirProducto"}, method = RequestMethod.GET)
	public String adquirirProducto(
			@RequestParam("almacen_id")Long id,
			Model model) {
		
		List<Producto> productosDisponibles = productoServImp.listar();
		model.addAttribute("productos",productosDisponibles);
		
		return "adquirir";
	}
	 /**
		 * Metodo que realiza la compra de un producto par un almacen.
		 * @param model
		 * @param productoAlmacenDTO
		 * @return
		 */
	@RequestMapping(value = {"/agregarProducto"},method = RequestMethod.POST)
	public String agregarProducto(
			@ModelAttribute("productoAlmacenDTO") ProductoAlmacenDTO productoAlmacenDTO,
			Model model,BindingResult result) {
		
		Almacen almacen=almacenService.obtener(productoAlmacenDTO.getAlmacen());
		Producto producto=productoServImp.obtener(productoAlmacenDTO.getProducto());
		
		ProductoAlmacen productoAlmacen = productoAlmacenSer
											.obtenerProductoAlmacen(productoAlmacenDTO.getAlmacen(), productoAlmacenDTO.getProducto());
		
		if(productoAlmacen!=null) {
			productoAlmacen.incrStock(productoAlmacenDTO.getStock());
			productoAlmacenSer.guadar(productoAlmacen);
		}else {
			productoAlmacenSer.guadar(new ProductoAlmacen(almacen, producto, productoAlmacenDTO.getStock()));
		}
		return "AdquisicionProducto";
	}
	 /**
		 * Metodo que lista los almacenes con el numero de porductos en rotura
		 * @param model
		 * @return
		 */
	@RequestMapping(value = {"/roturaStock"},method = RequestMethod.GET)
	public String roturaStock(Model model) {
		Map<Almacen, Long>roturaStockMap=productoAlmacenSer.roturaStock();
		model.addAttribute("roturaStock",roturaStockMap);
		return"roturaStock";
	}
	 /**
		 * Metodo que muestra la lista de productos en rotura.
		 * @param model
		 * @param almacenId
		 * @return
		 */
	@RequestMapping(value = {"/roturaStockProductos"},method = RequestMethod.GET)
	public String productosRotura(
			@RequestParam("almacenId")Long almacenId,
			Model model) {
		List<ProductoAlmacen> productos = productoAlmacenSer.obtenerProductosDeAlmacenEnRotura(almacenId, 5);
		model.addAttribute("productos",productos);
		return"vistaProductos";
	}

	
	 /**
		 * Metodo que inicializa instancias de venta y productoAlmacenDTO.
		 */
	@ModelAttribute
	public void setGenericos(Model model){
		ProductoAlmacenDTO productoAlmacenDTO=new ProductoAlmacenDTO();
		Venta venta = new Venta();
		model.addAttribute("venta", venta);
		model.addAttribute("productoAlmacenDTO", productoAlmacenDTO);
	}
}
