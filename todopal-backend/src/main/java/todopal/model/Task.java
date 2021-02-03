package todopal.model;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Task{
   private boolean isMandatory;

private void setIsMandatory(boolean value) {
    this.isMandatory = value;
}
private boolean isIsMandatory() {
    return this.isMandatory;
}
private String tag;

private void setTag(String value) {
    this.tag = value;
}
private String getTag() {
    return this.tag;
}
private String category;

private void setCategory(String value) {
    this.category = value;
}
private String getCategory() {
    return this.category;
}
private int pointCount;

private void setPointCount(int value) {
    this.pointCount = value;
}
private int getPointCount() {
    return this.pointCount;
}
private String name;

private void setName(String value) {
    this.name = value;
}
private String getName() {
    return this.name;
}
private String description;

private void setDescription(String value) {
    this.description = value;
}
private String getDescription() {
    return this.description;
}
private Date startDate;

private void setStartDate(Date value) {
    this.startDate = value;
}
private Date getStartDate() {
    return this.startDate;
}
private Date dueDate;

private void setDueDate(Date value) {
    this.dueDate = value;
}
private Date getDueDate() {
    return this.dueDate;
}
private int id;

private void setId(int value) {
    this.id = value;
}
@Id
private int getId() {
    return this.id;
}
   private Set<TaskContainer> tracking;
   
   @OneToMany(mappedBy="task" )
   public Set<TaskContainer> getTracking() {
      return this.tracking;
   }
   
   public void setTracking(Set<TaskContainer> trackings) {
      this.tracking = trackings;
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
