package com.hlc.etiquetas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hlc.etiquetas.entidad.Etiqueta;

@Repository
public interface EtiquetaRepositorio extends JpaRepository<Etiqueta, Long>{
		List<Etiqueta> findByNombre(String Nombre);
}
