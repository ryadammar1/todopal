package todopal.model;

import javax.persistence.Entity;
import java.time.LocalDate;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task{
   private boolean isMandatory;

public void setIsMandatory(boolean value) {
    this.isMandatory = value;
}
public boolean isIsMandatory() {
    return this.isMandatory;
}
private String tag;

public void setTag(String value) {
    this.tag = value;
}
public String getTag() {
    return this.tag;
}
private String category;

public void setCategory(String value) {
    this.category = value;
}
public String getCategory() {
    return this.category;
}
private int pointCount;

public void setPointCount(int value) {
    this.pointCount = value;
}
public int getPointCount() {
    return this.pointCount;
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
private LocalDate startDate;

public void setStartDate(LocalDate value) {
    this.startDate = value;
}
public LocalDate getStartDate() {
    return this.startDate;
}
private LocalDate dueDate;

public void setDueDate(LocalDate value) {
    this.dueDate = value;
}
public LocalDate getDueDate() {
    return this.dueDate;
}
private long taskId;

public void setTaskId(long value) {
    this.taskId = value;
}
@Id
public long getTaskId() {
    return this.taskId;
}
   private Classroom classroom;
   
   @ManyToOne
   public Classroom getClassroom() {
      return this.classroom;
   }
   
   public void setClassroom(Classroom classroom) {
      this.classroom = classroom;
   }
   
   }
