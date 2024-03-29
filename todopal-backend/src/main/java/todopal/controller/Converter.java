package todopal.controller;

import java.util.stream.Collectors;

import todopal.dto.ClassroomDto;
import todopal.dto.StudentDto;
import todopal.dto.TaskContainerDto;
import todopal.dto.TaskDto;
import todopal.dto.TeacherDto;
import todopal.model.Classroom;
import todopal.model.Student;
import todopal.model.Task;
import todopal.model.TaskContainer;
import todopal.model.Teacher;

public class Converter {
    protected static TeacherDto convertToDto(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("There is no such Teacher!");
        }

        return new TeacherDto(teacher.getName(), teacher.getEmail(), teacher.getPassword(), teacher.getBio(),
                teacher.getApprovalCode());
    }

    protected static TeacherDto convertToDtoWithPersonalInfo(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("There is no such Teacher!");
        }

        TeacherDto dto = new TeacherDto(teacher.getName(), teacher.getEmail(), teacher.getPassword(), teacher.getBio());
        dto.setClassroom(
                teacher.getClassroom().stream().map((c) -> new ClassroomDto(c.getName())).collect(Collectors.toSet()));

        return dto;
    }

    protected static TaskDto convertToDto(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("There is no such Task!");
        }

        return new TaskDto(task.getTaskId(), task.getName(), task.getDescription(), task.getTag(), task.getCategory(),
                task.isIsMandatory(), task.getPointCount(), task.getStartDate(), task.getDueDate());
    }

    protected static TaskContainerDto convertToDto(TaskContainer taskContainer) {
        if (taskContainer == null) {
            throw new IllegalArgumentException("There is no such Task Container!");
        }

        TaskDto taskDto = convertToDto(taskContainer.getTask());
        return new TaskContainerDto(taskContainer.getTaskContainerId(), taskContainer.getCompletionDate(),
                taskContainer.getStatus(), taskDto, taskContainer.getFeedback());
    }

    protected static ClassroomDto convertToDto(Classroom classroom) {
        if (classroom == null) {
            throw new IllegalArgumentException("There is no such Classroom!");
        }
        TeacherDto teacherDto = convertToDto(classroom.getTeacher());

        return new ClassroomDto(classroom.getClassroomId(), classroom.getName(), classroom.getSubject(), teacherDto,
                classroom.getImagePath());
    }

    protected static StudentDto convertToDto(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("There is no such Teacher!");
        }

        return new StudentDto(student.getName(), student.getEmail(), student.getPassword());
    }

    protected static StudentDto convertToStudentDtoWithPersonalInfo(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("There is no such Student!");
        }

        return new StudentDto(student.getName(), student.getEmail(), student.getPassword(), student.getBio(),
                student.getTotalPoints());
    }
}
