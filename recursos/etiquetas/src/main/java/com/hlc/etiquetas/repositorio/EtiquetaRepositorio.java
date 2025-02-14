package com.hlc.etiquetas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlc.etiquetas.entidad.Etiqueta;


@Repository
public interface EtiquetaRepositorio extends JpaRepository<Etiqueta, Long> {

    /**
     * Busca etiquetas por nombre exacto (sensible a mayúsculas y minúsculas).
     *
     * @param nombre El nombre exacto de la etiqueta.
     * @return Una lista de etiquetas que coincidan exactamente con el nombre.
     */
    List<Etiqueta> findByNombre(String nombre);

    /**
     * Busca etiquetas cuyo nombre contenga una cadena específica, ignorando mayúsculas y minúsculas.
     *
     * @param nombre Subcadena a buscar en el nombre de las etiquetas.
     * @return Una lista de etiquetas que contienen la subcadena especificada.
     */
    List<Etiqueta> findByNombreContainingIgnoreCase(String nombre);

}
