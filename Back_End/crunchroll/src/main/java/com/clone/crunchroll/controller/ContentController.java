package com.clone.crunchroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {






  @GetMapping("/admin/home")
  public String handleAdminHome() {
    return "home";
  }

  @GetMapping("/user/home")
  public String handleUserHome() {
    return "home";
  }



  @GetMapping("/login")
public String login(@RequestParam(value = "logout", required = false) String logout, Model model) {
    if (logout != null) {
        model.addAttribute("message", "Você foi desconectado com sucesso.");
    }
    return "custom_login"; // Retorna a view 'login.html'
}
}