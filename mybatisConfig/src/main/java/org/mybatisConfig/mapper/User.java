package org.mybatisConfig.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface User {
	@Select("SELECT\r\n" + 
			"users.id,\r\n" + 
			"users.lname,\r\n" + 
			"users.age\r\n" + 
			"FROM\r\n" + 
			"users")
	List<Map<String,Object>> getList();
}
