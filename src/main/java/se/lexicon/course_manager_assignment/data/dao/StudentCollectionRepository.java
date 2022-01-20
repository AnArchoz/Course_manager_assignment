package se.lexicon.course_manager_assignment.data.dao;


import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class StudentCollectionRepository implements StudentDao {

	private Collection<Student> students;

	public StudentCollectionRepository(Collection<Student> students) {
		this.students = students;
	}

	@Override
	public Student createStudent(String name, String email, String address) {
		int studentId = StudentSequencer.nextStudentId();
		Student student = new Student(studentId, name, email, address);
		students.add(student);
		return student;
	}

	@Override
	public Student findByEmailIgnoreCase(String email) {
		Student student = new Student();
		for (Student temp : students) {
			if (temp.getEmail().equalsIgnoreCase(email)) {
				student = temp;
			}
		}
		return student;
	}

	@Override
	public Collection<Student> findByNameContains(String name) {
		Student student = new Student();

		Collection<Student> st = new ArrayList<>();
		for (Student temp : students) {
			if (temp.getName().contains(name.toLowerCase())) {
				st.add(temp);
			}

		}
		return st;
	}

	@Override
	public Student findById(int id) {
		Student student = new Student();
		for (Student temp : students) {
			if (temp.getId() == id) {
				student = temp;
			}
		}
		return student;
	}

	@Override
	public Collection<Student> findAll() {
		return students;
	}

	@Override
	public boolean removeStudent(Student student) {
		return students.remove(student);
	}

	@Override
	public void clear() {
		this.students = new HashSet<>();
	}
}
