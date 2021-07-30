package com.metube.post.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.metube.post.service.postService;
import com.metube.post.vo.postVO;
import com.metube.sub.service.subService;
import com.metube.sub.vo.subVO;
import com.metube.upload.service.uploadService;
import com.metube.upload.vo.uploadVO;
import com.metube.comment.service.commentService;
import com.metube.comment.vo.commentVO;

@Controller
@RequestMapping(value="/post")
public class postController {

	@Resource(name = "PostService")
	private postService postService;
	
	@Resource(name = "CommentService")
	private commentService commentService;
	
	@Resource(name = "SubService")
	private subService subService;
	
	@Resource(name = "UploadService")
	private uploadService uploadService;
	
	@Resource(name="uploadPath")
    String uploadPath;
	
	/**
	 * 게시물 생성 페이지로 간다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goCreate")
	public ModelAndView goCreatePost() throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("createPost");
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 게시물을 작성한다
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public boolean createPost(
			MultipartHttpServletRequest request
	) throws Exception {
		try {		
			postVO pvo = new postVO();
			pvo.setTitle(request.getParameter("title"));
			pvo.setDescription(request.getParameter("description"));
			pvo.setKind(Integer.parseInt(request.getParameter("kind")));
			pvo.setUser_pk(Integer.parseInt(request.getParameter("user_pk")));
			int create_result = postService.createPost(pvo);
			
	        UUID uuid = UUID.randomUUID();

			//파일
	        //image
	        String image_fileName = (request.getFile("image").getOriginalFilename());
	    	System.out.println("image_fileName :" + image_fileName);
	    	
	    	String image_ext = image_fileName.substring(image_fileName.lastIndexOf(".") + 1);
	    	System.out.println("image_ext :" + image_ext);
	    	
	        String image_savedName =  uuid.toString() + "_" + image_fileName;
	        File image_target = new File(uploadPath, image_savedName);
	        System.out.println("image :" + image_target);
	        
	        //video
			String video_fileName = (request.getFile("video").getOriginalFilename());
			
			String video_ext = video_fileName.substring(video_fileName.lastIndexOf(".") + 1);
			
	        String video_savedName = uuid.toString() + "_" + video_fileName;
	        File video_target = new File(uploadPath, video_savedName);
	        
	        //경로 생성
	        if (!new File(uploadPath).exists()) {
	            new File(uploadPath).mkdirs();
	        }
	        //파일 복사
	        try {
	            FileCopyUtils.copy(request.getFile("image").getBytes(), image_target);
	            FileCopyUtils.copy(request.getFile("video").getBytes(), video_target);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
			
	        uploadVO uvo = new uploadVO();
			uvo.setPost_pk(create_result);
			uvo.setImg_name(image_savedName);
			uvo.setImg_ext(image_ext);
			uvo.setVideo_name(video_savedName);
			uvo.setVideo_ext(video_ext);

			uploadService.saveDataURL(uvo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
   
	/**
	 * 게시물 목록을 가져온다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView getPostList() throws Exception {
		try {
			postVO vo = new postVO();
			ModelAndView mv = new ModelAndView();
			mv.setViewName("main");
			mv.addObject("postList", postService.getPostList(vo));
			mv.addObject("noticeList", postService.getNoticeList(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 게시물을 조회한다.
	 * @param title
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search/{title}")
	public ModelAndView searchPostList(
			@PathVariable("title") String title
	) throws Exception {
		try {
			postVO vo = new postVO();
			vo.setTitle(title);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("main");
			mv.addObject("postList", postService.searchPostList(vo));
			mv.addObject("noticeList", postService.searchNoticeList(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 공지사항을 가져온다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notice/list")
	public ModelAndView getNoticeList() throws Exception {
		try {
			postVO vo = new postVO();
			ModelAndView mv = new ModelAndView();
			mv.setViewName("noticePost");
			mv.addObject("noticeList", postService.getNoticeList(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 운영자 게시물 삭제 처리
	 * @param post_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/admin/{post_pk}", method = RequestMethod.DELETE)
	public boolean is_deletePost(
			@PathVariable("post_pk") int post_pk
	) throws Exception {
		postVO vo = new postVO();
		vo.setPk(post_pk);
		try {		
			postService.is_deletePost(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 게시물을 삭제한다.
	 * @param session
	 * @param post_pk
	 * @param user_pk
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/{post_pk}", method = RequestMethod.DELETE)
	public boolean deletePost(
		@PathVariable("post_pk") int post_pk
	) throws Exception {
		postVO vo = new postVO();
		vo.setPk(post_pk);
		try {		
			postService.deletePost(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 상세 게시물을 불러온다.
	 * @param post_pk
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail/{post_pk}", method = RequestMethod.GET)
	public ModelAndView detailPost(
			@PathVariable("post_pk") int post_pk, HttpSession session
	) throws Exception {
		try {
			postVO vo = new postVO();
			vo.setPk(post_pk);
			
			commentVO comment_vo = new commentVO();
			comment_vo.setPost_pk(post_pk);
			
			postVO post_result = postService.detailPost(vo);
			
			subVO subvo = new subVO();
			subvo.setP_user_pk(post_result.getUser_pk());
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("post", post_result);
			mv.addObject("comment", commentService.getComment(comment_vo));
			mv.addObject("sub", subService.getSub(post_result.getUser_pk(), session));
			mv.addObject("sub_count", subService.sub_count(subvo));

			mv.setViewName("detailPost");
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 게시물 수정 페이지로 간다.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goModify/{post_pk}")
	public ModelAndView goModifyPost(@PathVariable("post_pk") int post_pk) throws Exception {
		try {
			ModelAndView mv = new ModelAndView();
			postVO vo = new postVO();
			vo.setPk(post_pk);
			
			mv.setViewName("modifyPost");
			mv.addObject("post", postService.detailPost(vo));
			return mv;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 게시물을 수정한다
	 * @param vo: pk 수정할 게시물,
	 * 			수정데이터: title, description
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/modify", method = RequestMethod.PUT)
	public boolean modifyPost(@RequestBody postVO vo) throws Exception {
		try {		
			//현재 시간 구하기
	        ZoneId zid = ZoneId.systemDefault();
			ZonedDateTime datetime = ZonedDateTime.now(zid);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" );
	        String timeStamp = datetime.format(formatter);
	        vo.setUpdate_at(timeStamp);
	    
			postService.modifyPost(vo);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
