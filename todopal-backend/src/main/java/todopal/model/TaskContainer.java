package todopal.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TaskContainer {
    private LocalDate completionDate;
    private TaskStatus status;
    private Task task;
    private String feedback;
    private long taskContainerId;

    public void setCompletionDate(LocalDate value) {
        this.completionDate = value;
    }
    
    public void setFeedback(String value) {
        this.feedback = value;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public LocalDate getCompletionDate() {
        return this.completionDate;
    }

    public void setStatus(TaskStatus value) {
        this.status = value;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    @OneToOne(optional = false)
    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTaskContainerId(long value) {
        this.taskContainerId = value;
    }

    @Id
    public long getTaskContainerId() {
        return this.taskContainerId;
    }
}
