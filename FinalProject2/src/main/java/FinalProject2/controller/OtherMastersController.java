package FinalProject2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject2.model.CostDivision;
import FinalProject2.model.DayoffDivision;
import FinalProject2.model.Department;
import FinalProject2.model.Employee;
import FinalProject2.model.Position;
import FinalProject2.model.TaskDivision;
import FinalProject2.service.CostDivisionService;
import FinalProject2.service.DayOffDivisionService;
import FinalProject2.service.DepartmentService;
import FinalProject2.service.PositionService;
import FinalProject2.service.TaskDivisionService;

@Controller
@RequestMapping("other_masters")
public class OtherMastersController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private TaskDivisionService taskDivisionService;
	
	@Autowired
	private CostDivisionService costDivisionService;
	
	@Autowired
	private DayOffDivisionService dayOffDivisionService;
	
	@GetMapping
    public String index(Model model) {
		
        List<Department> departments = departmentService.findAll();
        List<Position> positions = positionService.findAll();
        List<TaskDivision> taskDivisions = taskDivisionService.findAll();
        List<CostDivision> costDivisions = costDivisionService.findAll();
        List<DayoffDivision> dayOffDivisions = dayOffDivisionService.findAll();
        
        model.addAttribute("departments", departments);
        model.addAttribute("positions", positions);
        model.addAttribute("taskDivisions", taskDivisions);
        model.addAttribute("costDivisions", costDivisions);
        model.addAttribute("dayOffDivisions", dayOffDivisions);
        
        return "other_masters";
        
    }
	
	@GetMapping("new")
    public String newEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
	 
        return "employee/new";
    }

}
