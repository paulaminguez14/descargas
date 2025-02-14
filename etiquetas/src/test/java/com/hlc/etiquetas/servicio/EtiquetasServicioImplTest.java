package com.hlc.etiquetas.servicio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.hlc.etiquetas.entidad.Etiqueta;
import com.hlc.etiquetas.repositorio.EtiquetaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtiquetaServicioImplTest {

    @Mock
    private EtiquetaRepositorio etiquetaRepositorio;

    @InjectMocks
    private EtiquetasServicioImpl etiquetaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe guardar una etiqueta correctamente")
    void testGuardarEtiqueta() {
        Etiqueta etiqueta = new Etiqueta("Etiqueta 1", "Descripcion 1", true, 1);

        when(etiquetaRepositorio.save(etiqueta)).thenReturn(etiqueta);

        Etiqueta resultado = etiquetaServicio.guardarEtiqueta(etiqueta);

        assertNotNull(resultado);
        assertEquals("Etiqueta 1", resultado.getNombre());
        verify(etiquetaRepositorio, times(1)).save(etiqueta);
    }

    @Test
    @DisplayName("Debe obtener una etiqueta por ID correctamente")
    void testObtenerEtiquetaPorId() {
        Etiqueta etiqueta = new Etiqueta("Etiqueta 1", "Descripcion 1", true, 1);
        etiqueta.setId(1L);

        when(etiquetaRepositorio.findById(1L)).thenReturn(Optional.of(etiqueta));

        Etiqueta resultado = etiquetaServicio.obtenerEtiquetaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(etiquetaRepositorio, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar una excepción si no se encuentra la etiqueta por ID")
    void testObtenerEtiquetaPorIdNoEncontrada() {
        when(etiquetaRepositorio.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> etiquetaServicio.obtenerEtiquetaPorId(1L));

        assertEquals("Etiqueta no encontrada con ID: 1", exception.getMessage());
        verify(etiquetaRepositorio, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe obtener todas las etiquetas correctamente")
    void testObtenerTodasLasEtiquetas() {
        List<Etiqueta> etiquetas = Arrays.asList(
                new Etiqueta("Etiqueta 1", "Descripcion 1", true, 1),
                new Etiqueta("Etiqueta 2", "Descripcion 2", false, 3)
        );

        when(etiquetaRepositorio.findAll()).thenReturn(etiquetas);

        List<Etiqueta> resultado = etiquetaServicio.obtenerTodasLasEtiquetas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(etiquetaRepositorio, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe eliminar una etiqueta existente por ID correctamente")
    void testEliminarEtiquetaExistente() {
        // Configuración del mock
        Long idEtiqueta = 1L;
        when(etiquetaRepositorio.existsById(idEtiqueta)).thenReturn(true);
        doNothing().when(etiquetaRepositorio).deleteById(idEtiqueta);

        // Ejecución del método
        etiquetaServicio.eliminarEtiqueta(idEtiqueta);

        // Verificaciones
        verify(etiquetaRepositorio, times(1)).existsById(idEtiqueta);
        verify(etiquetaRepositorio, times(1)).deleteById(idEtiqueta);
    }

    @Test
    @DisplayName("Debe lanzar una excepción al intentar eliminar una etiqueta inexistente")
    void testEliminarEtiquetaInexistente() {
        // Configuración del mock
        Long idEtiqueta = 1L;
        when(etiquetaRepositorio.existsById(idEtiqueta)).thenReturn(false);

        // Ejecución y verificación de la excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> etiquetaServicio.eliminarEtiqueta(idEtiqueta));
        assertEquals("Etiqueta no encontrada con ID: 1", exception.getMessage());

        // Verificaciones
        verify(etiquetaRepositorio, times(1)).existsById(idEtiqueta);
        verify(etiquetaRepositorio, never()).deleteById(anyLong());
    }
    
    @Test
    @DisplayName("Debe actualizar una etiqueta existente sin crear duplicados")
    void testActualizarEtiquetaSinDuplicados() {
        // Configuración del mock
        Long idEtiqueta = 1L;
        Etiqueta etiquetaExistente = new Etiqueta("Etiqueta Original", "Descripción Original", true, 1);
        etiquetaExistente.setId(idEtiqueta);

        Etiqueta etiquetaActualizada = new Etiqueta("Etiqueta Actualizada", "Descripción Actualizada", false, 2);
        etiquetaActualizada.setId(idEtiqueta);

        // Simular la existencia del ID en el repositorio
        when(etiquetaRepositorio.existsById(idEtiqueta)).thenReturn(true);
        when(etiquetaRepositorio.findById(idEtiqueta)).thenReturn(Optional.of(etiquetaExistente));
        when(etiquetaRepositorio.save(any(Etiqueta.class))).thenReturn(etiquetaActualizada);

        // Llamar al método del servicio
        Etiqueta resultado = etiquetaServicio.guardarEtiqueta(etiquetaActualizada);

        // Verificar el comportamiento
        verify(etiquetaRepositorio, times(1)).existsById(idEtiqueta);
        verify(etiquetaRepositorio, times(1)).findById(idEtiqueta);
        verify(etiquetaRepositorio, times(1)).save(etiquetaExistente); // El objeto existente debe actualizarse

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals("Etiqueta Actualizada", resultado.getNombre());
        assertEquals("Descripción Actualizada", resultado.getDescripcion());
        assertEquals(false, resultado.getActivo());
        assertEquals(2, resultado.getPrioridad());
    }
  
}
