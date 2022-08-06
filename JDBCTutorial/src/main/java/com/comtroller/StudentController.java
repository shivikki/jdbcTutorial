package com.comtroller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.StudentImpl;
import com.dto.Student;
import com.service.StudentService;

@RestController
public class StudentController {

	private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService studentService;

	@GetMapping("/getStudentList")
	public List<Student> getStudentList() {
		List<Student> studentList = new ArrayList<>();
		try {
			studentList = studentService.getStudentList();
			LOG.info("inside controller success");
		} catch (Exception e) {
			LOG.error("Exception occured at /getStudentlist" + e);
		}
		return studentList;
	}

	@PostMapping("/addStudent")
	public Student createStudent(@RequestBody Student student) {
		Student studentCreated = new Student();
		try {
			studentCreated = studentService.createStudent(student);

		} catch (Exception e) {
			LOG.error("Exception occured at /addStudent" + e);
		}
		return studentCreated;
	}
	
	@PostMapping("/updateStudent")
	public boolean updateStudent(@RequestBody Student student) {
		boolean studentUpdated=false;
		try {
			studentUpdated = studentService.updateStudent(student);

		} catch (Exception e) {
			LOG.error("Exception occured at /updateStudent" + e);
		}
		return studentUpdated;
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public int deleteStudent(@PathVariable int id) {
		int studentDeleted=0;
		try {
			 studentDeleted = studentService.deleteStudent(id);

		} catch (Exception e) {
			LOG.error("Exception occured at /updateStudent" + e);
		}
		return studentDeleted;
	}
	
	@PostMapping("/getStudentById/{id}")
	public List<Student> getStudentById(@PathVariable int id) {
		List<Student> studentList=new ArrayList<>();
		try {
			 studentList = studentService.getStudentById(id);

		} catch (Exception e) {
			LOG.error("Exception occured at /getStudentById/{id}" + e);
		}
		return studentList;
	}
}
