package todopal.model;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class TaskContainer{
   private TaskStatus status;

private void setStatus(TaskStatus value) {
    this.status = value;
}
private TaskStatus getStatus() {
    return this.status;
}
private Date dateCompleted;

private void setDateCompleted(Date value) {
    this.dateCompleted = value;
}
private Date getDateCompleted() {
    return this.dateCompleted;
}
private Student student;

@ManyToOne(optional=false)
public Student getStudent() {
   return this.student;
}

public void setStudent(Student student) {
   this.student = student;
}

private Student student1;

@ManyToOne(optional=false)
public Student getStudent1() {
   return this.student1;
}

public void setStudent1(Student student1) {
   this.student1 = student1;
}

private int id;

private void setId(int value) {
    this.id = value;
}
@Id
private int getId() {
    return this.id;
}
   private Task task;
   
   @ManyToOne(optional=false)
   public Task getTask() {
      return this.task;
   }
   
   public void setTask(Task task) {
      this.task = task;
   }
   
   }
