package FinalProject2.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject2.service.EmployeeService;
import FinalProject2.model.Employee;
import FinalProject2.model.UserAccount;

@Controller
@RequestMapping("myaccount")
public class MyAccountController {
	
	@Autowired
	HttpSession session;

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public String myacount(Model model) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		Optional<Employee> employee = employeeService.findOne(user.getUsername());
		Employee emp = employee.get();
		model.addAttribute("employee", emp);
		return "mypage/account";
		
	}
}
