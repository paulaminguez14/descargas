package com.hlc.cliente_uno_a_muchos_pedido.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{
	List<Producto> findByCategoria(Categoria categoria);
}
