package model;

import javax.persistence.Entity;
import Array.*;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student extends Person{
   private int totalPoints;

private void setTotalPoints(int value) {
    this.totalPoints = value;
}
private int getTotalPoints() {
    return this.totalPoints;
}
private Array taskCategories;

private void setTaskCategories(Array value) {
    this.taskCategories = value;
}
private Array getTaskCategories() {
    return this.taskCategories;
}
private Array taskTags;

private void setTaskTags(Array value) {
    this.taskTags = value;
}
private Array getTaskTags() {
    return this.taskTags;
}
   private Set<TaskContainer> school;
   
   @OneToMany(mappedBy="student" )
   public Set<TaskContainer> getSchool() {
      return this.school;
   }
   
   public void setSchool(Set<TaskContainer> schools) {
      this.school = schools;
   }
   
   private Set<TaskContainer> personal;
   
   @OneToMany(mappedBy="student1" )
   public Set<TaskContainer> getPersonal() {
      return this.personal;
   }
   
   public void setPersonal(Set<TaskContainer> personals) {
      this.personal = personals;
   }
   
   private Classroom classroom;
   
   @ManyToOne(optional=false)
   public Classroom getClassroom() {
      return this.classroom;
   }
   
   public void setClassroom(Classroom classroom) {
      this.classroom = classroom;
   }
   
   }
