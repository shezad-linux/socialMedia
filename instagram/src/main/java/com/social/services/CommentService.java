package com.social.services;

import java.util.List;

import com.social.exception.CommentException;
import com.social.exception.PostException;
import com.social.exception.UserException;
import com.social.model.Comments;

public interface CommentService {
	
	public Comments createComment(Comments comment,Integer postId,Integer userId) throws PostException, UserException;

	public Comments findCommentById(Integer commentId) throws CommentException;
	public Comments likeComment(Integer CommentId,Integer userId) throws UserException, CommentException;
	public Comments unlikeComment(Integer CommentId,Integer userId) throws UserException, CommentException;
	
	public String deleteCommentById(Integer commentId) throws CommentException;
	
	public String editComment(Comments comment, Integer commentId) throws CommentException;
	
	public List<Comments> findCommentByPostId(Integer postId)throws PostException;
}
