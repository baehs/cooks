package kr.co.cooks.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.cooks.service.QNAService;
import kr.co.cooks.vo.NoticeVO;
import kr.co.cooks.vo.QNAVO;
import kr.co.cooks.vo.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QNAControl {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeControl.class);
	
	@Autowired QNAService qnaService;
	QNAVO qnaVO;
	ModelAndView mav = new ModelAndView();
	
	@RequestMapping(value = "/QNAlist.app")
	public ModelAndView list(@RequestParam(defaultValue ="1") int pageNum, HttpSession session) {
		
		ModelAndView mav= new ModelAndView();	
		
		HashMap<String,Object> hm = qnaService.qnalist(pageNum);
		
		mav.addObject("pageNum", pageNum);
		mav.addObject("qnaList", (List<QNAVO>)hm.get("qnaList"));
		mav.addObject("count",hm.get("count") );
		mav.addObject("pagecode", (String)hm.get("pagecode"));
		mav.setViewName("QNA/qna_list");
		return mav;
	}
	
	@RequestMapping(value="/QNAWriteForm.app")
	public ModelAndView QNAWriteForm(HttpSession session, HttpServletResponse res){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("QNA/qna_writeForm");
		return mav;
	}
	
	@RequestMapping(value="/QNAWrite.app")
	public String QNAWrite(@ModelAttribute QNAVO qnaVO, @RequestParam String userId, HttpSession session){
		qnaVO.setId((String)session.getAttribute("id"));
		qnaService.qnaWrite(qnaVO, userId);
		return "redirect:/QNAlist.app?pageNum="+1;
	}
	
	@RequestMapping(value="/QNAContent.app")
	public ModelAndView QNAContent(QNAVO qnaVO, HttpServletRequest req, HttpServletResponse res, HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		
		int q_Num=Integer.parseInt(req.getParameter("q_Num"));
	
		qnaVO = qnaService.qnaContent(q_Num, qnaVO);
		
		mav.addObject("qnaVO", qnaVO);
		mav.addObject("pageNum", req.getParameter("pageNum"));
		mav.setViewName("QNA/qna_content");
		return mav;
	}
	
	@RequestMapping(value="/QNAupdateForm.app")
	public ModelAndView QNAupdateForm(@RequestParam int q_Num, @RequestParam int pageNum){
		ModelAndView mav = new ModelAndView();
		qnaVO=qnaService.getQNAUpdate(q_Num);
		mav.addObject("pageNum", pageNum);
		mav.addObject("qnaVO", qnaVO);
		mav.setViewName("QNA/qna_updateForm");
		return mav;
	}
	
	@RequestMapping(value="/QNAUpdate.app")
	public ModelAndView QNAUpdate(QNAVO qnaVO, @RequestParam String pageNum){
		ModelAndView mav = new ModelAndView();
		qnaService.qnaUpdate(qnaVO);
		mav.setViewName("redirect:/QNAContent.app?q_Num="+qnaVO.getQ_Num()+"&pageNum="+pageNum);
		return mav;
	}
	
	@RequestMapping(value="QNADelete.app")
	public ModelAndView QNADelete(@RequestParam int q_Num, @RequestParam int pageNum){
		ModelAndView mav = new ModelAndView();
		qnaService.qnaDelete(q_Num);
		mav.addObject("pageNum", pageNum);
		mav.setViewName("redirect:/QNAlist.app?pageNum="+pageNum);
		return mav;
	}
	
	@RequestMapping(value = "/QNAreplyForm.app")
	public ModelAndView replyForm(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNum", req.getParameter("pageNum"));
		mav.addObject("q_dept", req.getParameter("q_dept"));
		mav.addObject("q_position", req.getParameter("q_position"));
		mav.setViewName("qna_replyForm");
		return mav;
	}
	
//	@Transactional
//	@RequestMapping(value = "/reply.daejeon")
//	public ModelAndView reply(BoardVO article, HttpSession session, @RequestParam String pageNum) {
//		ModelAndView mav = new ModelAndView();
//		article.setId((String)session.getAttribute("id"));
//		bs.reply(article);
//		mav.setViewName("redirect:/list.daejeon?pageNum="+pageNum);
//		return mav;
//	}
	
	

}
