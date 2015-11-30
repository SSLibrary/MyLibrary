package com.ss.academy.java.service.comment;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.academy.java.dao.comment.CommentDao;
import com.ss.academy.java.model.comment.Comment;


@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao dao;
	
	/*
	 * Find comment by comment_id
	 */
	public Comment findById(Integer comment_id){
		return dao.findById(comment_id);
	}

	/*
	 * Save comment
	 */
	public 	void saveComment(Comment comment){
		dao.saveComment(comment);
	}

	/*
	 * Show all comments
	 */
	public List<Comment> findAllComments(){
		return dao.findAllComments();
	}

	/*
	 * Delete comment
	 */
	public void deleteCommentById(Integer comment_id){
		dao.deleteCommentById(comment_id);
	
	}	
}
