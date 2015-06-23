package kr.co.cooks.controller;

import java.util.List;

import kr.co.cooks.service.ReviewService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ReviewControl {
	
	@Autowired ReviewService reviewService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewControl.class);
	static final int PAGE_DEFAULT_SIZE = 5;
	
	@RequestMapping(value = "/reviewList.app")
	public ModelAndView reviewList(@RequestParam(defaultValue = "1") int pageNo,
									@RequestParam(defaultValue = "5") int pageSize) {
		
		if(pageSize <= 0)
			pageSize = PAGE_DEFAULT_SIZE;
		
		int endPageNo = reviewService.getEndPageNo(pageSize);
		
		if(pageNo <= 0 ) pageNo = 1;
		if(pageNo > endPageNo) pageNo = endPageNo;
		
		List<?> reviewList = reviewService.getReviewList(pageNo, pageSize);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("status", "success");
		mav.addObject("currentPageNo", pageNo);
		mav.addObject("endPageNo", endPageNo);
		mav.addObject("reviewList", reviewList);
		mav.setViewName("JSON");
		
		return mav;
	}
	

}
