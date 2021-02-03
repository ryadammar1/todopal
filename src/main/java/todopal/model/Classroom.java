package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import Array.*;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Classroom{
   private String classId;

private void setClassId(String value) {
    this.classId = value;
}
@Id
private String getClassId() {
    return this.classId;
}
private String name;

private void setName(String value) {
    this.name = value;
}
private String getName() {
    return this.name;
}
private String descrription;

private void setDescrription(String value) {
    this.descrription = value;
}
private String getDescrription() {
    return this.descrription;
}
private String image;

private void setImage(String value) {
    this.image = value;
}
private String getImage() {
    return this.image;
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
private String subject;

private void setSubject(String value) {
    this.subject = value;
}
private String getSubject() {
    return this.subject;
}
   private Set<Teacher> teachers;
   
   @ManyToMany
   public Set<Teacher> getTeachers() {
      return this.teachers;
   }
   
   public void setTeachers(Set<Teacher> teacherss) {
      this.teachers = teacherss;
   }
   
   private Set<Student> students;
   
   @OneToMany(mappedBy="classroom" )
   public Set<Student> getStudents() {
      return this.students;
   }
   
   public void setStudents(Set<Student> studentss) {
      this.students = studentss;
   }
   
   private Set<Task> task;
   
   @OneToMany(mappedBy="classroom" )
   public Set<Task> getTask() {
      return this.task;
   }
   
   public void setTask(Set<Task> tasks) {
      this.task = tasks;
   }
   
   }
