package FinalProject2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.TaskMonthlyResult;
import FinalProject2.repository.TaskMonthlyResultRepository;

@Service
@Transactional
public class TaskMonthlyResultService{

	@Autowired
	TaskMonthlyResultRepository taskMRR;

	public List<TaskMonthlyResult> findAll() {
	    return taskMRR.findAll();
	}
	
	public Optional<TaskMonthlyResult> findOne(String employee_id) {
        return taskMRR.findById(employee_id);
    }
	
    public TaskMonthlyResult save(TaskMonthlyResult employee) {
        return taskMRR.save(employee);
    }

    @Transactional
    public void delete(String employee_id) {
    	taskMRR.deleteById(employee_id);
    }

	public int getSumBonusPoint(String username) {
		return taskMRR.getSumBonusPoint(username);

	}

	public List<TaskMonthlyResult> findByEmployeeId(String employeeId) {
		return taskMRR.findByEmployeeId(employeeId);
	}

	public Page<TaskMonthlyResult> findByEmployeeId(int pageNum, int pageSize, String employeeId) {
		return taskMRR.findByEmployeeId(employeeId, PageRequest.of(pageNum<=0?0:pageNum, pageSize));
	}
}

