package net.loco.dao.users;

import java.util.HashMap;
import java.util.List;

import net.loco.domain.users.User;

public interface UserDao {

	User findById(String userId);

	void create(User user);

	void update(User user);

	public List<HashMap<String, String>> selBoardList();
	
}