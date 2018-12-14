package FinalProject2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import FinalProject2.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	//Todo: 退職者が表示されなくなる。退職者の役職と部署には「退職済み」を入れることで対処　morishita
	@Query("SELECT emplo FROM Employee emplo "
			+ "LEFT OUTER JOIN EmploymentInfo info ON emplo.employee_id = info.employee_id "
			+ "LEFT OUTER JOIN Department depart ON info.department = depart "
			+ "LEFT OUTER JOIN Position posi ON info.position = posi "
			+ "WHERE emplo.employee_name LIKE %:employee_name% AND "
			+ "emplo.employee_id >= :employeeId_start AND emplo.employee_id <= :employeeId_to AND "
			+ "emplo.birthday >= :birthday_start AND emplo.birthday <= :birthday_to AND "
			+ "depart.department_name  LIKE %:department_name% AND "
			+ "posi.position_name  LIKE %:position_name% AND "
			+ "(emplo.sex = :sex1 OR emplo.sex = :sex2)")
	public Page<Employee> findBySearch(String employeeId_start, String employeeId_to, String employee_name, LocalDate birthday_start, LocalDate birthday_to, String department_name, String position_name, char sex1, char sex2, Pageable pageable);
	
	@Query("SELECT emplo FROM Employee emplo "
			+ "LEFT OUTER JOIN EmploymentInfo info ON emplo.employee_id = info.employee_id "
			+ "LEFT OUTER JOIN Department depart ON info.department = depart "
			+ "LEFT OUTER JOIN Position posi ON info.position = posi "
			+ "WHERE emplo.employee_name LIKE %:employee_name% AND "
			+ "emplo.employee_id >= :employeeId_start AND emplo.employee_id <= :employeeId_to AND "
			+ "emplo.birthday >= :birthday_start AND emplo.birthday <= :birthday_to AND "
			+ "(emplo.sex = :sex1 OR emplo.sex = :sex2)")
	public Page<Employee> findBySearch(String employeeId_start, String employeeId_to, String employee_name, LocalDate birthday_start, LocalDate birthday_to, char sex1, char sex2, Pageable pageable);
	
	@Query("select count(emplo.employee_id) from Employee emplo " + 
			"Left join EmploymentInfo info on emplo.employee_id = info.employee_id " + 
			"Where department_id Is Not Null")
	public int findActiveEmployeeNumber();

}