package com.adil.social.service;

import com.adil.social.models.Comment;

import java.util.List;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    public Comment likeComment(Integer commentId, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;

    List<Comment> getAllComments() throws Exception;
}
