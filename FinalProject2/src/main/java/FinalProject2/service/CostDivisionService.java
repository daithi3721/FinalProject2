package FinalProject2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.CostDivision;
import FinalProject2.repository.CostDivisionRepository;

@Service
@Transactional
public class CostDivisionService {
	
	@Autowired
	CostDivisionRepository repository;
	
	public List<CostDivision> findAll() {
	    return repository.findAll();
	}
	
	public Optional<CostDivision> findOne(int cost_division_id) {
        return repository.findById(cost_division_id);
    }
	
    public CostDivision save(CostDivision costDivision) {
        return repository.save(costDivision);
    }

    @Transactional
    public void delete(int cost_division_id) {
        repository.deleteById(cost_division_id);
    }

}
