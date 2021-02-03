package todopal.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import java.util.List;
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
private List<String> taskCategories;

private void setTaskCategories(List<String> value) {
    this.taskCategories = value;
}
@ElementCollection
private List<String> getTaskCategories() {
    return this.taskCategories;
}
private List<String> taskTags;

private void setTaskTags(List<String> value) {
    this.taskTags = value;
}
   @ElementCollection
private List<String> getTaskTags() {
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
