package net.loco.dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import net.loco.domain.users.User;

public class JdbcUserDao extends JdbcDaoSupport implements UserDao{
	private static final Logger log = LoggerFactory.getLogger(JdbcUserDao.class);
	
	@PostConstruct
	public void  initialize(){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("loco.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		log.info("database initialized success!");
		
	}

	/* (non-Javadoc)
	 * @see net.loco.dao.users.IUserDao#findById(java.lang.String)
	 */
	@Override
	public User findById(String userId) {
		String sql = "select userId, password, name, email, phone, message from USERS where userId = ?";
		RowMapper<User> rowMapper = new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(
							rs.getString("userId"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getString("email"),
							rs.getString("phone"),
							rs.getString("message")
						);
			}
		};
		
		try{
			return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
		}catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see net.loco.dao.users.IUserDao#create(net.loco.domain.users.User)
	 */
	@Override
	public void create(User user) {
		String sql = "insert into USERS (userId, password, name, email, phone, message) values (?, ?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), user.getPhone(), user.getMessage() );
	}
	
	/* (non-Javadoc)
	 * @see net.loco.dao.users.IUserDao#update(net.loco.domain.users.User)
	 */
	@Override
	public void update(User user){
		String sql = "update USERS set password=?, name=?, email=?, phone=?, message=? where userId = ?";
		getJdbcTemplate().update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getPhone(), user.getMessage(), user.getUserId() );
	}

	@Override
	public List<HashMap<String, String>> selBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

}
