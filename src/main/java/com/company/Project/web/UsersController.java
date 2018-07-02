package com.company.Project.web;

import com.company.Project.entities.Problem;
import com.company.Project.entities.Solution;
import com.company.Project.entities.User;
import com.company.Project.services.base.ProblemsService;
import com.company.Project.services.base.SolutionsService;
import com.company.Project.services.base.UsersService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import java.io.InvalidObjectException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
  private final UsersService usersService;
  private final ProblemsService problemsService;
  private final SolutionsService solutionsService;

  @Autowired
  public UsersController(UsersService usersService, ProblemsService problemsService, SolutionsService solutionsService) {
    this.usersService = usersService;
    this.problemsService = problemsService;
    this.solutionsService = solutionsService;
  }

  @GetMapping("/auth/register")
  public String register(Model model){
    model.addAttribute("user", new User());
    return "/auth/register";
  }

  @PostMapping("/auth/register")
  public String register(@ModelAttribute User user){
    List<User> allUsers = usersService.getAllUsers();
    for(int i=0;i<allUsers.size();i++){
      if(user.getUsername().equals(allUsers.get(i).getUsername())){
        return "redirect:/error/804";
      }
    }
    try {
      usersService.create(user);
      return "redirect:/login";
    } catch (InvalidObjectException e) {
      return "redirect:/error/804";
    }
  }

  @GetMapping("/users/browse")
  public String browse(Model model){
    model.addAttribute("user", new User());
    List<User> users = usersService.getAllUsers();
    List<List<User>> usrs= new ArrayList<>();
    List<User> finalThree = new ArrayList<>();
    int i=0;
    if(users.size()>3) {
      for (i = 0; i < users.size() - 3; i = i + 3) {
        usrs.add(users.subList(i, i + 3));
      }
    }
    while(i<users.size()){
      finalThree.add(users.get(i));
      i++;
    }
    model.addAttribute("users", usrs);
    model.addAttribute("finalThree", finalThree);
    return "users/browse";
  }

  @PostMapping("/users/browse")
  public String browse(@ModelAttribute User user, Model model){
    List<String> usernames = new ArrayList<>();
    List<User> users = usersService.getAllUsers();
    List<User> foundUsers = new ArrayList<>();
    for(int i=0;i<users.size();i++){
      if(users.get(i).getUsername().toLowerCase().contains(user.getUsername().toLowerCase()))foundUsers.add(users.get(i));
      usernames.add(users.get(i).getUsername());
    }
    if(usernames.contains(user.getUsername())){
      String html = "redirect:/users/" + user.getUsername();
      return html;
    }else if(!foundUsers.isEmpty()){
      List<List<User>> usrs= new ArrayList<>();
      List<User> finalThree = new ArrayList<>();
      int i=0;
      if(foundUsers.size()>3) {
        for (i = 0; i < foundUsers.size() - 3; i = i + 3) {
          usrs.add(foundUsers.subList(i, i + 3));
        }
      }
      while(i<foundUsers.size()){
        finalThree.add(foundUsers.get(i));
        i++;
      }
      model.addAttribute("users", usrs);
      model.addAttribute("user", new User());
      model.addAttribute("finalThree", finalThree);
      return "users/browse";
    }
    else {
      return "redirect:/error/803";
    }
  }

  @GetMapping("/users/{username}")
  public String details(@PathVariable String username, Model model, Principal principal){
    User user = usersService.getUserByUsername(username);
    if(user.getUsername().equals(principal.getName())){
      return "redirect:/profile";
    } else{
      model.addAttribute("message", user.getUsername() + "'s profile");
      model.addAttribute("problemMessage", user.getUsername() + "'s problems:");
      model.addAttribute("solutionMessage", "Problems to which " + user.getUsername() + " has written solutions:");
    }
    List<Problem> problemsByUser = problemsService.getProblemsByUser(user);
    List<Problem> problemsWithSolutionByUser = new ArrayList<Problem>();
    List<Solution> tempSolutions = solutionsService.getSolutionsByUser(user);
    for(int i =0;i<tempSolutions.size(); i++){
      if(!problemsWithSolutionByUser.contains(tempSolutions.get(i).getProblem()))problemsWithSolutionByUser.add(tempSolutions.get(i).getProblem());
    }
    model.addAttribute("user", user);
    model.addAttribute("problems", problemsByUser);
    model.addAttribute("problemsWithSolByUser", problemsWithSolutionByUser);
    return "users/details";
  }
  @GetMapping("/profile")
  public String details(Model model, Principal principal){
    User user = usersService.getUserByUsername(principal.getName());
    List<Problem> problemsByUser = problemsService.getProblemsByUser(user);
    List<Problem> problemsWithSolutionByUser = new ArrayList<Problem>();
    List<Solution> tempSolutions = solutionsService.getSolutionsByUser(user);
    for(int i =0;i<tempSolutions.size(); i++){
      if(!problemsWithSolutionByUser.contains(tempSolutions.get(i).getProblem()))problemsWithSolutionByUser.add(tempSolutions.get(i).getProblem());
    }
    model.addAttribute("user", user);
    model.addAttribute("problems", problemsByUser);
    model.addAttribute("problemsWithSolByUser", problemsWithSolutionByUser);
    model.addAttribute("message", "Your profile");
    model.addAttribute("problemMessage", "Your problems:");
    model.addAttribute("solutionMessage", "Problems to which you have written solutions:");

    return "users/details";
  }
}
