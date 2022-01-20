package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

	@Autowired
	private CourseDao testObject;

	@Test
	@DisplayName("Test context successfully setup")
	void context_loads() {
		assertNotNull(testObject);
	}

	@Test
	void testCreateUniqueStudent() {
		// Tests if unique id:s are created even with equal values elsewise

		Course course = testObject.createCourse("Java", LocalDate.of(2021, 1, 1), 4);
		Course course1 = testObject.createCourse("Java", LocalDate.of(2021, 1, 1), 4);

		assertNotEquals(course, course1);
	}

	@Test
	void testFindByID() {
		Course course = testObject.createCourse("Java", LocalDate.of(2021, 1, 1), 4);
		Course course2 = testObject.findById(course.getId());

		assertEquals(course, course2);
	}

	@Test
	void testFindByNameContains() {
		testObject.createCourse("Java", LocalDate.of(2021, 1, 1), 4);

		ArrayList<Course> courseList = (ArrayList<Course>) testObject.findByNameContains("ja");
		ArrayList<Course> courseList1 = (ArrayList<Course>) testObject.findByNameContains("jav");

		assertEquals("Java", courseList.get(0).getCourseName());
		assertEquals("Java", courseList1.get(0).getCourseName());

	}

	@Test
	void testFindBeforeDate() {
		testObject.createCourse("Java", LocalDate.of(2020, 1, 1), 4);
		testObject.createCourse("C++", LocalDate.of(2021, 1, 1), 4);

		ArrayList<Course> list = (ArrayList<Course>) testObject.findByDateBefore(LocalDate.of(2022, 1, 1));

		assertTrue(LocalDate.of(2022, 1, 1).isAfter(list.get(0).getStartDate()));
		assertTrue(LocalDate.of(2022, 1, 1).isAfter(list.get(1).getStartDate()));
	}

	@Test
	void testFindAfterDate() {
		testObject.createCourse("Java", LocalDate.of(2020, 1, 1), 4);
		testObject.createCourse("C++", LocalDate.of(2021, 1, 1), 4);

		ArrayList<Course> list = (ArrayList<Course>) testObject.findByDateAfter(LocalDate.of(2019, 1, 1));

		assertTrue(LocalDate.of(2019, 1, 1).isBefore(list.get(0).getStartDate()));
		assertTrue(LocalDate.of(2019, 1, 1).isBefore(list.get(1).getStartDate()));
	}

	@Test
	void testFindAll() {
		Course course = testObject.createCourse("Java", LocalDate.of(2021, 1, 1), 4);
		Course course1 = testObject.createCourse("c++", LocalDate.of(2011, 1, 1), 3);

		Collection<Course> list = testObject.findAll();

		assertTrue(list.contains(course));
		assertTrue(list.contains(course1));
	}

	@Test
	void testFindStudentById() {
		Course course = testObject.createCourse("Java", LocalDate.of(2021, 1, 1), 4);
		Course course1 = testObject.createCourse("c++", LocalDate.of(2011, 1, 1), 3);

		Student student = new Student(1, "simon", "simon@lexicon", "växjö");

		course.enrollStudents(student);
		course1.enrollStudents(student);

		Collection<Course> list = testObject.findByStudentId(student.getId());

		assertTrue(list.contains(course));
		assertTrue(list.contains(course1));
	}

	@Test
	void testRemoveCourse() {
		Course course = testObject.createCourse("Java", LocalDate.of(2021, 1, 1), 4);

		assertTrue(testObject.removeCourse(course));
		assertFalse(testObject.removeCourse(course));
	}



	@AfterEach
	void tearDown() {
		testObject.clear();
		CourseSequencer.setCourseSequencer(0);
	}
}
