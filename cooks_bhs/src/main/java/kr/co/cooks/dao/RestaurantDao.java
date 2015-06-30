package kr.co.cooks.dao;

import java.util.List;

import kr.co.cooks.vo.RestaurantFileVO;
import kr.co.cooks.vo.RestaurantVO;

public interface RestaurantDao {
	public void addRestaurant(RestaurantVO addRestaurantVO);
	public void addFileUpload(RestaurantFileVO restaurantFileVO);
	public int getRestaurantCount();
	public List<RestaurantVO> getRestaurant();

}
