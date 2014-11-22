package MediaData.dao;

import java.util.List;

import MediaData.entity.User;

public interface UserDao {

	public void addUser(User user);

	public List<User> getUsers(String userName);
	
	public List<User> getUsers();
}