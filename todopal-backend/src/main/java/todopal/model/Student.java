package todopal.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Student extends Person {
   private int totalPoints;
   private ArrayList<String> taskCategories;
   private ArrayList<String> taskTags;
   private Set<TaskContainer> schoolTask;
   private Set<TaskContainer> personalTask;
   private Classroom classroom;

   public void setTotalPoints(int value) {
      this.totalPoints = value;
   }

   public int getTotalPoints() {
      return this.totalPoints;
   }

   public void setTaskCategories(ArrayList<String> value) {
      this.taskCategories = value;
   }

   public ArrayList<String> getTaskCategories() {
      return this.taskCategories;
   }

   public void setTaskTags(ArrayList<String> value) {
      this.taskTags = value;
   }

   public ArrayList<String> getTaskTags() {
      return this.taskTags;
   }

   @OneToMany
   public Set<TaskContainer> getSchoolTask() {
      return this.schoolTask;
   }

   public void setSchoolTask(Set<TaskContainer> schoolTasks) {
      this.schoolTask = schoolTasks;
   }

   @OneToMany
   public Set<TaskContainer> getPersonalTask() {
      return this.personalTask;
   }

   public void setPersonalTask(Set<TaskContainer> personalTasks) {
      this.personalTask = personalTasks;
   }

   @ManyToOne(optional = true)
   public Classroom getClassroom() {
      return this.classroom;
   }

   public void setClassroom(Classroom classrooms) {
      this.classroom = classrooms;
   }
}
