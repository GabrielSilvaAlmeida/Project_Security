package com.clone.crunchroll.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.clone.crunchroll.model.cliente.Cliente;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // Obter a autenticação atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Verificar se o usuário está autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            // O principal contém o nome do usuário ou um objeto UserDetails (Cliente)
            Object principal = authentication.getName();

            // Verificar se é uma instância do seu Cliente
            if (principal instanceof Cliente) {
                Cliente cliente = (Cliente) principal;
                model.addAttribute("nomeUsuario", cliente.getName());
            } else {
                // Caso seja um username (String), adicione o nome de outra forma
                model.addAttribute("nomeUsuario", principal.toString());
            }
        }

        return "home"; // Renderiza a página home.html
    }
}