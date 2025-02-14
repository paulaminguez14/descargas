package com.hlc.cliente_uno_a_muchos_pedido.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Categoria;
import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.CategoriaServicio;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.ProductoServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

	private static final String VISTA_FORMULARIO = "categorias/formulario";
    private static final String REDIRECT_LISTADO = "redirect:/categorias";
    private static final String VISTA_LISTA = "categorias/lista";
    private static final String VISTA_DETALLE = "categorias/detalle";
    private static final String VISTA_PRODUCTOS_CATEGORIA = "productos/lista";
    
    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaServicio.obtenerTodasLasCategorias();
        model.addAttribute("categorias", categorias);
        return VISTA_LISTA;
    }

    @GetMapping("/{id}")
    public String mostrarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);
        model.addAttribute("categoria", categoria);
        return VISTA_DETALLE;
    }

    @GetMapping("/{id}/pedidos")
    public String mostrarProductosPorCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);
        List<Producto> productos = productoServicio.obtenerProductosPorCategoria(categoria);
        model.addAttribute("categoria", categoria);
        model.addAttribute("productos", productos);
        return VISTA_PRODUCTOS_CATEGORIA;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return VISTA_FORMULARIO;
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoria", categoria);
            return VISTA_FORMULARIO;
        }
        categoriaServicio.guardarCategoria(categoria);
        return REDIRECT_LISTADO;
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaServicio.obtenerCategoriaPorId(id);
        model.addAttribute("categoria", categoria);
        return VISTA_FORMULARIO;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
    	categoriaServicio.eliminarCategoria(id);
        return REDIRECT_LISTADO;
    }
}
