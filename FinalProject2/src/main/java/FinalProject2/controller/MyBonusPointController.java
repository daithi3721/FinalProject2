package FinalProject2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject2.model.BonusPointUse;
import FinalProject2.model.BonusPointUseForm;
import FinalProject2.model.CostDivision;
import FinalProject2.model.TaskMonthlyResult;
import FinalProject2.model.UserAccount;
import FinalProject2.pagination.PagenationHelper;
import FinalProject2.service.BonusPointUseService;
import FinalProject2.service.CostDivisionService;
import FinalProject2.service.TaskMonthlyResultService;
import FinalProject2.utility.UtilityMethod;

@Controller
@RequestMapping("mybonuspoint")
public class MyBonusPointController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	TaskMonthlyResultService taskMRS;
	
	@Autowired
	CostDivisionService costDS;

	@Autowired
	BonusPointUseService bPointUS;
	
	@GetMapping
	public String bonusPoint(Model model) {
		UserAccount user = (UserAccount) session.getAttribute("user");
		Page<TaskMonthlyResult> taskMRP = taskMRS.findByEmployeeId(0, 3, user.getUsername());
		model.addAttribute("TaskMonthlyResults", taskMRP);
        model.addAttribute("page", PagenationHelper.createPagenation(taskMRP));
		Page<BonusPointUse> bPointUP = bPointUS.findByEmployeeId(0, 5, user.getUsername());
		model.addAttribute("BonusPointUses", bPointUP);
        model.addAttribute("bPointUP", PagenationHelper.createPagenation(bPointUP));
		int getpoint = taskMRS.getSumBonusPoint(user.getUsername());
		int usepoint = bPointUS.getSumUsePoint(user.getUsername());
		model.addAttribute("point", getpoint - usepoint);
		return "mypage/bonuspoint";
	}
}
