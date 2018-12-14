package FinalProject2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import FinalProject2.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
		

	@Query("SELECT emplo FROM Employee emplo "
			+ "INNER JOIN EmploymentInfo info ON emplo.employee_id = info.employee_id "
			+ "INNER JOIN Department depart ON info.department = depart "
			+ "INNER JOIN Position posi ON info.position = posi "
			+ "WHERE emplo.employee_name LIKE %:employee_name% AND "
			+ "emplo.employee_id >= :employeeId_start AND emplo.employee_id <= :employeeId_to AND "
			+ "emplo.birthday >= :birthday_start AND emplo.birthday <= :birthday_to AND "
			+ "depart.department_name  LIKE %:department_name% AND "
			+ "posi.position_name  LIKE %:position_name% AND "
			+ "(emplo.sex = :sex1 OR emplo.sex = :sex2)")
	public List<Employee> findBySearch(String employeeId_start, String employeeId_to, String employee_name, LocalDate birthday_start, LocalDate birthday_to, String department_name, String position_name, char sex1, char sex2);

	@Modifying
	@Query("UPDATE Employee emplo SET emplo.password = :newPass, emplo.password_update = GETDATE() WHERE emplo.employee_id = :username")
	public void changePass(String username, String newPass);

}