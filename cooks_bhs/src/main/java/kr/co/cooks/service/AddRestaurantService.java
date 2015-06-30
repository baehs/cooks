package kr.co.cooks.service;

import kr.co.cooks.dao.AddRestaurantDao;
import kr.co.cooks.vo.AddRestaurantVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddRestaurantService {
	@Autowired AddRestaurantDao addRestaurantdao;
	AddRestaurantVO addRestaurantVO;
	
	public void addRestaurant(AddRestaurantVO addRestaurantVO){
		addRestaurantdao.addRestaurant(addRestaurantVO);
	}

}
