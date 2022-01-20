package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

	@Autowired
	private StudentDao testObject;

	@Test
	@DisplayName("Test context successfully setup")
	void context_loads() {
		assertNotNull(testObject);
	}

	@Test
	void testCreateUniqueStudent() {
		// Tests if unique id:s are created even with equal values elsewise
		Student student = testObject.createStudent("simon", "simon@lexicon", "växjö");
		Student student2 = testObject.createStudent("simon", "simon@lexicon", "växjö");

		assertNotEquals(student, student2);
	}

	@Test
	void testFindByEmailIgnoreCase() {
		testObject.createStudent("simon", "simon@lexicon", "växjö");

		assertEquals("simon", testObject.findByEmailIgnoreCase("sImOn@LeXiCoN").getName());
	}

	@Test
	void testFindByNameContains() {
		testObject.createStudent("simon", "simon@lexicon", "växjö");

		ArrayList<Student> studentList = (ArrayList<Student>) testObject.findByNameContains("si");
		ArrayList<Student> studentList1 = (ArrayList<Student>) testObject.findByNameContains("simo");

		assertEquals("simon", studentList.get(0).getName());
		assertEquals("simon", studentList1.get(0).getName());

	}

	@Test
	void testFindByID() {
		Student student = testObject.createStudent("simon", "simon@lexicon", "växjö");


		assertEquals(student, testObject.findById(student.getId()));
	}
	//Write your tests here

	@Test
	void testRemoveStudent() {
		Student student = testObject.createStudent("simon", "simon@lexicon", "växjö");

		assertTrue(testObject.removeStudent(student));
		assertFalse(testObject.removeStudent(student));
	}


	@Test
	void testFindAll() {
		Student student = testObject.createStudent("simon", "simon@lexicon", "växjö");
		Student student1 = testObject.createStudent("anto", "anto@lexicon", "not växjö xd");

		Collection<Student> list = testObject.findAll();

		assertTrue(list.contains(student));
		assertTrue(list.contains(student1));
	}


	@AfterEach
	void tearDown() {
		testObject.clear();
		StudentSequencer.setStudentSequencer(0);
	}
}
