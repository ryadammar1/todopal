package todopal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todopal.dao.TeacherRepository;
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
		return teacherRepository.findTeacherByEmail(email);
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

	public Teacher createTeacher(String approvalCode, String teacherName, String teacherEmail, String teacherPassword,
			String teacherBio) {
		Teacher teacher = new Teacher();

		teacher.setApprovalCode(approvalCode);
		teacher.setName(teacherName);
		teacher.setEmail(teacherEmail);
		teacher.setPassword(teacherPassword);
		teacher.setBio(teacherBio);

		teacherRepository.save(teacher);
		return teacher;
	}

	public Teacher updateTeacher(String approvalCode, String teacherName, String teacherEmail, String teacherPassword,
			String teacherBio) {
		return createTeacher(approvalCode, teacherName, teacherEmail, teacherPassword, teacherBio);
	}

	public boolean deleteTeacher(String teacherEmail) {
		Teacher teacher = teacherRepository.findTeacherByEmail(teacherEmail);

		if (teacher != null) {
			teacherRepository.delete(teacher);
			return true;
		}
		return false;
	}
}
