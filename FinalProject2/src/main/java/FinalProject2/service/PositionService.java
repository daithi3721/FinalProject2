package FinalProject2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.Department;
import FinalProject2.model.EmploymentInfo;
import FinalProject2.model.Position;
import FinalProject2.repository.PositionRepository;

@Service
@Transactional
public class PositionService {
	
	@Autowired
	PositionRepository repository;
	
	public List<Position> findAll() {
	    return repository.findAll();
	}
	
	//従業員が現在所属している役職のみを抽出
		public List<Position> findAll_activePosition(){
			List<Position> activePositionList = new ArrayList<Position>();
			List<Position> allPositions = repository.findAll();
			for(Position position: allPositions) {
				List<EmploymentInfo> employmentInfoList = position.getEmploymentInfoList();
				if(employmentInfoList.size() != 0) {
					activePositionList.add(position);
				}
			}
			return activePositionList;
		}

}
