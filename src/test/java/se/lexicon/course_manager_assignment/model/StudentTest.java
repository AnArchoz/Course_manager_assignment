package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
	private Student student;
	private Student student2;
	private Student student3;

	@BeforeEach
	public void init() {
		student = new Student(1, "derp", "derpderp", "derpderpderp");
		student2 = new Student(1, "derp", "derpderp", "derpderpderp");
		student3 = new Student(2, "derp", "derpderp", "derpderpderp");
	}

	@Test
	public void testSettersAndGetters() {
		student.setAddress("stockholm");
		student.setEmail("simon@lexicon");
		student.setName("simon");

		assertAll("student",
				() -> assertEquals("simon", student.getName()),
				() -> assertEquals("stockholm", student.getAddress()),
				() -> assertEquals("simon@lexicon", student.getEmail()));

	}

	@Test
	public void testEqualsTrue() {
		assertEquals(student, student2);	}

	@Test
	public void testEqualsFalse() {
		assertNotEquals(student, student3);
	}
}
