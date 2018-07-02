package com.company.Project.web;


import com.company.Project.entities.Problem;
import com.company.Project.services.base.ProblemsService;
import com.company.Project.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

  private final ProblemsService problemsService;
  private final UsersService usersService;

  @Autowired
  public HomeController(ProblemsService problemsService, UsersService usersService) {
    this.problemsService = problemsService;
    this.usersService = usersService;
  }

  @GetMapping("/")
  public String index(Model model, Principal principal){
    List<Problem> problems = problemsService.getAllProblems();
    Collections.reverse(problems);
    model.addAttribute("message", "Welcome!");
    model.addAttribute("problems", problems);
    return "index";
  }
}
