package com.hlc.cliente_uno_a_muchos_pedido.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Cliente;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;

@DataJpaTest
class ProductoRepositoryTest {

	@Autowired
    private ProductoRepository productoRepository;

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Laptop Dell");
        producto1.setStock(10);

        producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Monitor LG");
        producto2.setStock(5);
    }
    
    @Test
    @DisplayName("📋 Debe recuperar todos los productos almacenados")
    void recuperarTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();

        assertThat(productos).hasSize(2); // 📌 Deberíamos tener exactamente 2 clientes
        assertThat(productos).extracting(Producto::getNombre).contains("Laptop Dell", "Monitor LG"); // 📌 Validamos sus nombres
    }
}
