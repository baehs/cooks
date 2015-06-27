package kr.co.cooks.service;

import java.util.HashMap;
import java.util.List;

import kr.co.cooks.dao.QNADao;
import kr.co.cooks.vo.NoticeVO;
import kr.co.cooks.vo.QNAVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class QNAService {
	@Autowired QNADao qnaDao;
	@Autowired Page page;
	QNAVO qnaVO;
	List<QNAVO> qnaList;
	
	public HashMap<String,Object> qnalist(int pageNum) {
		HashMap<String,Object> hm = new HashMap<>();
		int count=0;
		int pageSize=10;
	    int pageBlock=10;  	
		
	    if(pageNum <= 0){
			pageNum=1;
		}		
		
		count=qnaDao.getQNACount();		
		 
		page.paging(pageNum, count, pageSize, pageBlock);
		
	   if (count > 0) { 
		   HashMap<String, Integer> pageing = new HashMap<>();
			pageing.put("startRow", page.getStartRow());
			pageing.put("endRow", page.getEndRow());
        	qnaList=qnaDao.getQNA(pageing);        	
       } else {
            qnaList =null;  
       }     
	   
	   hm.put("count", count);
	   hm.put("qnaList", qnaList);
	   hm.put("pagecode", page.getSb().toString());
	   
	   return hm;
	}
	
	public void qnaWrite(QNAVO qnaVO){
		
		qnaDao.qnaWrite(qnaVO);
	}
	
	public QNAVO qnaContent(int q_Num){
		return qnaDao.qnaContent(q_Num);
	}
	

	public QNAVO getQNAUpdate(int q_Num){
		return qnaDao.getQNAUpdate(q_Num);
	}
	
	public void qnaUpdate(QNAVO qnaVO){
		qnaDao.qnaUpdate(qnaVO);
	}
	
	public void qnaDelete(int q_Num){
		qnaDao.qnaDelete(q_Num);
	}
	
	public void qnaReply(QNAVO qnaVO){
		qnaDao.qnaReply(qnaVO);
	}
	
	
}
