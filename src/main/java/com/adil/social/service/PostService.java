package com.adil.social.service;

import com.adil.social.exceptions.PostException;
import com.adil.social.exceptions.UserException;
import com.adil.social.models.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Post post, Integer userId) throws PostException, UserException;
    String DeletePost(Integer postId, Integer userId) throws PostException, UserException;
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId) throws PostException;
    List<Post> findAllPost();
    Post savedPost(Integer postId, Integer userId) throws PostException, UserException;
    Post likePost(Integer postId, Integer userId) throws PostException, UserException;
}
