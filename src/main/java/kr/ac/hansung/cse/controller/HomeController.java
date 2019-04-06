package kr.ac.hansung.cse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller // Controller로 등록이 되어있어서 Bean으로 등록이 된다.
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) // GET METHOD로  /로  오게되면 아래 코드가 불려지고 home.jsp로  넘어간다.
	
	public String home() {
		
		return "home"; //여기에 home과 tiles.xml에 있는 definition name="home"이 일치해야한다
	}
	
}
