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

	private final String EMPTY_LIST_STRING_EXCEPTION = "List name is not provided";

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

	@Transactional
	public Teacher createTeacher(String approvalCode, String teacherName, String teacherEmail, String teacherPassword,
			String teacherBio) {
		Teacher teacher = new Teacher();

		if (!teacherEmail.contains("@")) {
			throw new IllegalArgumentException("Invalid email is used");
		}

		if (teacherRepository.findTeacherByEmail(teacherEmail) != null) {
			throw new IllegalArgumentException("Already registered");
		}

		teacher.setApprovalCode(approvalCode);
		teacher.setName(teacherName);
		teacher.setEmail(teacherEmail);
		teacher.setPassword(teacherPassword);
		teacher.setBio(teacherBio);

		teacherRepository.save(teacher);
		return teacher;
	}

	@Transactional
	public Teacher updateTeacher(String teacherEmail, String newApprovalCode, String newTeacherName,
			String newTeacherPassword, String newTeacherBio) {
		Teacher teacher = teacherRepository.findTeacherByEmail(teacherEmail);
		if (teacher == null) {
			throw new IllegalArgumentException("Email cannot be changed!");
		}

		teacher.setApprovalCode(newApprovalCode);
		teacher.setName(newTeacherName);
		teacher.setPassword(newTeacherPassword);
		teacher.setBio(newTeacherBio);

		teacherRepository.save(teacher);
		return teacher;
	}

	@Transactional
	public boolean deleteTeacher(String teacherEmail) {
		Teacher teacher = teacherRepository.findTeacherByEmail(teacherEmail);

		if (teacher != null) {
			teacherRepository.delete(teacher);
			return true;
		}

		return false;
	}

	@Transactional
	public Teacher addToMandatoryLists(String teacherEmail, String mandatoryList)
		throws IllegalArgumentException {
			checkForEmptyString(mandatoryList);
			Teacher teacher = teacherRepository.findTeacherByEmail(teacherEmail);
			if (teacher.getMandatoryLists() == null) {
				ArrayList<String> mandatoryLists = new ArrayList<>();
				mandatoryLists.add(mandatoryList);
				teacher.setMandatoryLists(mandatoryLists);
			} else {
				if (teacher.getMandatoryLists().contains(mandatoryList)) {
					throw new IllegalArgumentException("Task list already exists");
				}
				teacher.getMandatoryLists().add(mandatoryList);
			}
			teacherRepository.save(teacher);
			return teacher;
	}

	@Transactional
	public Teacher addToOptionalLists(String teacherEmail, String optionalList)
			throws IllegalArgumentException {
				checkForEmptyString(optionalList);
				Teacher teacher = teacherRepository.findTeacherByEmail(teacherEmail);
				if (teacher.getOptionalLists() == null) {
					ArrayList<String> optionalLists = new ArrayList<>();
					optionalLists.add(optionalList);
					teacher.setOptionalLists(optionalLists);
				} else {
					if (teacher.getOptionalLists().contains(optionalList)) {
						throw new IllegalArgumentException("Task list already exists");
					}
					teacher.getOptionalLists().add(optionalList);
				}
				teacherRepository.save(teacher);
				return teacher;
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		iterable.forEach(resultList::add);
		return resultList;
	}

	private void checkForEmptyString(String parameterValue) {
		if (parameterValue.trim().length() == 0) {
			throw new IllegalArgumentException(EMPTY_LIST_STRING_EXCEPTION);
		}
	}
}
