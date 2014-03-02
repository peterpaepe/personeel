package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity @Table(name = "jobtitels" )
public class Jobtitel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@Size(min = 1, max=50, message="{Size.tekst}")
	private String naam;
	
	@OneToMany(mappedBy = "jobtitel", fetch = FetchType.LAZY)
	private Set<Werknemer> werknemers;

	public Set<Werknemer> getWerknemers() {
		return Collections.unmodifiableSet(werknemers);
	}

	public void addWerknemer(Werknemer werknemer) {
		werknemers.add(werknemer);
	}

	public void removeWerknemer(Werknemer werknemer) {
		werknemers.remove(werknemer);
	}	

	protected Jobtitel() {//default constructor voor JPA
	}
	
	public Jobtitel(String naam) {
		setNaam(naam);
	}	
	
	public Jobtitel(long id, String naam) {
		this(naam);
		setId(id);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.toUpperCase().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jobtitel other = (Jobtitel) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equalsIgnoreCase(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Jobtitel [id=" + id + ", naam=" + naam + "]";
	}
	
}
