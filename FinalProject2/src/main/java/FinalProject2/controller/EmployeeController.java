package FinalProject2.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import FinalProject2.model.Department;
import FinalProject2.model.Employee;
import FinalProject2.model.EmployeeSearch;
import FinalProject2.model.Position;
import FinalProject2.pagination.PagenationHelper;
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
		
		//ページングの最初のページを設定
		final int page_first = 0;
		
        Page<Employee> employees_page = employeeService.findAll(page_first);
        
        List<Employee> employees_list = employees_page.getContent();
        List<Department> departments = departmentService.findAll_activeDepartment();
        List<Position> positions = positionService.findAll_activePosition();
        
        model.addAttribute("employees", employees_page);

        session.setAttribute("employees", employees_page);
        session.setAttribute("departments", departments);
        session.setAttribute("positions", positions);
        
        model.addAttribute("page", PagenationHelper.createPagenation(employees_page));
    
        return "master/employee/index";
    }
	
	@GetMapping("page={page}")
	public String paginate(@PathVariable(name = "page") String page, Model model) {
		
		int page_number = Integer.parseInt(page);
		
		List<Employee> employees_base = employeeService.findAll();
		
		EmployeeSearch employeeSearch = (EmployeeSearch) session.getAttribute("employeeSearch");
		
		Page<Employee> employees_page;
		
		if(employeeSearch == null) {
			
			employees_page = employeeService.findAll(page_number);
			
		} else {
			
			employees_page = employeeService.findBySearch(page_number, employees_base, employeeSearch);
			
		}
		
		model.addAttribute("employees", employees_page);
		
		model.addAttribute("page", PagenationHelper.createPagenation(employees_page));
		
		return "master/employee/index";
		
	}
	
	@PostMapping("employeeSearch")
	public String employeeSearch(@Valid @ModelAttribute EmployeeSearch employeeSearch, Model model) {
		
		//ページングの最初のページを設定
		final int page_first = 0;
		
		//検索条件を維持したままのページネーションを実装する為にフラグとして使う
		session.setAttribute("employeeSearch", employeeSearch);
		
		//findBySearchの引数に渡すEmployeeリストを取得
		List<Employee> employees_base = employeeService.findAll();
		
		//検索条件を取得
		String employee_name = employeeSearch.getEmployee_name();
		String age = employeeSearch.getAge();
		String department_name = employeeSearch.getDepartment_name();
		String position_name = employeeSearch.getPosition_name();
		String sex = employeeSearch.getSex();
		
		Page<Employee> employees_page = employeeService.findBySearch(page_first, employees_base, employeeSearch);
		
		String employeeId_start = null;
		String employeeId_to = null;
		
		if(employees_page.getSize() == 0) {

			employeeId_start = employees_base.get(0).getEmployee_id();
			employeeId_to = employees_base.get(employees_base.size() - 1).getEmployee_id();
			
		} else {
			employeeId_start = employeeSearch.getEmployeeId_start();
			employeeId_to = employeeSearch.getEmployeeId_to();
		}
		
		model.addAttribute("employees", employees_page);
		model.addAttribute("page", PagenationHelper.createPagenation(employees_page));
		
		//検索条件を画面に渡す
		session.setAttribute("employeeId_start", employeeId_start);
		session.setAttribute("employeeId_to", employeeId_to);
		session.setAttribute("employee_name", employee_name);
		session.setAttribute("department_name", department_name);
		session.setAttribute("position_name", position_name);
		session.setAttribute("sex", sex);
		session.setAttribute("age", age);
        
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



