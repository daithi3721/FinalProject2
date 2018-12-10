package FinalProject2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
