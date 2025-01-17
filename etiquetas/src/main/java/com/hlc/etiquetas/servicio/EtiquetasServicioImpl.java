package com.hlc.etiquetas.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hlc.etiquetas.entidad.Etiqueta;
import com.hlc.etiquetas.repositorio.EtiquetaRepositorio;

@Service
public class EtiquetasServicioImpl implements EtiquetasServicio {

    private final EtiquetaRepositorio etiquetaRepositorio;

    public EtiquetasServicioImpl(EtiquetaRepositorio etiquetaRepositorio) {
        this.etiquetaRepositorio = etiquetaRepositorio;
    }

    @Override
    public Etiqueta guardarEtiqueta(Etiqueta etiqueta) {
        if (etiqueta.getId() != null && etiquetaRepositorio.existsById(etiqueta.getId())) {
            // Lógica para actualizar
            Etiqueta etiquetaExistente = etiquetaRepositorio.findById(etiqueta.getId())
                    .orElseThrow(() -> new RuntimeException("Etiqueta no encontrada con ID: " + etiqueta.getId()));

            etiquetaExistente.setNombre(etiqueta.getNombre());
            etiquetaExistente.setDescripcion(etiqueta.getDescripcion());
            etiquetaExistente.setActivo(etiqueta.getActivo());
            etiquetaExistente.setPrioridad(etiqueta.getPrioridad());
            return etiquetaRepositorio.save(etiquetaExistente);
        } else {
            // Crear una nueva etiqueta
            return etiquetaRepositorio.save(etiqueta);
        }
    }

    @Override
    public Etiqueta obtenerEtiquetaPorId(Long id) {
        // Busca una etiqueta por su ID y lanza una excepción si no se encuentra
        return etiquetaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Etiqueta no encontrada con ID: " + id));
    }

    @Override
    public List<Etiqueta> obtenerTodasLasEtiquetas() {
        // Recupera todas las etiquetas de la base de datos
        return etiquetaRepositorio.findAll();
    }

    @Override
    public void eliminarEtiqueta(Long id) {
        // Verifica si el ID existe en lugar de cargar toda la entidad
        if (!etiquetaRepositorio.existsById(id)) {
            throw new RuntimeException("Etiqueta no encontrada con ID: " + id);
        }
        etiquetaRepositorio.deleteById(id);
    }




}