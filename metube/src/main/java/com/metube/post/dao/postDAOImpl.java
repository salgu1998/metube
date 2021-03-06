package com.metube.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metube.common.dao.mysqlAbstractMapper;
import com.metube.post.vo.postVO;

@Repository("PostDAO")
public class postDAOImpl extends mysqlAbstractMapper implements postDAO {

	@Override
	public postVO selectOne(postVO vo) throws Exception {
		return selectOne("PostDAO.selectPost", vo);
	}
	
	@Override
	public postVO detailNotice(postVO vo) throws Exception {
		return selectOne("PostDAO.selectNotice", vo);
	}
	
	@Override
	public List<postVO> getPostList(postVO vo) throws Exception {
		return selectList("PostDAO.freeList", vo);
	}

	@Override
	public int createPost(postVO vo) throws Exception {
		insert("PostDAO.createPost", vo);
		return vo.getPk();
	}

	@Override
	public int deletePost(postVO vo) throws Exception {
		return delete("PostDAO.deletePost", vo);
	}

	@Override
	public int update_view(postVO vo) throws Exception {
		return update("PostDAO.update_view", vo);
	}

	@Override
	public int is_deletePost(postVO vo) throws Exception {
		System.out.println(vo.getPk());
		return update("PostDAO.is_delete", vo);
	}

	@Override
	public List<postVO> getNoticeList(postVO vo) throws Exception {
		return selectList("PostDAO.noticeList", vo);
	}

	@Override
	public int modifyPost(postVO vo) throws Exception {
		return update("PostDAO.modifyPost", vo);
	}

	@Override
	public List<postVO> searchPostList(postVO vo) throws Exception {
		return selectList("PostDAO.searchPostList", vo);
	}

	@Override
	public List<postVO> searchNoticeList(postVO vo) throws Exception {
		return selectList("PostDAO.searchNoticeList", vo);
	}

	@Override
	public List<postVO> userPostList(postVO pvo) throws Exception {
		return selectList("PostDAO.userPostList", pvo);
	}

	@Override
	public List<postVO> userCommunityList(postVO pvo) throws Exception {
		return selectList("PostDAO.userCommunityList", pvo);
	}

	@Override
	public int createNotice(postVO vo) throws Exception {
		return insert("PostDAO.createNotice", vo);
	}
}

