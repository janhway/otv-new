package MediaData.controllers;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import MediaData.dao.UserDao;
import MediaData.entity.ReturnMsg;
import MediaData.entity.User;
import MediaData.msg.CreateUser;

@Controller
@RequestMapping("/user")
public class UserManager {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public void welcomeName(HttpServletRequest req, HttpServletResponse rsp) {
		try {
			String name = (String) req.getParameter("name");
			
			rsp.setContentType("text/plain");
			PrintWriter out = rsp.getWriter();
			out.println("name=" + name);

		} catch (Exception ex) {

		}
		return;

	}
	
	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public void welcomeName(@RequestParam/*(value="cc", required=false, defaultValue="World")*/ String name, HttpServletResponse rsp) {
		try {			
			rsp.setContentType("text/plain");
			PrintWriter out = rsp.getWriter();
			out.println("name=" + name);

		} catch (Exception ex) {

		}
		return;

	}

	@RequestMapping(value = "/", method = RequestMethod.POST, headers = "Content-Type=application/json;charset=UTF-8" /*consumes = "application/json"*/)  
	public @ResponseBody
	User createUser(@RequestBody CreateUser crtUser) {
		
		User user = new User();
		user.setId(0);
		user.setFirstName(crtUser.getFirstName());
		user.setLastName(crtUser.getLastName());
		user.setUserName(crtUser.getUserName());
		user.setPassword(crtUser.getPassWord());
		
		userDao.addUser(user);

		return user;
	}	
	
	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)  
	public @ResponseBody
	List<User> getUsers(@PathVariable String userName) {
		
		List<User> userList = userDao.getUsers(userName);		
		
		return userList;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)  
	public @ResponseBody
	List<User> getUsers() {
		
		List<User> userList = userDao.getUsers();		
		
		return userList;
	}	

}
