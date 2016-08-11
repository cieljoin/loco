package net.loco.bootstrap.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bootstrap")
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/main.do")
	public String main() {
		log.debug("logback");
		return "/bootstrap/main";
	}
}
