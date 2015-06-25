package kr.co.cooks.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.cooks.service.NoticeService;
import kr.co.cooks.vo.NoticeVO;
import kr.co.cooks.vo.UserVO;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeControl {

	private static final Logger logger = LoggerFactory.getLogger(NoticeControl.class);
	
	@Autowired NoticeService noticeService;
	NoticeVO noticeVO;
	UserVO userVO;
	
	@RequestMapping(value="/NoticeList.app")
	public ModelAndView NoticeList(@RequestParam(defaultValue ="1") int pageNum, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> hm = noticeService.NoticeList(pageNum);
		
		if(session.getAttribute("loginUser") != null) {
			
			userVO = (UserVO)session.getAttribute("loginUser");
			mav.addObject("userLevel", userVO.getUser_Level());
		}
		mav.addObject("pageNum", pageNum);
		mav.addObject("noticelist", (List<NoticeVO>)hm.get("noticelist"));
		mav.addObject("count", hm.get("count"));
		mav.addObject("pagecode", (String)hm.get("pagecode"));
		mav.setViewName("board_notice/notice");
		
		return mav;	
	}
	
	@RequestMapping(value="/NoticeWriteForm.app")
	public ModelAndView NoticeWriteForm(HttpSession session, HttpServletResponse res){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_notice/notice_writeForm");
		return mav;
	}
	
	@RequestMapping(value="/NoticeWrite.app")
	public String NoticeWrite(@ModelAttribute NoticeVO noticeVO, @RequestParam String userId, HttpSession session){
		noticeVO.setId((String)session.getAttribute("id"));
		noticeService.noticeWrite(noticeVO, userId);
		return "redirect:/NoticeList.app?pageNum="+1;
	}
	
	@RequestMapping(value="/NoticeContent.app")
	public ModelAndView NoticeContent(NoticeVO noticeVO, HttpServletRequest req, HttpServletResponse res, HttpSession session){    
		
		//userVO = (UserVO)session.getAttribute("loginUser");
		
		ModelAndView mav = new ModelAndView();
		
		int no_Num=Integer.parseInt(req.getParameter("no_Num"));
		
		if((UserVO)session.getAttribute("loginUser") != null){
			mav.addObject("userLevel", userVO.getUser_Level());
		}
		noticeVO = noticeService.noticeContent(no_Num, noticeVO);
		
		mav.addObject("noticeVO", noticeVO);
		mav.addObject("pageNum", req.getParameter("pageNum"));
		mav.setViewName("board_notice/notice_content");
		return mav;
	}
	
	@RequestMapping(value="/noticeHit.app")
	public ModelAndView NoticeHit(int no_Num){
		ModelAndView mav = new ModelAndView();
		noticeService.noticeHit(no_Num);
		mav.setViewName("JSON");
		return mav;
	}
	
	@RequestMapping(value="/NoticeupdateForm.app")
	public ModelAndView NoticeupdateForm(@RequestParam int no_Num, @RequestParam int pageNum){
		ModelAndView mav = new ModelAndView();
		noticeVO=noticeService.getNoticeUpdate(no_Num);
		mav.addObject("pageNum", pageNum);
		mav.addObject("noticeVO", noticeVO);
		mav.setViewName("board_notice/notice_updateForm");
		return mav;
	}
	
	@RequestMapping(value="/NoticeUpdate.app")
	public ModelAndView NoticeUpdate(NoticeVO noticeVO, @RequestParam String pageNum){
		ModelAndView mav = new ModelAndView();
		noticeService.noticeUpdate(noticeVO);
		mav.setViewName("redirect:/NoticeContent.app?no_Num="+noticeVO.getNo_Num()+"&pageNum="+pageNum);
		return mav;
	}
	
	@RequestMapping(value="/NoticeDelete.app")
	public ModelAndView NoticeDelete(@RequestParam int no_Num, @RequestParam int pageNum){
		ModelAndView mav = new ModelAndView();
		noticeService.noticeDelete(no_Num);
		mav.addObject("pageNum", pageNum);
		mav.setViewName("redirect:/NoticeList.app?pageNum="+pageNum);
		return mav;
	}
}
