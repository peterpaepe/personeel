package be.vdab.web;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.entities.Jobtitel;
import be.vdab.services.JobtitelService;
import be.vdab.web.JobtitelController;

public class JobtitelControllerTest {
	private JobtitelService jobtitelService;
	private JobtitelController jobtitelController;
	private Iterable<Jobtitel> jobtitels;
	private Jobtitel jobtitel;
	
	@Before
	public void setUp(){
		jobtitel = new Jobtitel(1L, "dummy");
		jobtitels = Collections.emptyList();
		jobtitelService = Mockito.mock(JobtitelService.class);
		Mockito.when(jobtitelService.findAll()).thenReturn(jobtitels);
		Mockito.when(jobtitelService.read(1L)).thenReturn(jobtitel);
		jobtitelController = new JobtitelController(jobtitelService);
	}
	/*
	@Test
	public void findAllActiveertJuisteView(){
		Assert.assertEquals("jobtitels/jobtitels", jobtitelController.findAll().getViewName());
	}
	
	@Test
	public void readActiveertJuisteView(){
		Assert.assertEquals("jobtitels/jobtitels", jobtitelController.read("1").getViewName());
	}*/	
	
	@Test
	public void findAllMaakRequestAttribuutJobtitels(){
		Assert.assertSame(jobtitels, jobtitelController.findAll().getModelMap().get("jobtitels"));
	}
	
	@Test
	public void readMetBestaandeIDGeeftJobtitelTerug(){
		Assert.assertSame(jobtitel, jobtitelController.read("1").getModelMap().get("jobtitel"));
	}
	
	@Test
	public void readMetOnbestaandeIDGeeftNullTerug(){
		Assert.assertNull(jobtitelController.read("999999999").getModelMap().get("jobtitel"));
	}
}
