package com.company.Project.entities;

import com.company.Project.entities.base.ModelEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements ModelEntity {
  private int id;
  private String username;
  private String password;
  private Set<Problem> problems;
  private Set<Solution> solutions;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public int getId(){return id;}

  public void setId(int id){this.id = id;}

  @Column(name = "Username")
  public String getUsername(){return username;}

  public void setUsername(String username) {this.username = username;}

  @Column(name = "Password")
  public String getPassword(){ return password;}

  public void setPassword(String password){this.password = password;}

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  public Set<Problem> getProblems(){return problems;}

  void setProblems(Set<Problem> problems){this.problems = problems;}

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  public Set<Solution> getSolutions(){return solutions;}

  void setSolutions(Set<Solution> solutions){this.solutions = solutions;}
}
