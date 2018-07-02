package com.company.Project.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

  @GetMapping("/logout")
  public String logout(){
    SecurityContextHolder.getContext().setAuthentication(null);
    return "redirect:/";
  }
}
