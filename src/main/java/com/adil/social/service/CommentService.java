package com.adil.social.service;

import com.adil.social.exceptions.CommentException;
import com.adil.social.exceptions.UserException;
import com.adil.social.models.Comment;

import java.util.List;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws CommentException, UserException;

    public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException;

    public Comment findCommentById(Integer commentId) throws CommentException;

    List<Comment> getAllComments() throws CommentException;
}
