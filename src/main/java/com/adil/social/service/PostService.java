package com.adil.social.service;

import com.adil.social.models.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Post post, Integer userId) throws Exception;
    String DeletePost(Integer postId, Integer userId)throws Exception;
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId) throws Exception;
    List<Post> findAllPost();
    Post savedPost(Integer postId, Integer userId) throws Exception;
    Post likePost(Integer postId, Integer userId) throws Exception;
}
