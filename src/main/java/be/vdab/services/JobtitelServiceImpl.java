package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.JobtitelDAO;
import be.vdab.entities.Jobtitel;
import be.vdab.exceptions.JobtitelNietGevondenException;

@Service// met deze annotation maak je een Spring bean van deze class
@Transactional(readOnly = true)//@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class JobtitelServiceImpl implements JobtitelService {
	private final JobtitelDAO jobtitelDAO;
	
	protected JobtitelServiceImpl() {
		this.jobtitelDAO = null;
	}

	@Autowired
	public JobtitelServiceImpl(JobtitelDAO jobtitelDAO) {
		this.jobtitelDAO = jobtitelDAO;
	}

	@Override
	public Jobtitel read(long id) {
		Jobtitel jobtitel = jobtitelDAO.findOne(id);
		if (jobtitel == null){
			throw new JobtitelNietGevondenException();
		}
		return jobtitel;
	}
	
	@Override
	public Iterable<Jobtitel> findAll() {
		return jobtitelDAO.findAll(new Sort("naam"));
	}

}
