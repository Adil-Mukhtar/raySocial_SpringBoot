package com.adil.social.controller;


import com.adil.social.models.Comment;
import com.adil.social.service.CommentService;
import com.adil.social.service.UserService;
import com.adil.social.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@PathVariable Integer postId,
                                 @RequestBody Comment comment, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment createdComment = commentService.createComment(comment,postId,user.getId());

        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@PathVariable Integer commentId,
                               @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment likedComment = commentService.likeComment(commentId,user.getId());

        return likedComment;
    }

    @GetMapping("/api/comments/{commentId}")
    public Comment findCommentById(@PathVariable Integer commentId) throws Exception {

        Comment comment = commentService.findCommentById(commentId);

        return comment;
    }

    @GetMapping("/api/comments")
    public List<Comment> findAllComments() throws Exception {
        List<Comment> comments = commentService.getAllComments();
        return comments;
    }
}
