package todopal.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class TaskContainer{
   private Date completionDate;

public void setCompletionDate(Date value) {
    this.completionDate = value;
}
public Date getCompletionDate() {
    return this.completionDate;
}
private TaskStatus status;

public void setStatus(TaskStatus value) {
    this.status = value;
}
public TaskStatus getStatus() {
    return this.status;
}
private Task task;

@OneToOne(optional=false)
public Task getTask() {
   return this.task;
}

public void setTask(Task task) {
   this.task = task;
}

private long taskContainerId;

public void setTaskContainerId(long value) {
    this.taskContainerId = value;
}
@Id
public long getTaskContainerId() {
    return this.taskContainerId;
}
}
