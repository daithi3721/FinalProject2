package FinalProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import FinalProject2.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
}