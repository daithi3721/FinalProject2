package FinalProject2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.Department;
import FinalProject2.model.TaskDivision;
import FinalProject2.repository.DepartmentRepository;
import FinalProject2.repository.TaskDivisionRepository;

@Service
@Transactional
public class TaskDivisionService {
	
	@Autowired
	TaskDivisionRepository repository;
	
	public List<TaskDivision> findAll() {
	    return repository.findAll();
	}

}
