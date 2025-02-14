package com.hlc.cliente_uno_a_muchos_pedido.servicio;

import java.util.List;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;

public interface CategoriaServicio {
	Categoria guardarCategoria(Categoria categoria);
	Categoria obtenerCategoriaPorId(Long id);
	 List<Categoria> obtenerTodasLasCategorias();
	 Categoria actualizarCategoria(Long id, Categoria categoria);
	 void eliminarCategoria(Long id);
	 List<Producto>obtenerProductosPorCategoria(Categoria categoria);
}
