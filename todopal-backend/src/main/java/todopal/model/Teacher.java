package todopal.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Teacher extends Person{
   private String approvalCode;

public void setApprovalCode(String value) {
    this.approvalCode = value;
}
public String getApprovalCode() {
    return this.approvalCode;
}
   private Set<Classroom> classroom;
   
   @OneToMany(fetch = FetchType.EAGER)
   public Set<Classroom> getClassroom() {
      return this.classroom;
   }
   
   public void setClassroom(Set<Classroom> classrooms) {
      this.classroom = classrooms;
   }
   
   }
