package com.hlc.etiquetas.entidad;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EtiquetaTest {
	
	   @Test
	   void testConstructorConParametros() {
	        // Crear una etiqueta usando el constructor con parámetros
	        Etiqueta etiqueta = new Etiqueta("Tarea urgente", "Etiqueta para tareas urgentes", true, 1);

	        // Validar que los atributos se inicializan correctamente
	        assertEquals("Tarea urgente", etiqueta.getNombre(), "El nombre no es el esperado");
	        assertEquals("Etiqueta para tareas urgentes", etiqueta.getDescripcion(), "La descripción no es la esperada");
	        assertTrue(etiqueta.getActivo(), "El estado activo no es el esperado");
	        assertEquals(1, etiqueta.getPrioridad(), "La prioridad no es la esperada");
	    }

	    @Test
	    void testSettersYGetters() {
	        // Crear una etiqueta vacía usando el constructor por defecto
	        Etiqueta etiqueta = new Etiqueta();

	        // Asignar valores a los atributos usando setters
	        etiqueta.setNombre("Tarea normal");
	        etiqueta.setDescripcion("Etiqueta para tareas normales");
	        etiqueta.setActivo(false);
	        etiqueta.setPrioridad(3);

	        // Validar que los valores asignados son correctos
	        assertEquals("Tarea normal", etiqueta.getNombre(), "El nombre no es el esperado");
	        assertEquals("Etiqueta para tareas normales", etiqueta.getDescripcion(), "La descripción no es la esperada");
	        assertFalse(etiqueta.getActivo(), "El estado activo no es el esperado");
	        assertEquals(3, etiqueta.getPrioridad(), "La prioridad no es la esperada");
	    }


	    @Test
	    void testEstadoActivo() {
	        // Crear etiqueta y verificar el estado activo
	        Etiqueta etiqueta = new Etiqueta("Etiqueta activa", "Descripción", true, 2);
	        assertTrue(etiqueta.getActivo(), "La etiqueta debería estar activa");

	        // Cambiar el estado a inactivo
	        etiqueta.setActivo(false);
	        assertFalse(etiqueta.getActivo(), "La etiqueta debería estar inactiva");
	    }

}
