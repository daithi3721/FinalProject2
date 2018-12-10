package FinalProject2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.Department;
import FinalProject2.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {
	
	@Autowired
	DepartmentRepository repository;
	
	public List<Department> findAll() {
	    return repository.findAll();
	}

}
