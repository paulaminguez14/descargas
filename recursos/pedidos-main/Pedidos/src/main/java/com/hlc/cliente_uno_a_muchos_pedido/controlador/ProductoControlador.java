package com.hlc.cliente_uno_a_muchos_pedido.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hlc.cliente_uno_a_muchos_pedido.entidad.Producto;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.CategoriaServicio;
import com.hlc.cliente_uno_a_muchos_pedido.servicio.ProductoServicio;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

    private static final String VISTA_FORMULARIO = "productos/formulario";
    private static final String REDIRECT_LISTADO = "redirect:/productos";
    private static final String VISTA_LISTA = "productos/lista";
    private static final String VISTA_DETALLE = "productos/detalle";
    private static final String VISTA_CATEGORIA = "categorias/detalle";

    @Autowired
    private ProductoServicio productoServicio;
    
    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoServicio.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return VISTA_LISTA;
    }

    @GetMapping("/detalle/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        model.addAttribute("categorias", categoriaServicio.obtenerTodasLasCategorias());
        if (producto == null) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto);
        return VISTA_DETALLE;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaServicio.obtenerTodasLasCategorias());
        return VISTA_FORMULARIO;
    }

    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute Producto producto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaServicio.obtenerTodasLasCategorias());
            return VISTA_FORMULARIO;
        }
        productoServicio.guardarProducto(producto);
        return REDIRECT_LISTADO;
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        if (producto == null) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaServicio.obtenerTodasLasCategorias());
        return VISTA_FORMULARIO;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoServicio.eliminarProducto(id);
        return REDIRECT_LISTADO;
    }
    
    @GetMapping("/listar/{id}")
    public String listarCategoria(@PathVariable Long id) {
        categoriaServicio.obtenerCategoriaPorId(id);
        return VISTA_CATEGORIA;
    }
}
