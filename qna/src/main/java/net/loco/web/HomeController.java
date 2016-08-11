package net.loco.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.loco.dao.users.UserDao;
import net.loco.domain.users.User;

@Controller
@RequestMapping("/users2")
public class HomeController {
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired	
	private UserDao userDao;
	
	@RequestMapping("/form")
	public String form(){
		return "form";
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(User user){
		log.debug("User : {}", user);
		userDao.create(user);
		log.debug("DB User : {}", userDao.findById(user.getUserId()));
		
		return "from";
	}
	
	@RequestMapping("/home")
	public String home() {
		
		log.debug("logback");
		return "home";
	}
}
