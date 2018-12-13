package FinalProject2.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import FinalProject2.model.Department;
import FinalProject2.model.Employee;
import FinalProject2.model.EmployeeSearch;
import FinalProject2.model.Position;
import FinalProject2.service.DepartmentService;
import FinalProject2.service.EmployeeService;
import FinalProject2.service.PositionService;

@Controller
@RequestMapping("master/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	HttpSession session; 
	
	@GetMapping
    public String index(Model model) {
		
        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll_activeDepartment();
        List<Position> positions = positionService.findAll_activePosition();
        
        String employeeId_start = employees.get(0).getEmployee_id();
        String employeeId_to = employees.get(employees.size() - 1).getEmployee_id();
        
        model.addAttribute("employees", employees);
        session.setAttribute("departments", departments);
        session.setAttribute("positions", positions);

        model.addAttribute("employeeId_start", employeeId_start);
        model.addAttribute("employeeId_to", employeeId_to);
    
        return "master/employee/index";
    }
	
	@PostMapping("employeeSearch")
	public String employeeSearch(@Valid @ModelAttribute EmployeeSearch employeeSearch, Model model) {
		
		List<Employee> employees_base = employeeService.findAll();
		
		String employee_name = employeeSearch.getEmployee_name();
		String age = employeeSearch.getAge();
		String department_name = employeeSearch.getDepartment_name();
		String position_name = employeeSearch.getPosition_name();
		String sex = employeeSearch.getSex();
		
		List<Employee> employees = employeeService.findBySearch(employees_base, employeeSearch);
		String employeeId_start = null;
		String employeeId_to = null;
		
		if(employees.size() == 0) {
			employeeId_start = employees_base.get(0).getEmployee_id();
			employeeId_to = employees_base.get(employees_base.size() - 1).getEmployee_id();
			
		} else {
			employeeId_start = employeeSearch.getEmployeeId_start();
			employeeId_to = employeeSearch.getEmployeeId_to();
		}
		model.addAttribute("employees", employees);
		model.addAttribute("employeeId_start", employeeId_start);
        model.addAttribute("employeeId_to", employeeId_to);
        model.addAttribute("employee_name", employee_name);
        model.addAttribute("department_name", department_name);
        model.addAttribute("position_name", position_name);
        model.addAttribute("sex", sex);
        model.addAttribute("age", age);
        
		return "master/employee/index";
	}
	
	@GetMapping("new")
    public String newEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
	 
        return "employee/new";
    }
	
	@GetMapping("{employee_id}/edit")
    public String edit(@PathVariable String employee_id, Model model) {
		Optional<Employee> employee_check = employeeService.findOne(employee_id);
		if (employee_check.isPresent()){
		    Employee employee = employee_check.get();
		    model.addAttribute("employee", employee);
		}
		else{
		   // alternative processing....
		}

        return "employee/edit";
    }
	
	@GetMapping("{employee_id}")
    public String show(@PathVariable String employee_id, Model model) {
		Optional<Employee> employee_check = employeeService.findOne(employee_id);
		if (employee_check.isPresent()){
		    Employee employee = employee_check.get();
		    model.addAttribute("employee", employee);
		}
		else{
		   // alternative processing....
		}

        return "employee/show";
    }
	
	@PostMapping
    public String create(@Valid @ModelAttribute Employee employee, BindingResult result) {
		if(result.hasErrors()) {
			return "employee/new";
		}
		Employee new_employee = employeeService.newEmployeeId(employee);
        employeeService.save(new_employee);
        
        return "redirect:/employee";
    }
	
	@PutMapping("{employee_id}")
    public String update(@PathVariable String employee_id, @Valid @ModelAttribute Employee employee, BindingResult result) {
       if(result.hasErrors()) {
    	   return "employee/edit";
       }
       employee.setEmployee_id(employee_id);
       employeeService.save(employee);
        
        return "redirect:/employee";
    }
	
	@DeleteMapping("{employee_id}")
    public String destroy(@PathVariable String employee_id) {
        employeeService.delete(employee_id);
        return "redirect:/employee";
    }
}



