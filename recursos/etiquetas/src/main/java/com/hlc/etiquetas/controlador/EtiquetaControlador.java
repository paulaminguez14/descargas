package com.hlc.etiquetas.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hlc.etiquetas.entidad.Etiqueta;
import com.hlc.etiquetas.servicio.EtiquetasServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/etiquetas")
public class EtiquetaControlador {

    private final EtiquetasServicio etiquetaServicio;

    /*
     * VISTAS
     */
    private final String VISTA_LISTA = "etiquetas/lista";
    private final String VISTA_FORMULARIO = "etiquetas/formulario";
    private final String REDIRECT_ETIQUETA = "redirect:/etiquetas";

    public EtiquetaControlador(EtiquetasServicio etiquetaServicio) {
        this.etiquetaServicio = etiquetaServicio;
    }

 /* Muestra la lista de etiquetas con un filtro opcional.
         *
         * @param query El término de búsqueda opcional para filtrar etiquetas.
         * @param model Modelo para pasar datos a la vista.
         * @return Nombre de la plantilla Thymeleaf.
         */
        @GetMapping
        public String listarEtiquetas(@RequestParam(name = "nombre", required = false) String nombre, Model model) {
            List<Etiqueta> etiquetas;

            if (nombre != null && !nombre.trim().isEmpty()) {
                etiquetas = etiquetaServicio.buscarEtiquetasPorNombre(nombre);
            } else {
                etiquetas = etiquetaServicio.obtenerTodasLasEtiquetas();
            }

            model.addAttribute("etiquetas", etiquetas);
            return VISTA_LISTA;
      }

    

    // Mostrar formulario para agregar una nueva etiqueta
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaEtiqueta(Model model) {
        model.addAttribute("etiqueta", new Etiqueta());
        return VISTA_FORMULARIO;
    }

    // Guardar una nueva etiqueta
    @PostMapping 
    public String guardarEtiqueta(@Valid @ModelAttribute("etiqueta") Etiqueta etiqueta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VISTA_FORMULARIO; // Volver al formulario si hay errores
        }
        etiquetaServicio.guardarEtiqueta(etiqueta);
        return REDIRECT_ETIQUETA;
    }

    // Mostrar formulario para editar una etiqueta existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEtiqueta(@PathVariable Long id, Model model) {
        Etiqueta etiqueta = etiquetaServicio.obtenerEtiquetaPorId(id);
        model.addAttribute("etiqueta", etiqueta);
        return VISTA_FORMULARIO;
    }

    // Actualizar una etiqueta existente
    @PostMapping("/{id}")
    public String actualizarEtiqueta(@PathVariable Long id, @Valid @ModelAttribute("etiqueta") Etiqueta etiqueta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VISTA_FORMULARIO; // Volver al formulario si hay errores
        }
        etiqueta.setId(id); // Asegurar que el ID sea correcto
        etiquetaServicio.guardarEtiqueta(etiqueta); // Guardar o actualizar sin duplicar
        return REDIRECT_ETIQUETA;
    }
    


    // Eliminar una etiqueta
    @GetMapping("/eliminar/{id}")
    public String eliminarEtiqueta(@PathVariable Long id) {
        etiquetaServicio.eliminarEtiqueta(id);
        return REDIRECT_ETIQUETA;
    }
}