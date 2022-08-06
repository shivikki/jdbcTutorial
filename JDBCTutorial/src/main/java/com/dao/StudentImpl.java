package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.constant.StringConstant;
import com.dto.Student;
import com.mapper.Mapper;

@Repository
public class StudentImpl implements StudentDao {

	private static final Logger LOG = LoggerFactory.getLogger(StudentImpl.class);

	//private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;

//	@Autowired
//	public void setDataSourceRead(final DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		this.jdbcTemplateNamed = new NamedParameterJdbcTemplate(dataSource);
//	}

	@Override
	public List<Student> getStudentList() {
		List<Student> studList = new ArrayList<>();
		try {
			List<Map<String, Object>> listData = jdbcTemplate.queryForList(StringConstant.GET_STUDENT_LIST);

//			for (Map<String, Object> list : listData) {
//				Student student;
//				student = (Student) new Mapper().mapStudentList(list);
//				studList.add(student);
//			}
			
			listData.forEach((list) ->{
				Student student;
				student = (Student) new Mapper().mapStudentList(list);
				studList.add(student);
			});
		} catch (Exception e) {
			LOG.error("Exception in StudentImpl class + getStudentList() m/d" + e);
		}
		return studList;
	}

	@Override
	public Student createStudent(Student student) {
		int result = 0;

		try {// update function returns int value ie no. of rows affected
			result = jdbcTemplate.update(StringConstant.INSERT_STUDENT, student.getId(), student.getName(),
					student.getCity());
			LOG.info("Number of record inserted " + result);
		} catch (Exception e) {
			LOG.error("Exception in StudentImpl class + createStudent m/d" + e);
		}

		return student;
	}

	@Override
	public boolean updateStudent(Student student) {
		boolean flag = false;
		Student stud = new Student();
//		Map<String,Object> params=new HashMap<>();
//		params.put("id", student.getId());
		try {
			// jdbcTemplateNamed used bcoz with query using map not placeholder ?
			// queryForObject- 3rd argument Student.class is used as that query is return
			// data from Student table
			// Map<String,Object>
			// studs=jdbcTemplateNamed.queryForObject(StringConstant.FETCH_STUDENT_BY_ID,params);

			// int
			// studentId=jdbcTemplate.execute(StringConstant.FETCH_STUDENT_BY_ID,student.getId(),Integer.class);
			// System.out.println("stud "+stud);
			// if(studentId>0) {
			Map<String,Object> params=new HashMap<>();
			params.put("student_id", student.getId());
			params.put("student_name", student.getName());
			params.put("student_city", student.getCity());
			jdbcTemplateNamed.update(StringConstant.UPDATE_STUDENT_BY_ID, params);
			flag = true;
			// }

		} catch (Exception e) {
			LOG.error("Exception in StudentImpl class +updateStudent m/d" + e);
			flag = false;
		}
		return flag;
	}

	@Override
	public int deleteStudent(int id) {
		int result = 0;

		try {
			result = jdbcTemplate.update(StringConstant.DELETE_STUDENT_BY_ID, id);

		} catch (Exception e) {
			LOG.error("Exception in StudentImpl class +deleteStudent m/d" + e);
		}
		return result;
	}

	@Override
	public List<Student> getStudentById(int id) {
		List<Student> studentList = new ArrayList<>();
		
		try {
			List<Map<String, Object>> studData = jdbcTemplate.queryForList(StringConstant.GET_STUDENT_BY_ID, id);
			System.out.println(studData+"studDat");
			
			if(!studData.isEmpty()) {
				for (Map<String, Object> list : studData) {
					Student student = (Student) new Mapper().mapStudentList(list);
					studentList.add(student);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception in StudentImpl class + getStudentById m/d" + e);
		}
		return studentList;
	}

	// select query in jdbc
	/*
	 * queryForMap Query given SQL to create a prepared statement from SQL and a
	 * list of arguments to bind to the query, expecting a result map
	 * 
	 * public T queryForObject(String sql,RowMapper<T>,Object args); use this if
	 * sigle object has to be fetched
	 * 
	 * public List<T> query(String sql,RowMapper<T> rowMapper) rowMapper converts
	 * result set to object
	 * 
	 * implemetation of Row Mapper needs to be done which maps data to object
	 * 
	 * 
	 * 
	 */
}
