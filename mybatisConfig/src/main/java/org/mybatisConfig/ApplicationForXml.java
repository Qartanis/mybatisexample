package org.mybatisConfig;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONArray;

/**
 * Hello world!
 *
 */
public class ApplicationForXml {
	public static void main(String[] args) {
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
		String statement = "org.mybatisConfig.mapper.userMapper.getUserList";
		//分页插件使用
		PageHelper.startPage(1, 1);
		List<Map<String,Object>> users =session.selectList(statement);
		System.out.println(new JSONArray().fromObject(users));
		//使用PageInfo包装结果
		PageInfo  page=new PageInfo (users);
		System.out.println("页面统计:"+page.getPages());
		System.out.println("当前页:"+page.getPageNum());
		System.out.println("合计行:"+page.getTotal());
		//一级缓存测试
		session.close();
	}
}
