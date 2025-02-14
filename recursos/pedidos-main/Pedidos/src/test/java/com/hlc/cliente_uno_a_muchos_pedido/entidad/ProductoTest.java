package com.hlc.cliente_uno_a_muchos_pedido.entidad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductoTest {

	private Producto producto;
	
	@BeforeEach
    void setUp() {
		producto = new Producto();
		producto.setId(1L);
		producto.setNombre("NombreProducto");
		producto.setDescripcion("Producto de prueba");
		producto.setPeso(8.0);
		producto.setStock(3);
		
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		producto.setCategoria(categoria);
    }
	
	@Test
    @DisplayName("Debe crear un producto con valores válidos")
    void crearPedido_DeberiaTenerValoresValidos() {
        assertThat(producto.getId()).isEqualTo(1L);
        assertThat(producto.getNombre()).isEqualTo("NombreProducto");
        assertThat(producto.getDescripcion()).isEqualTo("Producto de prueba");
        assertThat(producto.getPeso()).isEqualTo(8.0);
        assertThat(producto.getStock()).isEqualTo(3);
        assertThat(producto.getCategoria()).isNotNull();
    }

	@Test
    @DisplayName("Debe modificar correctamente la descripción del producto")
    void modificarDescripcion_DeberiaActualizarDescripcion() {
        String nuevaDescripcion = "Nuevo producto actualizado";
        producto.setDescripcion(nuevaDescripcion);
        assertThat(producto.getDescripcion()).isEqualTo(nuevaDescripcion);
    }
}
