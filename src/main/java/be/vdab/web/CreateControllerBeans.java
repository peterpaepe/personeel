package be.vdab.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("be.vdab.web")//Je definieert controller beans met @Controller. Je geeft aan dat Spring één bean moet maken voor elke class met de annotation @Controller in de package be.vdab.controllers.
public class CreateControllerBeans extends WebMvcConfigurationSupport {//De class WebMvcConfigurationSupport initialiseert Spring MVC.
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {// (1)Je definieert in de method addResourceHandlers welke requests de DispatcherServlet niet naar controller beans moet sturen.
		registry.addResourceHandler("/images/**").addResourceLocations(
				"/images/");// (2)Je voert op de parameter registry de method addResourceHandler uit. Je geeft als parameter het URL patroon van static resources mee. /images/** betekent alle URL’s die beginnen met /images/. De twee sterretjes betekenen dat de URL na /images/ nog één of meerdere / tekens kan bevatten (bvb. de URL /images/products/12.jpg). Als je maar één sterretje gebruikt, past het patroon bij /images/12.jpg, maar niet bij /images/products/12.jpg. Je voert op het resultaat van de method addResourceHandler de method addResourceLocations uit. Je geeft als parameter de locatie mee van de static resources. Dit is een locatie in het web gedeelte van je project (de folder webapp).
		registry.addResourceHandler("/styles/**").addResourceLocations(
				"/styles/");
		registry.addResourceHandler("/scripts/**").addResourceLocations(
				"/scripts/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {//(1)Je maakt een bean van de class InternalResourceViewResolver. Spring gebruikt zo’n bean bij het activeren van een JSP
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/JSP/");// (2)De bean concateneert de inhoud van de property prefix vóór de String die een @RequestMapping method teruggeeft.
		viewResolver.setSuffix(".jsp");// (3)De bean concateneert de inhoud van de property suffix ná de String die een @RequestMapping method teruggeeft. Als een @RequestMapping method filialen/toevoegen teruggeeft, maakt de bean hiervan /WEB-INF/JSP/filialen/toevoegen.jsp
		return viewResolver;
	}
	
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/info").setViewName("info");//(1)Spring geeft requests naar de URL /info rechtstreeks door naar /WEB-INF/JSP/info.jsp.
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource // (1)Een object dat de interface MessageSource implementeert leest teksten uit resource bundles. De class ReloadableResourceBundleMessageSource implementeert deze interface
		= new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/resourceBundles/teksten"); // (2)Je vult basename met de locatie en de base name van de properties bestanden.
		messageSource.setFallbackToSystemLocale(false); // (3)Default staat de property fallbackToSystemLocale op true. Als Spring een tekst niet vindt in het properties bestand met taalcode en landcode van de gebruiker en ook niet in het properties bestand met taalcode  van de gebruiker, zoekt Spring de tekst dan in het properties bestand  met de taalcode van het besturingssysteem.  Dit is vervelend: je website gedraagt zich anders naargelang je hem installeert op een besturingssysteem met een andere taal. Je plaatst daarom de property fallbackToSystemLocale op false. Als Spring nu een tekst niet vindt in het properties bestand met taalcode en landcode van de gebruiker en ook niet in het properties bestand met taalcode van de gebruiker, zoekt Spring de tekst in het properties bestand zonder taalcode. 
		return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean(){
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;
	}
	
	@Override// importeer Validator uit org.springframework.validation
	public Validator getValidator() {
		return new SpringValidatorAdapter(localValidatorFactoryBean().getValidator());//(1)Spring MVC maakt standaard zelf een Validator bean. Je geeft hier aan dat Spring MVBC niet zelf zo’n bean moet maken, maar de bean moet gebruiken die je maakte in de method validatorFactory
	}
	
}
