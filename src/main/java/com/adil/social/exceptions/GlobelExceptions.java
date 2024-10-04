package com.adil.social.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice //this annotation tells spring security that all our exceptions will be handled here
public class GlobelExceptions {



    // --------------------------------------------------------------------------------


    // for chats
    @ExceptionHandler({ChatException.class})
    public ResponseEntity<ErrorDetails> chatExceptionHandler(ChatException ce, WebRequest req) {

        ErrorDetails error = new ErrorDetails(ce.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    //for comments
    @ExceptionHandler({CommentException.class})
    public ResponseEntity<ErrorDetails> commentExceptionHandler(CommentException ce, WebRequest req) {

        ErrorDetails error = new ErrorDetails(ce.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    //for message
    @ExceptionHandler({MessageException.class})
    public ResponseEntity<ErrorDetails> messageExceptionHandler(MessageException me, WebRequest req) {

        ErrorDetails error = new ErrorDetails(me.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    //for posts
    @ExceptionHandler({PostException.class})
    public ResponseEntity<ErrorDetails> postExceptionHandler(PostException pe, WebRequest req) {

        ErrorDetails error = new ErrorDetails(pe.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    //for reels
    @ExceptionHandler({ReelsException.class})
    public ResponseEntity<ErrorDetails> reelsExceptionHandler(ReelsException re, WebRequest req) {

        ErrorDetails error = new ErrorDetails(re.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    //for story
    @ExceptionHandler({StoryException.class})
    public ResponseEntity<ErrorDetails> storyExceptionHandler(StoryException se, WebRequest req) {

        ErrorDetails error = new ErrorDetails(se.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    // for users
    @ExceptionHandler({UserException.class})
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest req) {

        ErrorDetails error = new ErrorDetails(ue.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }

    // --------------------------------------------------------------------------------


    //we write the class for which we are making the handler ... here its Exception class
    //base exception
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ue, WebRequest req) {

        ErrorDetails error = new ErrorDetails(ue.getMessage(),
                req.getDescription(false), LocalDateTime.now()); //description is the text wall that we don't want
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
    }
}
