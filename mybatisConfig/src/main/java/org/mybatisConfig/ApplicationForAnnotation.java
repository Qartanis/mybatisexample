package org.mybatisConfig;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatisConfig.mapper.User;

import net.sf.json.JSONArray;

public class ApplicationForAnnotation {
	public static void main(String [] args) {
		String resource = "config/mybatisConfig.xml";
		//加载mybatis的配置文件（它也加载关联的映射文件）
		Reader reader = null;
		try {
			reader= Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件加载异常");
			System.out.println(e);
		}
		//构建sqlSession的工厂
		SqlSessionFactory sessionFactory= new SqlSessionFactoryBuilder().build(reader);
		//创建能执行映射文件中sql的sqlSession
		SqlSession  session =  sessionFactory.openSession();
		User user =session.getMapper(User.class);
		List<Map<String,Object>> users=user.getList();
		System.out.println(new JSONArray().fromObject(users));
		session.close();
	}
	
}
