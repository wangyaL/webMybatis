package com.mybatis;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.generator.user.dao.UserMapper;
import com.mybatis.generator.user.model.User;
import com.mybatis.generator.user.model.UserExample;


public class GeneratorTest {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	
	static{
		try{
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession(){
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		GeneratorTest t = new GeneratorTest();
//		t.selectByPrimaryKey();
		t.selectByExample();
//		t.add();
//		t.updatesd();
	}
	
	@SuppressWarnings("unused")
	private void selectByPrimaryKey(){
		SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userOperation=session.getMapper(UserMapper.class);
            User user = userOperation.selectByPrimaryKey(1);
			System.out.println("==userName==>>"+user.getUsername()+"---------loginName==>>"+user.getLoginname());
        } finally {
            session.close();
        }
	}
	
	private void selectByExample(){
		SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userOperation=session.getMapper(UserMapper.class);
            UserExample userExample = new UserExample();
//            userExample.createCriteria().andIdEqualTo(2);
//            userExample.createCriteria().andIdLessThanOrEqualTo(4).andIdNotEqualTo(2);
//            userExample.setOrderByClause("loginName");
//            userExample.setDistinct(true);
            List<User> users = userOperation.selectByExample(userExample);
            for (User user2 : users) {
            	System.out.println("id="+user2.getId()+"-------userName="+user2.getUsername()
            			+"---------loginName="+user2.getLoginname()
            			+"---------password="+user2.getPassword());
            }
        } finally {
            session.close();
        }
	}
	
	private void add(){
		SqlSession session = sqlSessionFactory.openSession();
		User user = new User();
		user.setLoginname("iiiii");
		user.setUsername("asdf");
		try {
			UserMapper userMapper = session.getMapper(UserMapper.class);
//			int status = userMapper.insert(user);
			int status = userMapper.insertSelective(user);
			session.commit();
			System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void updatesd(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserMapper userMapper = session.getMapper(UserMapper.class);
			User u = userMapper.selectByPrimaryKey(5);
			u.setUsername("pppp");
			int status = userMapper.updateByPrimaryKey(u);
			session.commit();
			System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
