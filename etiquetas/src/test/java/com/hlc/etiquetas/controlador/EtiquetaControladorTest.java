package com.hlc.etiquetas.controlador;

import com.hlc.etiquetas.entidad.Etiqueta;
import com.hlc.etiquetas.servicio.EtiquetasServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EtiquetaControlador.class)
public class EtiquetaControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EtiquetasServicio etiquetaServicio;

    /**
     * Configuración inicial que se ejecuta antes de cada prueba.
     * Se utiliza el método reset para asegurarse de que cualquier interacción previa
     * con el servicio mock no afecte las pruebas actuales.
     */
    @BeforeEach
    void setUp() {
        reset(etiquetaServicio);
    }

    /**
     * Prueba que el controlador llama al servicio para obtener una lista de etiquetas y
     * renderiza la vista 'lista'.
     */
    @Test
    @DisplayName("Debe listar todas las etiquetas y mostrar la vista correcta")
    void testListarEtiquetas() throws Exception {
        when(etiquetaServicio.obtenerTodasLasEtiquetas()).thenReturn(
                Arrays.asList(
                        new Etiqueta("Etiqueta 1", "Descripcion 1", true, 1),
                        new Etiqueta("Etiqueta 2", "Descripcion 2", false, 3)
                )
        );

        mockMvc.perform(get("/etiquetas"))
                .andExpect(status().isOk())
                .andExpect(view().name("etiquetas/lista"))
                .andExpect(model().attributeExists("etiquetas"))
                .andExpect(model().attribute("etiquetas", hasSize(2)));

        verify(etiquetaServicio, times(1)).obtenerTodasLasEtiquetas();
    }

    /**
     * Prueba que el controlador renderiza la vista 'formulario' para agregar una nueva etiqueta
     * y pasa un objeto vacío de tipo Etiqueta al modelo.
     */
    @Test
    @DisplayName("Debe mostrar el formulario para agregar una nueva etiqueta")
    void testMostrarFormularioNuevaEtiqueta() throws Exception {
        mockMvc.perform(get("/etiquetas/nueva"))
                .andExpect(status().isOk())
                .andExpect(view().name("etiquetas/formulario"))
                .andExpect(model().attributeExists("etiqueta"))
                .andExpect(model().attribute("etiqueta", instanceOf(Etiqueta.class)));
    }

    /**
     * Prueba que el controlador guarda una etiqueta a través del servicio y redirige correctamente
     * a la lista de etiquetas.
     */
    @Test
    @DisplayName("Debe guardar una etiqueta y redirigir a la lista de etiquetas")
    void testGuardarEtiqueta() throws Exception {
        mockMvc.perform(post("/etiquetas")
                .param("nombre", "Etiqueta 1")
                .param("descripcion", "Descripcion 1")
                .param("activo", "true")
                .param("prioridad", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/etiquetas"));

        verify(etiquetaServicio, times(1)).guardarEtiqueta(any(Etiqueta.class));
    }

    /**
     * Prueba que el controlador detecta errores de validación al intentar guardar una etiqueta
     * y regresa a la vista del formulario sin procesar el guardado.
     */
    @Test
    @DisplayName("Debe volver al formulario si hay errores de validación al guardar una etiqueta")
    void testGuardarEtiquetaConErrores() throws Exception {
        mockMvc.perform(post("/etiquetas")
                .param("nombre", "") // Nombre vacío, no válido
                .param("descripcion", "Descripcion 1")
                .param("activo", "true")
                .param("prioridad", "6")) // Prioridad fuera de rango
                .andExpect(status().isOk())
                .andExpect(view().name("etiquetas/formulario"));
    }

    /**
     * Prueba que el controlador carga una etiqueta existente desde el servicio y la pasa
     * al formulario de edición.
     */
    @Test
    @DisplayName("Debe mostrar el formulario para editar una etiqueta existente")
    void testMostrarFormularioEditarEtiqueta() throws Exception {
        Etiqueta etiqueta = new Etiqueta("Etiqueta 1", "Descripcion 1", true, 1);
        etiqueta.setId(1L);
        when(etiquetaServicio.obtenerEtiquetaPorId(1L)).thenReturn(etiqueta);

        mockMvc.perform(get("/etiquetas/editar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("etiquetas/formulario"))
                .andExpect(model().attributeExists("etiqueta"))
                .andExpect(model().attribute("etiqueta", etiqueta));
    }

    /**
     * Prueba que el controlador actualiza una etiqueta existente a través del servicio
     * y redirige correctamente a la lista de etiquetas.
     */
    @Test
    @DisplayName("Debe actualizar una etiqueta existente y redirigir a la lista de etiquetas")
    void testActualizarEtiqueta() throws Exception {
        mockMvc.perform(post("/etiquetas/1")
                .param("nombre", "Etiqueta Actualizada")
                .param("descripcion", "Descripcion Actualizada")
                .param("activo", "false")
                .param("prioridad", "4"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/etiquetas"));

        verify(etiquetaServicio, times(1)).guardarEtiqueta(any(Etiqueta.class));
    }

    /**
     * Prueba que el controlador elimina una etiqueta a través del servicio utilizando su ID
     * y redirige correctamente a la lista de etiquetas.
     */
    @Test
    @DisplayName("Debe eliminar una etiqueta y redirigir a la lista de etiquetas")
    void testEliminarEtiqueta() throws Exception {
        mockMvc.perform(get("/etiquetas/eliminar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/etiquetas"));

        verify(etiquetaServicio, times(1)).eliminarEtiqueta(1L);
    }
}


