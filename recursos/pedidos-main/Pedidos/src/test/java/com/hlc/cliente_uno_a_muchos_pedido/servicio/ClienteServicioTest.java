package com.hlc.cliente_uno_a_muchos_pedido.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Cliente;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import com.hlc.cliente_uno_a_muchos_pedido.repositorio.ClienteRepository;
import com.hlc.cliente_uno_a_muchos_pedido.repositorio.ProductoRepository;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.impl.ClienteServicioImpl;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.impl.ProductoServicioImpl;

class ClienteServicioTest {

	@Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServicioImpl clienteServicio;

    private Cliente cliente1;
    private Cliente cliente2;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente1 = new Cliente();
        cliente1.setNombre("Juan Pérez");
        cliente1.setTelefono("123456789");
        cliente1.setDireccion("Calle Principal 123");

        cliente2 = new Cliente();
        cliente2.setNombre("Ana Gómez");
        cliente2.setTelefono("987654321");
        cliente2.setDireccion("Avenida Secundaria 456");
    }
    
    @Test
    @DisplayName("💾 Debe guardar un cliente correctamente")
    void guardarCliente_DeberiaRetornarClienteGuardado() {
        when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        Cliente resultado = clienteServicio.guardarCliente(cliente1);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Juan Pérez");
        verify(clienteRepository, times(1)).save(cliente1); // 📌 Verifica que el método fue llamado 1 vez
    }

}
