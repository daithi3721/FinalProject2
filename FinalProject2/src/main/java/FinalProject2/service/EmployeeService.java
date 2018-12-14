package FinalProject2.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.Employee;
import FinalProject2.model.EmployeeSearch;
import FinalProject2.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//pagination実装のためにListからPageに戻り値の型を変更。キャストも追加
	public List<Employee> findAll() {
	    return repository.findAll();
	}
	
	public List<Employee> findBySearch(List<Employee> employees, EmployeeSearch employeeSearch) {
		
		String employee_name = employeeSearch.getEmployee_name();
		String employeeId_start = employeeSearch.getEmployeeId_start();
		String employeeId_to = employeeSearch.getEmployeeId_to();
		
		if(employeeId_start.equals("")) {
			employeeId_start = employees.get(0).getEmployee_id();
		}
		if(employeeId_to.equals("")) {
			employeeId_to = employees.get(employees.size() - 1).getEmployee_id();
		}
		
		char sex1 = '0';
		char sex2 = '0';
		switch(employeeSearch.getSex()) {
		case "未選択":
			sex1 = '0';
			sex2 = '1';
			break;
		case "男性":
			sex1 = '0';
			sex2 = '0';
			break;
		case "女性":
			sex1 = '1';
			sex2 = '1';
			break;
		}

		LocalDate now = LocalDate.now();
		LocalDate birthday_start = null;
		LocalDate birthday_to = null;
		switch(employeeSearch.getAge()) {
		case "未選択":
			birthday_start = now.minusYears(100);
			birthday_to = now.minusYears(1);
			break;
		case "10代":
			birthday_start = now.minusYears(19);
			birthday_to = now.minusYears(10);
			break;
		case "20代":
			birthday_start = now.minusYears(29);
			birthday_to = now.minusYears(20);
			break;
		case "30代":
			birthday_start = now.minusYears(39);
			birthday_to = now.minusYears(30);
			break;
		case "40代":
			birthday_start = now.minusYears(49);
			birthday_to = now.minusYears(40);
			break;
		case "50代":
			birthday_start = now.minusYears(59);
			birthday_to = now.minusYears(50);
			break;
		}
		
		String department_name = employeeSearch.getDepartment_name();
		if(department_name.equals("未選択")) {
			department_name= "";
		}
		String position_name = employeeSearch.getPosition_name();
		if(position_name.equals("未選択")) {
			position_name = "";
		}

		return repository.findBySearch(employeeId_start, employeeId_to, employee_name, birthday_start, birthday_to, department_name, position_name, sex1, sex2);
	}
	
	public Optional<Employee> findOne(String employee_id) {
        return repository.findById(employee_id);
    }
	
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public void delete(String employee_id) {
        repository.deleteById(employee_id);
    }
    
    public Employee newEmployeeId(Employee new_employee) {
    	
    	//pagination実装のためにListからPageに戻り値の型を変更
		List<Employee> employees = findAll();

		int i = 0;
		for(Employee employee: employees) {
			if(Integer.parseInt(employee.getEmployee_id()) > i) {
				i = Integer.parseInt(employee.getEmployee_id());
			}
		}
		String NewEmployeeId = String.format("%4s", String.valueOf(i + 1)).replace(" ", "0");
		new_employee.setEmployee_id(NewEmployeeId);
		
    	return new_employee;
    }

	public boolean checkPass(String id, String pass) {
		String dbPass = repository.findById(id).get().getPassword();
		return passwordEncoder.matches(pass, dbPass);
	}
	
	@Transactional
	public void changePass(String username, String newPass) {
		repository.changePass(username, passwordEncoder.encode(newPass));
	}

}
