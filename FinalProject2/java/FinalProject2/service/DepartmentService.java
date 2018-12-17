package FinalProject2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.Department;
import FinalProject2.model.Employee;
import FinalProject2.model.EmploymentInfo;
import FinalProject2.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {
	
	@Autowired
	DepartmentRepository repository;
	
	public List<Department> findAll() {
	    return repository.findAll();
	}
	
	//従業員が現在所属している部署のみを抽出
	public List<Department> findAll_activeDepartment(){
		List<Department> activeDepartmentList = new ArrayList<Department>();
		List<Department> allDepartments = repository.findAll();
		for(Department department: allDepartments) {
			List<EmploymentInfo> employmentInfoList = department.getEmploymentInfoList();
			if(employmentInfoList.size() != 0) {
				activeDepartmentList.add(department);
			}
		}
		return activeDepartmentList;
	}

}
