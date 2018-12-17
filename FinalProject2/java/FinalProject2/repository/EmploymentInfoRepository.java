package FinalProject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import FinalProject2.model.EmploymentInfo;

public interface EmploymentInfoRepository extends JpaRepository<EmploymentInfo, String> {
	
	
}