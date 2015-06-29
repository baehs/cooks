package kr.co.cooks.controller;

import javax.servlet.http.HttpSession;

import kr.co.cooks.service.AddRestaurantService;
import kr.co.cooks.vo.AddRestaurantVO;
import kr.co.cooks.vo.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddRestaurantControl {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeControl.class);
	@Autowired AddRestaurantService addRestaurantService;
	AddRestaurantVO addRestaurantVO;
	
	@RequestMapping(value="/AddRestaurantForm.app")
	public ModelAndView AddRestaurantForm(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addRestaurant/addResForm");
		return mav;
	}
	
	@RequestMapping(value="/addRestaurant.app")
	public ModelAndView AddRestaurant(HttpSession session,
									 @ModelAttribute AddRestaurantVO addRestaurantVO){
		
		UserVO userVO=(UserVO)session.getAttribute("loginUser");
		addRestaurantVO.setId(userVO.getId());
		
		addRestaurantService.addRestaurant(addRestaurantVO);
		
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}

}
