package com.hlc.cliente_uno_a_muchos_pedido.servicio;

import java.util.List;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;

public interface ProductoServicio {
	 Producto guardarProducto(Producto producto);
	    Producto obtenerProductoPorId(Long id);
	    List<Producto> obtenerTodosLosProductos();
	    List<Producto>obtenerProductosPorCategoria(Categoria categoria);
	    Producto actualizarProducto(Long id, Producto producto);
	    void eliminarProducto(Long id);
}
