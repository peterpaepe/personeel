package be.vdab.services;

import be.vdab.entities.Jobtitel;

public interface JobtitelService {
	Jobtitel read(long id);
	Iterable<Jobtitel> findAll();
}
