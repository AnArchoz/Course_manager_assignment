package se.lexicon.course_manager_assignment.model;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private String name;
	private String email;
	private String address;

	public Student(int id, String name, String email, String address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public Student() {

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Student)){
			return false;
		}

		Student student = (Student) obj;
		return (this.address.equals(student.getAddress())
				&& this.id == student.getId()
				&& this.email.equals(student.getEmail())
				&& this.name.equals(student.getName()));
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
