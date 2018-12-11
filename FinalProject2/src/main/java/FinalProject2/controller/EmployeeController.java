package FinalProject2.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import FinalProject2.model.Employee;
import FinalProject2.service.EmployeeService;

@Controller
@RequestMapping("master/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
    public String index(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        
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



