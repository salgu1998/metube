package com.metube.comment.dao;

import java.util.List;

import com.metube.comment.vo.commentVO;

public interface commentDAO {
	
	int createComment(commentVO vo) throws Exception;

	List<commentVO> getComment(commentVO vo) throws Exception;

	int deleteComment(commentVO vo) throws Exception;
	
}
