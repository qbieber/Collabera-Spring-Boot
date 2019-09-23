package com.collabera.school.service;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.collabera.school.model.Student;

@Service
public class StudentService 
{
	private static List<Student> students = new ArrayList<Student>();
	private static int idCounter = 1;
	
	static
	{
		students.add(new Student(idCounter++, "Jack", "Johnson", LocalDate.of(1998, 5, 22), "Computer Science"));
		
		students.add(new Student(idCounter++, "Marshal", "Matthews", LocalDate.of(1995, 7, 13), "Potions"));
		
		students.add(new Student(idCounter++, "Matthew", "Truelove", LocalDate.of(1950, 12, 12), "Computer Science"));
		
		students.add(new Student(idCounter++, "Lee", "Angioletti", LocalDate.of(1997, 1, 13), "Ping Pong"));
		
		students.add(new Student(idCounter++, "Daniel", "Espana", LocalDate.of(1969, 4, 20), "Spanish"));
	}
	
	// C.R.U.D
	// Create
	// Read
	// Update
	// Delete
	
	//Create
	public Student addStudent(String firstName, String lastName, LocalDate dob, String major)
	{
		Student student = new Student(idCounter++, firstName, lastName, dob, major);
		students.add(student);
		return student;
	}
	
	//Read
	public List<Student> getAllStudents() 
	{
		return students;
	}
	
	public Student getStudent(int id)
	{
		Student student = null;
		
		for (int s = 0; s < students.size(); s++)
		{
			if(students.get(s).getId() == id)
			{
				student = students.get(s);
				break;
			}
		}
		
		return student;
	}
	
	public List<Student> getStudentsInMajor(String major)
	{
		List<Student> studentMajor = new ArrayList<Student>();
		
		for (int s = 0; s < students.size(); s++)
		{
			if(students.get(s).getMajor().toLowerCase().equals(major.toLowerCase()));
			{
				studentMajor.add(students.get(s));
			}
		}
		
		return studentMajor;
	}
	
	//Update
	public void updateStudent(Student student)
	{
		Student studentToUpdate = getStudent(student.getId());
		studentToUpdate.setFirstName(student.getFirstName());
		studentToUpdate.setLastName(student.getLastName());
		studentToUpdate.setDateOfBirth(student.getDateOfBirth());
		studentToUpdate.setMajor(student.getMajor());
		
	}
	
	//Delete
	public void deleteStudent(int id)
	{
		for (int s = 0; s < students.size(); s++)
		{
			if(students.get(s).getId() == id)
			{
				students.remove(students.get(s));
				break;
			}
		}
	}
	
	public void deleteAllStudents()
	{
		students.clear();
	}
}
