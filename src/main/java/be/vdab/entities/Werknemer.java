package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

@Entity @Table(name = "werknemers" )
public class Werknemer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long id;
	
	@NotNull
	@Size(min = 1, max=50, message="{Size.tekst}")
	private String familienaam;
	
	@NotNull
	@Size(min = 1, max=50, message="{Size.tekst}")
	private String voornaam;
	
	@Size(max=50)
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "jobtitelid")
	private Jobtitel jobtitel;

	public Jobtitel getJobtitel() {
		return jobtitel;
	}

	public void setJobtitel(Jobtitel jobtitel) {
		this.jobtitel = jobtitel;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chefid")
	private Werknemer chef;

	public Werknemer getChef() {
		return chef;
	}

	public void setChef(Werknemer werknemer) {
		if (this.chef != null && this.chef.getWerknemers().contains(this)) {
			this.chef.removeWerknemer(this);
		}
		this.chef = werknemer;
		if (werknemer != null && !werknemer.getWerknemers().contains(this)) {
			werknemer.addWerknemer(this);
		}
	}	

	@OneToMany(mappedBy = "chef", fetch = FetchType.LAZY)
	private Set<Werknemer> werknemers;

	public Set<Werknemer> getWerknemers() {
		return werknemers;
	}

	public void setWerknemers(Set<Werknemer> werknemers) {
		this.werknemers = werknemers;
	}

	public void addWerknemer(Werknemer werknemer) {
		werknemers.add(werknemer);
		if (werknemer.getChef() != this) {
			werknemer.setChef(this);
		}
	}

	public void removeWerknemer(Werknemer werknemer) {
		werknemers.remove(werknemer);
		if (werknemer.getChef() == this) {
			werknemer.setChef(null);
		}
	}

	
	@NotNull
	@DecimalMin("0")
	@DecimalMax("9999999.99")
	@Digits(integer = 10, fraction = 2)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal salaris;
	
	public Werknemer() {
		jobtitel = new Jobtitel();
		werknemers = new HashSet<Werknemer>();
	}

	public Werknemer(String familienaam, String voornaam, String email, BigDecimal salaris) {
		setFamilienaam(familienaam);
		setVoornaam(voornaam);
		setEmail(email);
		setSalaris(salaris);		
	}
	
	public Werknemer(Long id, String familienaam, String voornaam, String email,
						BigDecimal salaris) {
		this(familienaam, voornaam, email, salaris);
		setId(id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getNaam() {
		return voornaam + ' ' + familienaam;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public BigDecimal getSalaris() {
		return salaris;
	}

	public void setSalaris(BigDecimal salaris) {
		this.salaris = salaris;
	}

	public void opslag(BigDecimal bedrag) {
		bedrag = bedrag.add(getSalaris());
		if (getSalaris().compareTo(bedrag) < 0) {
			BigDecimal bovenGrensSalaris = new BigDecimal("9999999.99");
			if (bedrag.compareTo(bovenGrensSalaris) <= 0){				 
				setSalaris(bedrag);
			}
			else {
				//throw new SalarisTeGrootException("Salaris te groot");
			}
		}
	}	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.toUpperCase().hashCode());
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
		Werknemer other = (Werknemer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equalsIgnoreCase(other.email))
			return false;
		return true;
	}	
	
	
	@Override
	public String toString() {
		return "Werknemer [id=" + id + ", familienaam=" + familienaam
				+ ", voornaam=" + voornaam + ", email=" + email + ", jobtitel="
				+ jobtitel + ", chef=" + chef + ", werknemers=" + werknemers
				+ ", salaris=" + salaris + "]";
	}


}
