package com.packt.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String welcome(Model model) {
    model.addAttribute("greeting", "Welcome to our Company!");
    model.addAttribute("tagline", "The Best Company!");

    return "welcome";
  }
}
