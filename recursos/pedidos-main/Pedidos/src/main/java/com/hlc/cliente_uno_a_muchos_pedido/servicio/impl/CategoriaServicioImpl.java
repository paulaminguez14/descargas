package com.hlc.cliente_uno_a_muchos_pedido.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Cliente;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Pedido;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import com.hlc.cliente_uno_a_muchos_pedido.excepcion.RecursoNoEncontradoException;
import com.hlc.cliente_uno_a_muchos_pedido.repositorio.CategoriaRepository;
import com.hlc.cliente_uno_a_muchos_pedido.repositorio.ClienteRepository;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.CategoriaServicio;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

	@Override
	public Categoria guardarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria obtenerCategoriaPorId(Long id) {
		return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con ID: " + id));
    }

	@Override
	public List<Categoria> obtenerTodasLasCategorias() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria actualizarCategoria(Long id, Categoria categoria) {
		Categoria existente = obtenerCategoriaPorId(id);
        existente.setNombre(categoria.getNombre());
        return categoriaRepository.save(existente);
	}

	@Override
	public void eliminarCategoria(Long id) {
		Categoria categoria = obtenerCategoriaPorId(id);
		categoriaRepository.delete(categoria);
	}

	@Override
	public List<Producto> obtenerProductosPorCategoria(Categoria categoria) {
		return categoria.getProductos();
	}

}
