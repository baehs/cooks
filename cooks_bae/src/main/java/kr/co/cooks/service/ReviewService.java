package kr.co.cooks.service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import kr.co.cooks.dao.ReviewDao;
import kr.co.cooks.vo.ReviewFileListVO;
import kr.co.cooks.vo.ReviewFileVO;
import kr.co.cooks.vo.ReviewVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Service
public class ReviewService {
	
	@Autowired ServletContext servletContext;
	@Autowired ReviewDao reviewDao ;
	
	// 마지막(끝) 페이지 구하는 메서드.
	public int getEndPageNum(int pageSize) {
		
		int totalSize = reviewDao.totalSize();
		int endPageNum = totalSize / pageSize;
		
		if( (totalSize % pageSize) > 0 )
			endPageNum++;
		
		return endPageNum;
	}
	
	// 리뷰 리스트를 가져오는 메서드
	public List<?> getReviewList(int pageNum, int pageSize) {
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("startRow", ((pageNum-1) * pageSize) );
		paramMap.put("pageSize", pageSize);
		
		return reviewDao.getReviewList(paramMap);
		
	}
	
	// 리뷰 글쓰기 메서드
	@Transactional()
	public void insertReview(ReviewVO reviewVO, MultipartHttpServletRequest multipartReq) {
		
		System.out.println("컨텐츠 내용 : " +reviewVO.getRe_Content());
		String contents = reviewVO.getRe_Content();
		String str[] = contents.split("\n");  // 줄바꿈 문자열을 찾아서 문자열을 자름.
		System.out.println("제목에 저장할 문자열 :" +str[0]);
		reviewVO.setRe_Title(str[0]); // 자른 문자열의 0번째 : 즉 첫문장을 ReviewVO re_Title에 저장.
		
		reviewDao.insertReview(reviewVO);
		
//		String realPath = "/Users/SongMJ/Documents/cooksUpload/"; // 파일이 로컬 하드에 저장되는 경로
		
		String fileUploadRealPath = servletContext.getRealPath("/fileUpload");
		
		File dir = new File(fileUploadRealPath);
		
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		
		if(multipartReq.getFiles("re_Fname") != null ) {

			List<MultipartFile> multiFile = multipartReq.getFiles("re_Fname");
			Iterator<MultipartFile> iterator = multiFile.iterator();
			
			while(iterator.hasNext()) {
				MultipartFile uploadFile = iterator.next();
				String originFileName = uploadFile.getOriginalFilename();
				String saveFileName = originFileName;
				long fileSize = uploadFile.getSize();
				
				if(!originFileName.isEmpty()) {
					if(new File(fileUploadRealPath + "/" + originFileName).exists()) {
						saveFileName = originFileName + "_" +System.currentTimeMillis();
					}
					
					ReviewFileVO reviewFileVO = new ReviewFileVO();
					reviewFileVO.setOriginFileName(originFileName);
					reviewFileVO.setSaveFileName(saveFileName);
					reviewFileVO.setFileSize(fileSize);
					
					reviewDao.insertFileUpload(reviewFileVO);
					
					try{
						uploadFile.transferTo(new File(fileUploadRealPath + "/" + saveFileName));
					} catch(Exception e) {
						e.printStackTrace();
					} // catch
				} // if
				
			} // while
		} // if
		
	} // insertReview()
	
	public ReviewFileListVO contentReview(int re_Num) {
		
		return reviewDao.contentReview(re_Num);
		
	}
	
	@Transactional()
	public void deleteReview(int re_Num) {
		
		reviewDao.deleteReviewPhoto(re_Num);
		reviewDao.deleteReview(re_Num);
	}
	
	public ReviewVO getUpdateReviewForm(int re_Num) {
		
		return reviewDao.getUpdateReviewForm(re_Num);
		
	}
	
	@Transactional()
	public void updateReview(ReviewVO reviewVO, MultipartHttpServletRequest multipartReq) {
		
		System.out.println("컨텐츠 내용 : " +reviewVO.getRe_Content());
		
		String contents = reviewVO.getRe_Content();
		reviewVO.setRe_Content(contents); // 컨텐츠 내용 수정.
		
		String str[] = contents.split("\n");  // 줄바꿈 문자열을 찾아서 문자열을 자름.
		System.out.println("제목에 저장할 문자열 :" +str[0]);
		reviewVO.setRe_Title(str[0]); // 자른 문자열의 0번째 : 즉 첫문장을 ReviewVO re_Title에 저장.
		
		reviewDao.updateReview(reviewVO);
		
//		String realPath = "/Users/SongMJ/Documents/cooksUpload/"; // 파일이 로컬 하드에 저장되는 경로
		
		String fileUploadRealPath = servletContext.getRealPath("/fileUpload");
		
		File dir = new File(fileUploadRealPath);
		
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		
		if(multipartReq.getFiles("re_Fname") != null ) {

			List<MultipartFile> multiFile = multipartReq.getFiles("re_Fname");
			Iterator<MultipartFile> iterator = multiFile.iterator();
			
			while(iterator.hasNext()) {
				MultipartFile uploadFile = iterator.next();
				String originFileName = uploadFile.getOriginalFilename();
				String saveFileName = originFileName;
				long fileSize = uploadFile.getSize();
				
				if(!originFileName.isEmpty()) {
					if(new File(fileUploadRealPath + "/" + originFileName).exists()) {
						saveFileName = originFileName + "_" +System.currentTimeMillis();
					}
					
					ReviewFileVO reviewFileVO = new ReviewFileVO();
					reviewFileVO.setOriginFileName(originFileName);
					reviewFileVO.setSaveFileName(saveFileName);
					reviewFileVO.setFileSize(fileSize);
					
					reviewDao.updateFileUpload(reviewFileVO);
					
					try{
						uploadFile.transferTo(new File(fileUploadRealPath + "/" + saveFileName));
					} catch(Exception e) {
						e.printStackTrace();
					} // catch
				} // if
				
			} // while
		} // if
		
	} // updateReview()

}
