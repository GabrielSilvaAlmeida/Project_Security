package com.clone.crunchroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.clone.crunchroll.model.cliente.Cliente;
import com.clone.crunchroll.model.cliente.ClienteRepository;

@Controller
public class RegisterController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "register"; // O nome da página HTML (register.html)
    }

    


    @PostMapping("/register")
    public String createUser(@ModelAttribute("cliente") Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        cliente.setRole("USER");  // Define a role como "USER" por padrão
        repository.save(cliente);
        return "redirect:/login";
    }

}