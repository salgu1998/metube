package com.metube.user.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.metube.user.vo.userVO;

public interface userDAO {
	
	List<userVO> getUserList(userVO vo) throws Exception;
	
	userVO loginCheck(userVO vo) throws Exception;
	
	userVO noPwUser(userVO vo) throws Exception;
		
	int signUp(userVO vo) throws Exception;

	List<userVO> nameGetUser(userVO vo) throws Exception;

	int userLock(userVO vo) throws Exception;

	int withdrawal(userVO vo) throws Exception;

	userVO getUser(userVO vo) throws Exception;

}
