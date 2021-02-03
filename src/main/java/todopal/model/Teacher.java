package model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Teacher extends Person{
   private String approvalCode;

private void setApprovalCode(String value) {
    this.approvalCode = value;
}
private String getApprovalCode() {
    return this.approvalCode;
}
   private Set<Classroom> classrooms;
   
   @ManyToMany(mappedBy="teachers" )
   public Set<Classroom> getClassrooms() {
      return this.classrooms;
   }
   
   public void setClassrooms(Set<Classroom> classroomss) {
      this.classrooms = classroomss;
   }
   
   }
