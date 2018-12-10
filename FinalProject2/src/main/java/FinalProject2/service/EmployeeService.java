package FinalProject2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.Employee;

import FinalProject2.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAll() {
	    return repository.findAll();
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

}
