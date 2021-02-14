package todopal.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {
    private boolean isMandatory;
    private String tag;
    private String category;
    private int pointCount;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private long taskId;
    private Classroom classroom;

    public void setIsMandatory(boolean value) {
        this.isMandatory = value;
    }

    public boolean isIsMandatory() {
        return this.isMandatory;
    }

    public void setTag(String value) {
        this.tag = value;
    }

    public String getTag() {
        return this.tag;
    }

    public void setCategory(String value) {
        this.category = value;
    }

    public String getCategory() {
        return this.category;
    }

    public void setPointCount(int value) {
        this.pointCount = value;
    }

    public int getPointCount() {
        return this.pointCount;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStartDate(LocalDate value) {
        this.startDate = value;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setDueDate(LocalDate value) {
        this.dueDate = value;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setTaskId(long value) {
        this.taskId = value;
    }

    @Id
    public long getTaskId() {
        return this.taskId;
    }

    @ManyToOne
    public Classroom getClassroom() {
        return this.classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

}
