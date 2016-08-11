package net.loco.web.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@RequestMapping("/users/home")
	public String home(){
		return "users/home";
	}
	
	@RequestMapping("/users/form")
	public String form(){
		return "users/form";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String create(){
		return "users/form";
	}
}
