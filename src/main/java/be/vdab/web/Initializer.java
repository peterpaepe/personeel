package be.vdab.web;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.vdab.dao.CreateDAOBeans;
import be.vdab.services.CreateServiceBeans;

public class Initializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {//Je class erft van AbstractAnnotationConfigDispatcherServletInitializer. Deze class registreert de DispatcherServlet als servlet bij de webserver.

	@Override
	protected String[] getServletMappings() {//Je associeert in deze method de DispatcherServlet met URL patronen. De webserver stuurt requests, waarvan de URL overeenkomt met één van deze URL patronen naar de DispatcherServlet
		return new String[] { "/" };//Het URL patroon / staat voor alle requests van je website. Je stuurt dus alle requests van je website naar de DispatcherServlet
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return  new Class<?>[] { CreateDAOBeans.class, CreateServiceBeans.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {//Je geeft in deze method aan welke classes de Java Config code bevatten voor je controller beans
		return new Class<?>[] { CreateControllerBeans.class };
	}
	
	@Override protected Filter[] getServletFilters() { //(1)   
		CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();   
		characterEncodingFilter.setEncoding("UTF-8"); //(2)   
		return new Filter[] { characterEncodingFilter,   new OpenEntityManagerInViewFilter(),   new HiddenHttpMethodFilter()}; 
	}

}
