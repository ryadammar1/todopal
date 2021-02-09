package todopal.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Classroom{
   private long classroomId;

public void setClassroomId(long value) {
    this.classroomId = value;
}
@Id
public long getClassroomId() {
    return this.classroomId;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
private String imagePath;

public void setImagePath(String value) {
    this.imagePath = value;
}
public String getImagePath() {
    return this.imagePath;
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
private String subject;

public void setSubject(String value) {
    this.subject = value;
}
public String getSubject() {
    return this.subject;
}
   private Teacher teacher;
   
   @ManyToOne
   public Teacher getTeacher() {
      return this.teacher;
   }
   
   public void setTeacher(Teacher teacher) {
      this.teacher = teacher;
   }
   
   private Set<Student> student;
   
   @ManyToMany(mappedBy="classroom" )
   public Set<Student> getStudent() {
      return this.student;
   }
   
   public void setStudent(Set<Student> students) {
      this.student = students;
   }
   
   private Set<Task> task;
   
   @OneToMany(mappedBy="classroom", fetch = FetchType.EAGER)
   public Set<Task> getTask() {
      return this.task;
   }
   
   public void setTask(Set<Task> tasks) {
      this.task = tasks;
   }
   
   }
