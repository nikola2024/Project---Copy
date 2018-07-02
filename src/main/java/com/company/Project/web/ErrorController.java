package com.company.Project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {
  @Autowired
  public ErrorController(){}

  @GetMapping("/error/{code}")
  public String errorPage(@PathVariable int code, Model model){
    int problemId=0;
    if(code>1000){
      int digits = 1 + (int) Math.log10(code);
      problemId = (int) (code-801*Math.pow(10,digits-3));
      code = 801;
    }
    switch (code){
      case 801:
        model.addAttribute("message1", 801);
        model.addAttribute("message2", "Invalid solution parameters");
        model.addAttribute("message3", "/problems/" + Integer.toString(problemId));
        break;
      case 802:
        model.addAttribute("message1", 802);
        model.addAttribute("message2", "Invalid problem parameters");
        model.addAttribute("message3", "/problems/share");
        break;
      case 803:
        model.addAttribute("message1", 803);
        model.addAttribute("message2", "No such user found");
        model.addAttribute("message3", "/users/browse");
        break;
      case 804:
        model.addAttribute("message1", 804);
        model.addAttribute("message2", "Invalid registration parameters (you have either entered an already taken username or a too short username or password)");
        model.addAttribute("message3", "/auth/register");
    }
    return "error/errorPage";
  }
}
