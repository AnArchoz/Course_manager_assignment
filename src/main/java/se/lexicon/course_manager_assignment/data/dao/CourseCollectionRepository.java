package se.lexicon.course_manager_assignment.data.dao;


import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class    CourseCollectionRepository implements CourseDao {

	private Collection<Course> courses;

	public CourseCollectionRepository(Collection<Course> courses) {
		this.courses = courses;
	}

	@Override
	public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
		int courseId = CourseSequencer.nextCourseId();
		Course course = new Course(courseId, courseName, startDate, weekDuration);

		courses.add(course);
		return course;
	}

	@Override
	public Course findById(int id) {
		Course course = new Course();

		for (Course element : courses) {
			if (element.getId() == id)
				course = element;
		}

		return course;
	}

	@Override
	public Collection<Course> findByNameContains(String name) {
		Collection<Course> courseNames = new ArrayList<>();

		for (Course course : courses) {
			String courseName = course.getCourseName().toLowerCase();
			if (courseName.contains(name.toLowerCase()))
				courseNames.add(course);
		}

		return courseNames;
	}

	@Override
	public Collection<Course> findByDateBefore(LocalDate end) {
		Collection<Course> beforeDates = new ArrayList<>();

		for (Course course : courses) {
			if (course.getStartDate().isBefore(end)) {
				beforeDates.add(course);
			}
		}

		return beforeDates;
	}

	@Override
	public Collection<Course> findByDateAfter(LocalDate start) {
		Collection<Course> afterDates = new ArrayList<>();

		for (Course course : courses) {
			if (course.getStartDate().isAfter(start)) {
				afterDates.add(course);
			}
		}

		return afterDates;
	}

	@Override
	public Collection<Course> findAll() {
		return courses;
	}

	@Override
	public Collection<Course> findByStudentId(int studentId) {
		Collection<Course> studentNames = new ArrayList<>();

		for (Course course : courses) {
			ArrayList<Student> students = course.getStudents();
			for (Student student : students) {
				if (student.getId() == studentId) {
					studentNames.add(course);
				}
			}
		}

		return studentNames;
	}

	@Override
	public boolean removeCourse(Course course) {
		return courses.remove(course);
	}

	@Override
	public void clear() {
		this.courses = new HashSet<>();
	}
}
