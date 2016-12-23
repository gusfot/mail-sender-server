/**
 * 
 */
package org.ohjic.mem.web;

import org.ohjic.mem.rest.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 메인 controller
 * @author gusfot
 *
 */
//@RequestMapping("")
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	
	@RequestMapping("/")
	public String home() {
		return "user/login";
	}
	
	@RequestMapping("/main")
	public String fileDownTest(){
		
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(){
		
		return "user/login";
	}
	
	@RequestMapping("/front")
	public String front() {

		return "front";
	}
	
	@RequestMapping("/runtime/view")
	public String runtimeView() {
		String a = "a";
		
		Integer.parseInt(a);
		
		return "runtime/view";
	}
	
	@RequestMapping("/runtime/rest")
	public String runtimeRest() {
		return "runtime/view";
	}
	
	@RequestMapping(value = "/runtime/check", method = RequestMethod.GET)
	public @ResponseBody RestResponse runtimeCheck() {
		RestResponse response = new RestResponse();
		
		String a = "a";
		
		Integer.parseInt(a);
		
		return response;
	}
}