package org.ohjic.flower.web;


import org.ohjic.flower.common.ArticlePaging;
import org.ohjic.flower.model.Article;
import org.ohjic.flower.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ArticleController {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value = "/article/board", method=RequestMethod.GET)
	public String board(@RequestParam(value="pageNo",defaultValue="1")int pageNo,
					   @RequestParam(value="keyList",defaultValue ="title")String keyList,
					   @RequestParam(value="keyword",defaultValue ="")String keyword,
					   Model model) {
		logger.info("pageNo 체크 {}", pageNo);
		logger.info("keyList 체크 {}", keyList);
		logger.info("keyword 체크 {}", keyword);
		
		ArticlePaging<Article> articlePaging = articleService.getList(pageNo, keyList, keyword);
		String jsonPaging = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonPaging = mapper.writeValueAsString(articlePaging); // articlePaging 리스트를 json으로 변환
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		
		model.addAttribute("paging", articlePaging);
		model.addAttribute("jsonPaging", jsonPaging);
		model.addAttribute("keyList", keyList);
		model.addAttribute("keyword", keyword);
		
		return "normal/layouts/board";
	}
}