package todopal.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.TeacherRepository;
import todopal.model.Classroom;
import todopal.model.Teacher;

@Service
public class TeacherService {
	
	@Autowired
	TeacherRepository teacherRepository;


	@Transactional
	public Teacher getTeacher(String email) {
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Teacher email cannot be empty!");
		}
		Teacher teacher = (Teacher) teacherRepository.findTeacherByEmail(email);

		return teacher;
	}
	
	@Transactional
	public List<Teacher> getAllTeachers() {
		return toList(teacherRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}


	public Teacher createTeacher(String APPROVAL_CODE, String TEACHER_NAME, String TEACHER_EMAIL,
			String TEACHER_PASSWORD, String TEACHER_BIO) {
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		teacher.setApprovalCode(APPROVAL_CODE);
		teacher.setName(TEACHER_NAME);
		teacher.setEmail(TEACHER_EMAIL);
		teacher.setPassword(TEACHER_PASSWORD);
		teacher.setBio(TEACHER_BIO);
		
		
		teacherRepository.save(teacher);
		return teacher;
	}
	
	public Teacher updateTeacher(String aPPROVAL_CODE, String tEACHER_NAME, String tEACHER_EMAIL,
			String tEACHER_PASSWORD, String tEACHER_BIO) {
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		teacher.setApprovalCode(aPPROVAL_CODE);
		teacher.setName(tEACHER_NAME);
		teacher.setEmail(tEACHER_EMAIL);
		teacher.setPassword(tEACHER_PASSWORD);
		teacher.setBio(tEACHER_BIO);
		
		
		teacherRepository.save(teacher);
		return teacher;
	}

	public boolean deleteTeacher(String tEACHER_EMAIL) {
		// TODO Auto-generated method stub
		Teacher teacher = teacherRepository.findTeacherByEmail(tEACHER_EMAIL);

		if (teacher != null) {
			teacherRepository.delete(teacher);
			return true;
		}
		return false;
	}


}

