package be.vdab.services;

import be.vdab.entities.Werknemer;

public interface WerknemerService {
	Werknemer read(long id);
	void update(Werknemer werknemer);
	Iterable<Werknemer> findAll();
	Werknemer findByChefIdIsNull();//Werknemer findPresident();
}