package kr.co.cooks.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.cooks.service.RestaurantService;
import kr.co.cooks.vo.RestaurantVO;
import kr.co.cooks.vo.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantControl {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeControl.class);
	@Autowired RestaurantService restaurantService;
	RestaurantVO restaurantVO;
	
	@RequestMapping(value="/AddRestaurantForm.app")
	public ModelAndView AddRestaurantForm(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addRestaurant/addResForm");
		return mav;
	}
	
	@RequestMapping(value="/addRestaurant.app", method=RequestMethod.POST)
	public ModelAndView AddRestaurant(@ModelAttribute RestaurantVO restaurantVO,
									 MultipartHttpServletRequest multipartReq,
									 HttpSession session){
		
		UserVO userVO=(UserVO)session.getAttribute("loginUser");
		restaurantVO.setId(userVO.getId());
		
		System.out.println(restaurantVO);
		
		
		restaurantService.addRestaurant(restaurantVO, multipartReq);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("addRestaurant/ResList");
		
		return mav;
	}
	
	@RequestMapping(value="/RestaurantList.app")
	public ModelAndView RestaurantList(){
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> hm = restaurantService.restaurantList();
		
		mav.addObject("restaurantlist", (List<RestaurantVO>)hm.get("restaurantlist"));
		mav.setViewName("addRestaurant/ResList");
		return mav;
	}
}
