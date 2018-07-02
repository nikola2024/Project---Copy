package com.company.Project.entities;

import com.company.Project.entities.base.ModelEntity;
import sun.plugin2.message.Message;

import javax.persistence.*;
import java.text.MessageFormat;

@Entity
@Table(name = "solutions")
public class Solution implements ModelEntity{
  private int id;
  private String text;
  private User user;
  private Problem problem;

  public Solution(){}

  public Solution(String text){this(-1, text);}

  public Solution(int id, String text){
    setId(id);
    setText(text);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public int getId() {return id;}

  public void setId(int id) {this.id = id;}

  @Column(name = "text", length = 2500, nullable = false)
  public String getText(){return text;}

  public void setText(String text) {this.text = text;}

  @ManyToOne
  @JoinColumn(name = "problem_id", nullable = false)
  public Problem getProblem(){return problem;}

  public void setProblem(Problem problem){this.problem = problem;}

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  public User getUser(){return user;}

  public void setUser(User user){this.user = user;}

  @Override
  public String toString(){
    return MessageFormat.format(
        "({0}, {1})",
        getId(),
        getText()
    );
  }
}
