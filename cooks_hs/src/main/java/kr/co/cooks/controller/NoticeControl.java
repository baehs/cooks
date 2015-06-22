package kr.co.cooks.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.cooks.service.NoticeService;
import kr.co.cooks.vo.NoticeVO;

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
	ModelAndView mav = new ModelAndView();
	
	@RequestMapping(value="/NoticeList.app")
	public ModelAndView NoticeList(@RequestParam String pageNum){

		ModelAndView mav = new ModelAndView();
		
		HashMap<String, Object> hm = noticeService.NoticeList(pageNum);
		
		mav.addObject("pageNum", pageNum);
		mav.addObject("noticelist", (List<NoticeVO>)hm.get("noticelist"));
		mav.addObject("count", hm.get("count"));
		mav.addObject("pagecode", (String)hm.get("pagecode"));
		mav.setViewName("board_notice/notice");
		
		return mav;	
	}
	
	@RequestMapping(value="/NoticeWriteForm.app")
	public ModelAndView NoticeWriteForm(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board_notice/notice_writeForm");
		return mav;
	}
	
	@RequestMapping(value="/NoticeWrite.app")
	public String NoticeWrite(@ModelAttribute NoticeVO noticeVO){
		noticeService.noticeWrite(noticeVO);
		return "redirect:/NoticeList.app?pageNum=1";
	}
	
	@RequestMapping(value="/NoticeContent.app")
	public ModelAndView NoticeContent(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mav = new ModelAndView();
		int no_num=Integer.parseInt(req.getParameter("no_num"));
		noticeVO = noticeService.noticeContent(no_num);
		mav.addObject("noticeVO", noticeVO);
		mav.addObject("pageNum", req.getParameter("pageNum"));
		mav.setViewName("board_notice/notice_content");
		return mav;
	}
	
	@RequestMapping(value="/NoticeupdateForm.app")
	public ModelAndView NoticeupdateForm(@RequestParam int no_num, @RequestParam int pageNum){
		ModelAndView mav = new ModelAndView();
		noticeService.getNoticeUpdate(no_num);
		mav.addObject("pageNum", pageNum);
		mav.addObject("noticeVO", noticeVO);
		mav.setViewName("board_notice/notice_updateForm");
		return mav;
	}
	
	@RequestMapping(value="/NoticeUpdate.app")
	public ModelAndView NoticeUpdate(NoticeVO noticeVO, @RequestParam String pageNum){
		ModelAndView mav = new ModelAndView();
		noticeService.noticeUpdate(noticeVO);
		mav.setViewName("redirect:/NoticeContent.app?no_num="+noticeVO.getNo_Num()+"&pageNum="+pageNum);
		return mav;
	}
	
	@RequestMapping(value="/NoticeDelete.app")
	public ModelAndView NoticeDelete(@RequestParam int no_num, @RequestParam int pageNum){
		ModelAndView mav = new ModelAndView();
		noticeService.noticeDelete(no_num);
		mav.setViewName("redirect:/NoticeList.app?pageNum="+pageNum);
		return mav;
	}
}
