package net.loco.bootstrap.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.loco.dao.users.UserDao;
import net.loco.domain.users.User;

@Controller
@RequestMapping("/bootstrap")
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/main.do")
	public String main() {
		return "/bootstrap/main";
	}
	
	@RequestMapping("/join.do")
	public String guestbook(Model model) {
		model.addAttribute("user", new User());
		return "/bootstrap/join";
	}
	
	@RequestMapping("/login.do")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "/bootstrap/login";
	}
	
	@RequestMapping(value="/create.do", method=RequestMethod.POST)
	public String create(User user){
		log.debug("User : {}", user);
		userDao.create(user);
		log.debug("Database : {}", userDao.findById(user.getUserId()));
		
		return "/bootstrap/guestbook";
	}
}
