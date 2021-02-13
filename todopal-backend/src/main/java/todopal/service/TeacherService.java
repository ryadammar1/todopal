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
	
//	@Transactional
//	public Teacher createTeacher(String name) {
//		if (name == null || name.trim().length() == 0) {
//			throw new IllegalArgumentException("Teacher name cannot be empty!");
//		} else if (teacherRepository.existsById(name)) {
//			throw new IllegalArgumentException("Teacher has already been created!");
//		}
//		Teacher teacher = new Teacher();
//		teacher.setName(name);
//		teacherRepository.save(teacher);
//		return teacher;
//	}


	@Transactional
	public Teacher getTeacher(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Teacher name cannot be empty!");
		}

		Teacher teacher = (Teacher) teacherRepository.findTeacherByemail(name);

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


	public Teacher createTeacher(String aPPROVAL_CODE, String tEACHER_NAME, String tEACHER_EMAIL,
			String tEACHER_PASSWORD, String tEACHER_BIO, HashSet<Classroom> cLASSROOMS) {
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		teacher.setApprovalCode(aPPROVAL_CODE);
		teacher.setName(tEACHER_NAME);
		teacher.setEmail(tEACHER_EMAIL);
		teacher.setPassword(tEACHER_PASSWORD);
		teacher.setBio(tEACHER_BIO);
		teacher.setClassroom(cLASSROOMS);
		
		teacherRepository.save(teacher);
		return teacher;
	}
	
	public Teacher updateTeacher(String aPPROVAL_CODE, String tEACHER_NAME, String tEACHER_EMAIL,
			String tEACHER_PASSWORD, String tEACHER_BIO, HashSet<Classroom> cLASSROOMS) {
		// TODO Auto-generated method stub
		Teacher teacher = new Teacher();
		teacher.setApprovalCode(aPPROVAL_CODE);
		teacher.setName(tEACHER_NAME);
		teacher.setEmail(tEACHER_EMAIL);
		teacher.setPassword(tEACHER_PASSWORD);
		teacher.setBio(tEACHER_BIO);
		teacher.setClassroom(cLASSROOMS);
		
		teacherRepository.save(teacher);
		return teacher;
	}

	public boolean deleteTeacher(String tEACHER_EMAIL) {
		// TODO Auto-generated method stub

		Teacher teacher = teacherRepository.findTeacherByemail(tEACHER_EMAIL);

		if (teacher != null) {
			teacherRepository.delete(teacher);
			return true;
		}
		return false;
	}


}

