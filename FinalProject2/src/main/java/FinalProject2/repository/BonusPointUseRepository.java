package FinalProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import FinalProject2.model.BonusPointUse;

@Repository
public interface BonusPointUseRepository extends JpaRepository<BonusPointUse, String>{

	@Query("SELECT SUM(u.bonus_point_use) FROM BonusPointUse u WHERE employee_id = :username")
	int getSumUsePoint(@Param("username") String username);

}
