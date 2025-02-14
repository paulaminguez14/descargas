package com.hlc.cliente_uno_a_muchos_pedido.servicio.impl;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import com.hlc.cliente_uno_a_muchos_pedido.repositorio.ProductoRepository;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.ProductoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPeso(productoActualizado.getPeso());
            producto.setStock(productoActualizado.getStock());
            return productoRepository.save(producto);
        }).orElse(null);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

	@Override
	public List<Producto> obtenerProductosPorCategoria(Categoria categoria) {
		return productoRepository.findByCategoria(categoria);
	}
}
