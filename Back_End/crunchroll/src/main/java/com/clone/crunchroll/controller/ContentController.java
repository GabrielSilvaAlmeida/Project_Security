package com.clone.crunchroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
  public String handleLogin() {
    return "custom_login";
  }
}