package be.vdab.web;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

class OpslagForm {
	@NotNull
	@DecimalMin("1")
	private BigDecimal salaris;
	
	OpslagForm(){
	}
	
	OpslagForm(BigDecimal salaris){
		setSalaris(salaris);
	}

	public BigDecimal getSalaris() {
		return salaris;
	}

	public void setSalaris(BigDecimal salaris) {
		this.salaris = salaris;
	}
}