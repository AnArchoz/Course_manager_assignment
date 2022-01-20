package se.lexicon.course_manager_assignment.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Course implements Serializable {
	private int id;
	private String courseName;
	private LocalDate startDate;
	private int weekDuration;
	private ArrayList<Student> students;

	public Course(int id, String courseName, LocalDate startDate, int weekDuration) {
		this.id = id;
		this.courseName = courseName;
		this.startDate = startDate;
		this.weekDuration = weekDuration;
		students = new ArrayList<>();
	}

	public Course() {

	}

	public void setWeekDuration(int weekDuration) {
		this.weekDuration = weekDuration;
	}

	public int getWeekDuration() {
		return weekDuration;
	}

	public int getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public boolean enrollStudents(Student student) {
		if (student == null || students.contains(student)) {
			return false;
		}

		return students.add(student);
	}

	public boolean unenrollStudents(Student student) {
		return students.remove(student);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
