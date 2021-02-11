package todopal.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student extends Person{
   private int totalPoints;

public void setTotalPoints(int value) {
    this.totalPoints = value;
}
public int getTotalPoints() {
    return this.totalPoints;
}
private ArrayList taskCategories;

public void setTaskCategories(ArrayList value) {
    this.taskCategories = value;
}
public ArrayList getTaskCategories() {
    return this.taskCategories;
}
private ArrayList taskTags;

public void setTaskTags(ArrayList value) {
    this.taskTags = value;
}
public ArrayList getTaskTags() {
    return this.taskTags;
}
   private Set<TaskContainer> schoolTask;
   
   @OneToMany
   public Set<TaskContainer> getSchoolTask() {
      return this.schoolTask;
   }
   
   public void setSchoolTask(Set<TaskContainer> schoolTasks) {
      this.schoolTask = schoolTasks;
   }
   
   private Set<TaskContainer> personalTask;
   
   @OneToMany
   public Set<TaskContainer> getPersonalTask() {
      return this.personalTask;
   }
   
   public void setPersonalTask(Set<TaskContainer> personalTasks) {
      this.personalTask = personalTasks;
   }
   
   private Classroom classroom;
   
   @ManyToOne(optional = true)
   public Classroom getClassroom() {
      return this.classroom;
   }
   
   public void setClassroom(Classroom classrooms) {
      this.classroom = classrooms;
   }
   
   }
