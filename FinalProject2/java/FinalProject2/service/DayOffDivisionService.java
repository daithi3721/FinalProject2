package FinalProject2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.DayoffDivision;
import FinalProject2.repository.DayOffDivisionRepository;

@Service
@Transactional
public class DayOffDivisionService {
	
	@Autowired
	DayOffDivisionRepository repository;
	
	public List<DayoffDivision> findAll() {
	    return repository.findAll();
	}
	
	public Optional<DayoffDivision> findOne(int dayoff_division_id) {
        return repository.findById(dayoff_division_id);
    }
	
    public DayoffDivision save(DayoffDivision dayoffDivision) {
        return repository.save(dayoffDivision);
    }

    @Transactional
    public void delete(int dayoff_division_id) {
        repository.deleteById(dayoff_division_id);
    }

}
