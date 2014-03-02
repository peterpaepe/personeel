package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")//De URL / stelt de “welcome page” van je website voor.
class IndexController {	
	@RequestMapping
	String index() {
		return "index";
	}
}
