package net.loco.dao.users;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.loco.domain.users.User;

@Repository("userDao")
public class MyBatisUserDao implements UserDao {
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	@Override
	public User findById(String userId) {
		return sqlSession.selectOne("UserMapper.findById", userId);
	}

	@Override
	public void create(User user) {
			sqlSession.insert("UserMapper.create", user);
	}

	@Override
	public void update(User user) {
		sqlSession.update("UserMapper.update", user);
	}
	
	@Override
	public void delete(User user){
		sqlSession.delete("UserMapper.delBoard", user);
	}
	
	@Override
	public List<HashMap<String, String>> selBoardList(){
		return sqlSession.selectList("UserMapper.selBoardList");
	}
	
}
