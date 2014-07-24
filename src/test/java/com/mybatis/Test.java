package com.mybatis;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.user.User;
import com.mybatis.user.UserDao;

public class Test {
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
		Test t = new Test();
//		t.findById();
		t.findAll();
	}
	public void findById(){
		/**没有dao的**/
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//			User user = (User) session.selectOne("com.mybatis.user.User.findById", 1);
//			System.out.println("==userName==>>"+user.getUserName()+"---------loginName==>>"+user.getLoginName());
//		} finally {
//			session.close();
//		}
		
		/**接口的方式编程**/
		SqlSession session = sqlSessionFactory.openSession();
        try {
            UserDao userOperation=session.getMapper(UserDao.class);
            User user = userOperation.findById(1);
			System.out.println("==userName==>>"+user.getUserName()+"---------loginName==>>"+user.getLoginName());
        } finally {
            session.close();
        }
	}
	
	public void findAll(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UserDao userDao = session.getMapper(UserDao.class);
			List<User> users = userDao.findAll("%w%");
			for (User user : users) {
				System.out.println("====id=>>"+user.getId()+"======userName==>>"+user.getUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
