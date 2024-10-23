package com.clone.crunchroll.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.clone.crunchroll.model.cliente.Cliente;
import com.clone.crunchroll.model.cliente.ClienteUserDetails;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        // Obter a autenticação atual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Verificar se o usuário está autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            // Verificar se o principal é uma instância de ClienteUserDetails
            if (principal instanceof ClienteUserDetails) {
                ClienteUserDetails clienteUserDetails = (ClienteUserDetails) principal;
                Cliente cliente = clienteUserDetails.getCliente();

                // Adicionar o nome completo ao modelo
                model.addAttribute("nomeUsuario", cliente.getName());
            }
        }

        return "home"; // Renderiza a página home.html
    }
}