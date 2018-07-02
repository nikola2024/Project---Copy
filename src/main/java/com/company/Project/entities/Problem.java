package com.company.Project.entities;

import com.company.Project.entities.base.ModelEntity;

import javax.persistence.*;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "problems")
public class Problem implements ModelEntity {
  private int id;
  private String title;
  private String statement;
  private int difficultyLevel;
  private String category;
  private Set<Solution> solutions;
  private User user;

  public Problem(){}

  public Problem(String title, String statement, int difficultyLevel, String category){
    this(-1, title, statement, difficultyLevel, category);
  }

  public Problem(int id, String title, String statement, int difficultyLevel, String category){
    setId(id);
    setTitle(title);
    setStatement(statement);
    setDifficultyLevel(difficultyLevel);
    setCategory(category);
    setSolutions(new HashSet<>());
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public int getId() {return id;}

  public void setId(int id) {this.id = id;}

  @Column(name = "title", length = 45, nullable = false)
  public String getTitle(){return title;}

  public void setTitle(String title) {this.title = title;}

  @Column(name = "Statement", length = 2500, nullable = false)
  public String getStatement(){return statement;}

  public void setStatement(String statement) {this.statement = statement;}

  @Column(name = "difficultyLevel", nullable = false)
  public int getDifficultyLevel(){return difficultyLevel;}

  public void setDifficultyLevel(int difficultyLevel) {this.difficultyLevel = difficultyLevel;}

  @Column(name="category", length = 45, nullable = false)
  public String getCategory(){return category;}

  public void setCategory(String category) {this.category = category;}

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER)
  public Set<Solution> getSolutions(){return solutions;}

  void setSolutions(Set<Solution> solutions){this.solutions = solutions;}

  @Override
  public String toString(){
    return MessageFormat.format(
        "({0}, {1}, {2}, {3}, {4})",
        getId(),
        getTitle(),
        getStatement(),
        getDifficultyLevel(),
        getCategory()
    );
  }

//  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//  @JoinTable(
//      name = "problems_categories",
//      joinColumns = {@JoinColumn(name = "problem_id")},
//      inverseJoinColumns = {@JoinColumn(name = "category_id")}
//  )
}
