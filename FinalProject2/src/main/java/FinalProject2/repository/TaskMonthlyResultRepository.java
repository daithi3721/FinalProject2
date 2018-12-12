package FinalProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import FinalProject2.model.TaskMonthlyResult;

@Repository
public interface TaskMonthlyResultRepository extends JpaRepository<TaskMonthlyResult, String>{
	
	@Query("select sum(t.bonus_point_add) from TaskMonthlyResult t where t.employee_id = :username")
	int getSumBonusPoint(@Param("username") String username);

}
