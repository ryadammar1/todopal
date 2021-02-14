package todopal.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Teacher extends Person {
   private String approvalCode;
   private Set<Classroom> classroom;

   public void setApprovalCode(String value) {
      this.approvalCode = value;
   }

   public String getApprovalCode() {
      return this.approvalCode;
   }

   @OneToMany(fetch = FetchType.EAGER)
   public Set<Classroom> getClassroom() {
      return this.classroom;
   }

   public void setClassroom(Set<Classroom> classrooms) {
      this.classroom = classrooms;
   }

}
