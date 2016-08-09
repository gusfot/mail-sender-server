package org.ohjic.flower.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LayoutController {
	
	private static final Logger logger = LoggerFactory.getLogger(LayoutController.class);
	
	@RequestMapping(value = "/normal/header", method = RequestMethod.GET)
	public String header() {
		return "layouts/normal_header";
	}
	
	@RequestMapping(value = "/normal/side", method = RequestMethod.GET)
	public String side() {
		return "layouts/normal_side";
	}
	
	@RequestMapping(value = "/layout/test", method = RequestMethod.GET)
	public String layout() {
		// TODO 삭제
		return "normal/layouts/layout_body_test";
	}
	
	@RequestMapping(value = "/layout/board", method = RequestMethod.GET)
	public String board() {
		return "normal/layouts/board";
	}
	
	@RequestMapping(value = "/layout/smartEditor", method = RequestMethod.GET)
	public String smartEditor() {
		return "normal/layouts/smart_editor";
	}
	
	@RequestMapping(value = "/layout/bonbu")
	public String bonbu() {
		return "normal/layouts/bonbu";
	}
	
//	@RequestMapping(value = "/layout/send", method = RequestMethod.POST)
//	public String send(
//			@RequestParam("smarteditor")String content,
//			Model model) {
//		
//		logger.info("여기로 오나 {}", content);
//		
//		model.addAttribute("content", content);
//		return "normal/layouts/send";
//	}

}