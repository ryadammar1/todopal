package todopal.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Classroom {
    private long classroomId;
    private String name;
    private String description;
    private String imagePath;
    private ArrayList<String> taskCategories;
    private ArrayList<String> taskTags;
    private String subject;
    private Teacher teacher;
    private Set<Student> student;
    private Set<Task> task;

    public void setClassroomId(long value) {
        this.classroomId = value;
    }

    @Id
    public long getClassroomId() {
        return this.classroomId;
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

    public void setImagePath(String value) {
        this.imagePath = value;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setTaskCategories(ArrayList<String> value) {
        this.taskCategories = value;
    }

    public ArrayList<String> getTaskCategories() {
        return this.taskCategories;
    }

    public void setTaskTags(ArrayList<String> value) {
        this.taskTags = value;
    }

    public ArrayList<String> getTaskTags() {
        return this.taskTags;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

    public String getSubject() {
        return this.subject;
    }

    @ManyToOne(optional = true)
    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "classroom")
    public Set<Student> getStudent() {
        return this.student;
    }

    public void setStudent(Set<Student> students) {
        this.student = students;
    }

    @OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER)
    public Set<Task> getTask() {
        return this.task;
    }

    public void setTask(Set<Task> tasks) {
        this.task = tasks;
    }
}
