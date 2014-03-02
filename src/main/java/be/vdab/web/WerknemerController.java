package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Werknemer;
import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/werknemers")
class WerknemerController {
	private final WerknemerService werknemerService;
	private long werknemerId;
	@Autowired//met deze annotation injecteert Spring de parameter filiaalService met de bean die de interface FiliaalService implementeert: FiliaalServiceImpl
	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView("werknemers/werknemer",
				"werknemer", werknemerService.findByChefIdIsNull());
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable String id)  {
		try{
			werknemerId = Long.parseLong(id);
			ModelAndView modelAndView = new ModelAndView("werknemers/werknemer", "werknemer", werknemerService.read(werknemerId));
			return modelAndView;
		}
		catch(Exception ex){
			return new ModelAndView("redirect:/404");
		}
	}
	
	@RequestMapping(value="{id}/opslag", method = RequestMethod.GET)
	public ModelAndView getOpslag(@PathVariable long id){
		ModelAndView modelAndView = new ModelAndView("werknemers/opslag");//		ModelAndView modelAndView = new ModelAndView("werknemers/opslag");
		modelAndView.addObject("werknemer", werknemerService.read(id));
		modelAndView.addObject("opslagForm", new OpslagForm());
		return modelAndView;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public ModelAndView putOpslag(@Valid OpslagForm opslagForm, BindingResult bindingResult, @PathVariable long id){
		ModelAndView modelAndView = new ModelAndView("/werknemers/opslag");//		ModelAndView modelAndView = new ModelAndView("/werknemers/opslag");
		if(!bindingResult.hasErrors()){
			Werknemer werknemer = werknemerService.read(id);
			werknemer.opslag(opslagForm.getSalaris());//werknemer.setSalaris(werknemer.getSalaris().add(opslagForm.getSalaris()));  // werknemer.opslag(opslagForm.getSalaris())
			werknemerService.update(werknemer);
			return new ModelAndView("redirect:/werknemers/{id}");
		}
		modelAndView.addObject("werknemer", werknemerService.read(id));
		return modelAndView;
	}
	
	@InitBinder("opslagForm")
	public void initBinderWerknemer(DataBinder dataBinder){
	}	
}