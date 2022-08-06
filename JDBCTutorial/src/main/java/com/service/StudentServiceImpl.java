package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StudentDao;
import com.dto.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao studentDao;

	@Override
	public List<Student> getStudentList() {

		return studentDao.getStudentList();
	}

	@Override
	public Student createStudent(Student student) {
		return studentDao.createStudent(student);
	}

	@Override
	public boolean updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}

	@Override
	public int deleteStudent(int id) {
		return studentDao.deleteStudent(id);
	}

	@Override
	public List<Student> getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

}
