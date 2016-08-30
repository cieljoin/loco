package net.loco.dao;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import net.loco.domain.users.User;


public class MyBatisTest {
	private static final Logger log = LoggerFactory.getLogger(MyBatisTest.class);
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws IOException{
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("loco.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		log.info("database initialized success!");
	}
	
	private DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/loco");
		dataSource.setUsername("sa");
		return dataSource;
	}

	@Test
	public void gettingStarted() throws Exception{
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("UserMapper.findById", "cieljoin5");
			log.debug("User : {}", user);
			
		  // Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
		} finally {
		  session.close();
		}
	}
	
	@Test
	public void insert() throws Exception{
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = session.selectOne("UserMapper.create", new User("cieljoin3", "asdf", "Name", "ciel@naver.com", "010", "Message"));
			log.debug("User DB : {}", session.selectOne("UserMapper.findById","cieljoin3"));
			//(#{userId}, #{password}, #{name}, #{email}, #{phone}, #{message})
		  // Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
		} finally {
		  session.close();
		}
	}
}
