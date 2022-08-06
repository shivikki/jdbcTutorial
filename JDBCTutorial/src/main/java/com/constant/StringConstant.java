package com.constant;

public class StringConstant {

	public static final String GET_STUDENT_LIST = "select * from" + " springjdbc.student;";

	public static final String INSERT_STUDENT = "insert into student(id,name,city) values(?,?,?);";
	// id is key of map params
	public static final String FETCH_STUDENT_BY_ID = "select count(*) from student where id=?;";

	public static final String UPDATE_STUDENT_BY_ID = "update student set name=:student_name, city=:student_city where id=:student_id";

	public static final String DELETE_STUDENT_BY_ID = "delete from student where id=?";

	public static final String GET_STUDENT_BY_ID = "select * from student where id=?";

}
