package com.hlc.cliente_uno_a_muchos_pedido.controlador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {

    @GetMapping("/home")
    public String mostrarHome(Model model) {
        model.addAttribute("titulo", "Página de Inicio");
        return "public/home"; // Renderiza templates/public/home.html
    }
}