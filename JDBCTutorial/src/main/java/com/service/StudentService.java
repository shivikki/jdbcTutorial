package com.service;

import java.util.List;

import com.dto.Student;

public interface StudentService {

	public List<Student> getStudentList();

	public Student createStudent(Student student);

	public boolean updateStudent(Student student);
	
	public int deleteStudent(int id);
	
	public  List<Student> getStudentById(int id);

}
