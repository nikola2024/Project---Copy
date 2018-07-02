package com.company.Project.web;

import com.company.Project.entities.Problem;
import com.company.Project.entities.Solution;
import com.company.Project.entities.User;
import com.company.Project.services.base.ProblemsService;
import com.company.Project.services.base.SolutionsService;
import com.company.Project.services.base.UsersService;
import com.company.Project.utils.loggers.base.LoggerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/problems")
public class ProblemController {
  private final ProblemsService problemsService;
  private final UsersService usersService;
  private final SolutionsService solutionsService;
  private final LoggerProvider<ProblemController> loggerProvider;

  @Autowired
  public ProblemController(ProblemsService problemsService, UsersService usersService, SolutionsService solutionsService, LoggerProvider<ProblemController> loggerProvider) {
    this.problemsService = problemsService;
    this.usersService = usersService;
    this.solutionsService = solutionsService;
    this.loggerProvider = loggerProvider;
    this.loggerProvider.setClass(ProblemController.class);
  }

  @GetMapping("/{id}")
  public String details(@PathVariable String id, Model model){
    loggerProvider.info("In problem details");
    Problem problem = problemsService.getProblemById(Integer.parseInt(id));
    Solution solution = new Solution();
    List<Solution> solutions = solutionsService.getSolutionsByProblem(problem);
    model.addAttribute("problem", problem);
    model.addAttribute("solution", solution);
    model.addAttribute("solutions", solutions);
    return "problems/details";
  }

  @PostMapping("/{id}")
  public String write(@PathVariable String id,@ModelAttribute Solution solution, Principal principal, @ModelAttribute Problem problem){
    try{
      User user = usersService.getUserByUsername(principal.getName());
      solution.setUser(user);
      solution.setProblem(problem);
      solutionsService.createSolution(solution);
      String idTemp = Integer.toString(problem.getId());
      return "redirect:/problems/" + idTemp;
    } catch (InvalidObjectException e) {
      String link = "redirect:/error/801" + Integer.toString(problem.getId());
      return link;
    }
  }

  @GetMapping("/share")
  public String share(Model model) {
    model.addAttribute("problem", new Problem());
    return "problems/share";
  }

  @PostMapping("/share")
  public  String share(@ModelAttribute Problem problem, Principal principal){
    try{
      User user = usersService.getUserByUsername(principal.getName());
      problem.setUser(user);
      problemsService.createProblem(problem);
      return "redirect:/problems/browse";
    } catch (InvalidObjectException e){
      return "redirect:/error/802";
    }
  }
  @GetMapping("/browse")
  public String browse(Model model){
    model.addAttribute("message", "All problems");
    List<Problem> problems = problemsService.getAllProblems();
    Collections.reverse(problems);
    model.addAttribute("problems", problems);
    return "problems/browse";
  }

  @GetMapping("/browse/{category}")
  public String browseCategory(@PathVariable String category, Model model){
    String message = "All " + category + " problems";
    model.addAttribute("message", message);
    List<Problem> problems = problemsService.getProblemsByCategory(category);
    model.addAttribute("problems", problems);
    return "problems/browse";
  }
}
