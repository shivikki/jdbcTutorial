package com.mapper;

import java.util.Map;

import com.dto.Student;

public class Mapper {

	public Object mapStudentList(Map<String, Object> studObj) {
		Student student = new Student();
		student.setId((int) studObj.get("id")); // "id" col name in db
		student.setName((String) studObj.get("name"));
		student.setCity((String) studObj.get("city"));
		return student;
	}
	

}
