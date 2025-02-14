package com.hlc.etiquetas.servicio;

import java.util.List;

import com.hlc.etiquetas.entidad.Etiqueta;

public interface EtiquetasServicio {
	 /**
     * Guarda una etiqueta. Si el ID es nulo, crea una nueva etiqueta.
     * Si el ID ya existe, actualiza la etiqueta correspondiente.
     *
     * @param etiqueta Objeto Etiqueta a guardar o actualizar.
     * @return La Etiqueta guardada o actualizada.
     */
    Etiqueta guardarEtiqueta(Etiqueta etiqueta);

    /**
     * Obtiene una etiqueta por su ID.
     *
     * @param id Identificador único de la etiqueta.
     * @return La Etiqueta encontrada.
     */
    Etiqueta obtenerEtiquetaPorId(Long id);

    /**
     * Obtiene una lista de todas las etiquetas.
     *
     * @return Lista de todas las etiquetas.
     */
    List<Etiqueta> obtenerTodasLasEtiquetas();

    /**
     * Elimina una etiqueta por su ID.
     *
     * @param id Identificador único de la etiqueta a eliminar.
     */
    void eliminarEtiqueta(Long id);


}
