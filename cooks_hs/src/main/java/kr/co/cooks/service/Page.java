package kr.co.cooks.service;

import org.springframework.stereotype.Service;

@Service
public class Page {

//	private int pageNum, count, pageSize, pageBlock;
//	private int totalPage, startPage, endPage;
	private int startRow, endRow;
	private StringBuffer sb;
	
	private Page(){//싱글톤
	}
	
	private static Page page = new Page();
	
	public static Page getInstance(){
		return page;
	}

	
//	public Page(int pageNum, int count, int pageSize, int pageBlock){
//		this.pageNum = pageNum;
//		this.count = count;
//		this.pageSize = pageSize;
//		this.pageBlock = pageBlock;
//		paging();
//	}
	
	public void paging(int pageNum, int count, int pageSize, int pageBlock){
		int totalPage = (int)Math.ceil((double)count/pageSize);
		startRow = (pageNum-1)*pageSize+1;
		endRow = pageNum*pageSize;
		
		int startPage = (int)((pageNum-1)/pageBlock)*pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
						
		sb = new StringBuffer();
		if(startPage < pageBlock){
			sb.append("<img src='images/hot.gif' width='30' height='9'>");
		}else{
			sb.append("<img src='images/hot.gif' width='30' height='9'");
			sb.append(" onclick ='location.href=\"list.daejeon?pageNum=");
			sb.append(startPage - pageBlock);
			sb.append("\"' style='cursor:pointer'> ");
		}
		
		sb.append("&nbsp;|");
		
		
		for(int i=startPage; i<=endPage; i++){
			if(i==pageNum){
				sb.append("&nbsp;&nbsp;<b><font color='#91B7EF'>");
				sb.append(i);
				sb.append("</font></b>");
				
			}else{
				sb.append("&nbsp;&nbsp;<a href='list.daejeon?pageNum=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a>");
			}
		}
		
		sb.append("&nbsp;|");
		
		if(endPage<totalPage){
			sb.append("<img src='images/hot.gif' width='30' height='9'");
			sb.append(" onclick ='location.href=\"list.daejeon?pageNum=");
			sb.append(startPage+pageBlock);
			sb.append("\"' style='cursor:pointer'> ");
		}else{
			sb.append("<img src='images/hot.gif' width='30' height='9'>");
		}
	}
	
	public int getStartRow(){
		return startRow;
	}
	public int getEndRow(){
		return endRow;
	}
	public StringBuffer getSb(){
		return sb;
	}

}
