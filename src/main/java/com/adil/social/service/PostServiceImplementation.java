package com.adil.social.service;

import com.adil.social.exceptions.PostException;
import com.adil.social.exceptions.UserException;
import com.adil.social.models.Post;
import com.adil.social.repository.PostRepository;
import com.adil.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adil.social.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws PostException, UserException {

        User user = userService.findUserById(userId);
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public String DeletePost(Integer postId, Integer userId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(post.getUser().getId() != user.getId()){
            throw new PostException("You can't delete someone else's post");
        }
        postRepository.delete(post);
        return "Post deleted successfully!";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws PostException {
        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isEmpty()){
            throw new PostException("Post not found with id: " + postId);
        }
        return opt.get();

    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws PostException, UserException {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
        }
        else{
            user.getSavedPosts().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws PostException, UserException {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        //the like button on second press removes the like, so that's what we are implementing here
        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }
        else{
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}
