package todopal.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import todopal.dto.PersonDto;
import todopal.dto.TeacherDto;
import todopal.model.Classroom;
import todopal.model.Person;
import todopal.model.Teacher;

import todopal.service.TeacherService;

@RestController
public class TodopalRestController {
	
	@Autowired
	private TeacherService teacherservice;
	
	@PostMapping(value = { "/teachers/{name}", "//teachers/{name}//" })
	public TeacherDto createTeacher(@PathVariable("approvalCode") String appCode,@PathVariable("name") String name,@PathVariable("email") String email,@PathVariable("password") String password,
			@PathVariable("bio") String bio,@PathVariable("classrooms") HashSet<Classroom>  classrooms) throws IllegalArgumentException {
		
		Teacher teacher = teacherservice.createTeacher(appCode,name,email,password,bio,classrooms);
		return convertToDto(teacher);
	}
	
	@GetMapping(value = { "/teachers/{name}", "/teachers/{name}/" })
	public TeacherDto getTeacherByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(teacherservice.getTeacher(name));
	}
	@GetMapping(value = { "/persons", "/persons/" })
	public List<PersonDto> getAllPersons() {
		List<PersonDto> persons = new ArrayList<>();
		for (Person person : teacherservice.getAllTeachers()) {
			persons.add(convertToDto(person));
		}
		return persons;
	}
	
	private PersonDto convertToDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getName());
		return personDto;
	}
	
	private TeacherDto convertToDto(Teacher t) {
		if (t == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		TeacherDto teacherDto = new TeacherDto(t.getName());
		return teacherDto;
	}
	
	

}
