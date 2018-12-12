package FinalProject2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.repository.BonusPointUseRepository;

@Service
@Transactional
public class BonusPointUseService {

	@Autowired
	BonusPointUseRepository bPointUR;

	public int getSumUsePoint(String username) {
		return bPointUR.getSumUsePoint(username);
	}
}
