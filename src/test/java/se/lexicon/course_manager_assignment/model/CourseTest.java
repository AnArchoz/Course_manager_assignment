package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class CourseTest {

	private Course testCourse;

	@BeforeEach
	public void beforeAll() {
		ArrayList<Student> students = new ArrayList<Student>(){
			{
				add(new Student(1, "Simon", "simon@lexicon", "stockholm"));
				add(new Student(2, "Antoine", "antoine@lexicon", "ramdala"));
			}
		};
		testCourse = new Course(12, "Java", LocalDate.now(), 12);
	}

	@Test
	void testSettersAndGetters() {
		testCourse.setCourseName("Java");
		//testCourse.getStartDate(LocalDate.now());
		//testCourse.set
	}

}
