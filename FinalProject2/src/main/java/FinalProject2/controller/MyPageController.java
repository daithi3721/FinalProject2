package FinalProject2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject2.model.UserAccount;

@Controller
@RequestMapping("mypage")
public class MyPageController {
	
	@Autowired
	HttpSession session;

	@GetMapping
	public String mypage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserAccount user = UserAccount.class.cast(authentication.getPrincipal());
    	session.setAttribute("user", user);
		return "mypage/index";
	}
}
