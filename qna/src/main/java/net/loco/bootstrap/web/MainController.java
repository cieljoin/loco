package net.loco.bootstrap.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import net.loco.dao.users.UserDao;
import net.loco.domain.users.Authenticate;
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
		model.addAttribute("authenticate", new Authenticate());
		return "/bootstrap/login";
	}
	
	@RequestMapping(value="/loginChk.do", method=RequestMethod.POST)
	public String loginChk(Authenticate authenticate, Model model, HttpSession session){
		User user = userDao.findById(authenticate.getUserId());
		
		if(user == null){
			//존재하지 않는 사용자
			model.addAttribute("errorMessage", "존재하지 않는 사용자압니다");
			return "/bootstrap/login";
		}
		
		if(!user.matchPassword(authenticate)){
			//비밀번호가 다른 상태
			model.addAttribute("errorMessage","비밀번호가 틀립니다.");
			return "/bootstrap/login";
		}
				
		
		//세션에 사용자 정보 저장
		session.setAttribute("userId", user.getUserId());

		return "redirect:/bootstrap/main.do";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/bootstrap/main.do";
	}
	
	@RequestMapping("/updateForm.do")
	public String updateForm(String userId, Model model) {
		
		if(userId == null){
			
		}
		
		User user = userDao.findById(userId);
		model.addAttribute("user", user);
		
		return "bootstrap/join";
	}
	
	
	
	@RequestMapping(value="/create.do", method=RequestMethod.POST)
	public void create(User user, HttpServletRequest req, HttpServletResponse res) throws Exception{
		Gson gson = new Gson();  // JSON 으로 데이터 편히 만들기위한 Gson
		Map<String, Object> param = new HashMap<String, Object>(); // return 데이터 저장소
		
		
		log.debug("User : {}", user);
		userDao.create(user);
		param.put("key", "03");
		log.debug("Database : {}", userDao.findById(user.getUserId()));

		String jsonData = gson.toJson(param); // JSON으로 데이터 만들어 줌
		String output = req.getParameter("callback") + "("+ jsonData +")"; 

		PrintWriter out = res.getWriter(); 
		out.println(output);
		//return "/bootstrap/guestbook";
	}
	
	@RequestMapping(value="/create.do", method=RequestMethod.PUT)
	public void update(User user, HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception{
		Gson gson = new Gson();  // JSON 으로 데이터 편히 만들기위한 Gson
		Map<String, Object> param = new HashMap<String, Object>(); // return 데이터 저장소
		
		log.debug("User : {}", user);
		userDao.update(user);
		
		Object temp = session.getAttribute("userId");
		if(temp == null){
			param.put("key", "01");
			throw new NullPointerException();
		}
		
		String userId = (String)temp;
		if( !user.matchUserId(userId)){
			param.put("key", "02");
			throw new NullPointerException();
		}

		userDao.findById(user.getUserId());
		param.put("key", "03");
		
		String jsonData = gson.toJson(param); // JSON으로 데이터 만들어 줌
		String output = req.getParameter("callback") + "("+ jsonData +")"; 

		PrintWriter out = res.getWriter(); 
		out.println(output);
	}
}
