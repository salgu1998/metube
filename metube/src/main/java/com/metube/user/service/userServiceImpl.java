package com.metube.user.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metube.user.dao.userDAO;
import com.metube.user.vo.userVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("UserService")
public class userServiceImpl implements userService{

	@Resource(name = "userDAO")
	private userDAO user_DAO;
	
	/**
	 * Test Service
	 */
	@Override
	public int onePlus(int num) throws Exception {
		return num + 1;
	}
	
	@Override
	public List<userVO> getUserList(userVO vo) throws Exception {
		System.out.println("userService - getUserList");
		return user_DAO.getUserList(vo);
	}

	@Override
	public boolean loginCheck(userVO vo, HttpSession session) throws Exception {
		System.out.println("userService - loginCheck");
		boolean result = user_DAO.loginCheck(vo);
		if(result) {
			userVO vo2 = noPwUser(vo);
			
			//session 등록
			session.setAttribute("email", vo2.getEmail());
			session.setAttribute("name", vo2.getName());
		}
		
		return result;
	}

	@Override
	public userVO noPwUser(userVO vo) throws Exception {
		return user_DAO.noPwUser(vo);
	}
	
	@Override
	public void logout(HttpSession session) throws Exception {
		//세션 변수 개별 삭제
		//session.removeAttribute("email");
		
		//세션 전체 초기화
		session.invalidate();
	}

	@Override
	public int signUp(userVO vo) throws Exception {
		int result = user_DAO.signUp(vo);
		return result;
	}

	
}
