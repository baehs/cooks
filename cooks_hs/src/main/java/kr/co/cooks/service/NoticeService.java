package kr.co.cooks.service;

import java.util.HashMap;
import java.util.List;

import kr.co.cooks.dao.NoticeDao;
import kr.co.cooks.vo.NoticeVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Service
public class NoticeService {
	@Autowired NoticeDao noticeDao;
	@Autowired Page page;
	NoticeVO noticeVO;
	ModelAndView mav = new ModelAndView();
	
	List<NoticeVO> noticelist;
	
	public HashMap<String, Object> NoticeList(String pageNum){
		
		HashMap<String, Object> hm = new HashMap<>();
		int count=0;
		int pageSize=10;
		int pageBlock=10;
		
		if(pageNum==null){
			pageNum="1";
		}
		
		count=noticeDao.getNoticeArticleCount();
		
		page.paging(Integer.parseInt(pageNum), count, pageSize, pageBlock);
		
		if(count>0){
			HashMap<String, Integer> pageing = new HashMap<>();
			pageing.put("startRow", page.getStartRow());
			pageing.put("endRow", page.getEndRow());
			noticelist=noticeDao.getNoticeArticles(pageing);
		} else {
			noticelist=null;
		}
		
		hm.put("count", count);
		hm.put("noticelist", noticelist);
		hm.put("pagecode", page.getSb().toString());
		
		return hm;
	}
	
	public void noticeWrite(NoticeVO noticeVO){
		noticeDao.noticeWrite(noticeVO);
	}
	
	public NoticeVO noticeContent(int no_num){
		return noticeDao.noticeContent(no_num);
	}
	
	public NoticeVO getNoticeUpdate(int no_num){
		return noticeDao.getNoticeUpdate(no_num);
	}
	
	public void noticeUpdate(NoticeVO noticeVO){
		noticeDao.noticeUpdate(noticeVO);
	}
	
	public void noticeDelete(int no_num){
		noticeDao.noticeDelete(no_num);
	}
	
	
	
}
