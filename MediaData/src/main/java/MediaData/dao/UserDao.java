package MediaData.dao;

import java.util.List;

import MediaData.entity.User;

public interface UserDao {

	public abstract void addUser(User user);

	public abstract List<User> getUsers(String userName);

}