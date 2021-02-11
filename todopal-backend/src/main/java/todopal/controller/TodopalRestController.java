package todopal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import todopal.dto.PersonDto;
import todopal.dto.TeacherDto;
import todopal.model.Person;
import todopal.model.Teacher;
import todopal.service.ClassroomService;

@RestController
public class TodopalRestController {
	
	@Autowired
	private ClassroomService service;
	
	@PostMapping(value = { "/persons/{name}", "/persons/{name}/" })
	public TeacherDto createPerson(@PathVariable("name") String name) throws IllegalArgumentException {
		
		Teacher teacher = service.createTeacher(name);
		return convertToDto(teacher);
	}
	@GetMapping(value = { "/persons/{name}", "/person/{name}/" })
	public TeacherDto getPersonByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(service.getTeacher(name));
	}
	
	@PostMapping(value = { "/teachers/{name}", "/teachers/{name}/" })
	public TeacherDto createTeacher(@PathVariable("name") String name) throws IllegalArgumentException {
		
		Teacher teacher = service.createTeacher(name);
		return convertToDto(teacher);
	}
	@GetMapping(value = { "/teachers/{name}", "/teachers/{name}/" })
	public TeacherDto getTeacherByName(@PathVariable("name") String name) throws IllegalArgumentException {
		return convertToDto(service.getTeacher(name));
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
