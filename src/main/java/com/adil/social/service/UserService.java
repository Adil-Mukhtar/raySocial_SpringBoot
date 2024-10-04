package com.adil.social.service;

import com.adil.social.exceptions.UserException;
import com.adil.social.models.User;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(Integer userId) throws UserException;
    public User findUserByEmail(String email);
    public User followUser(Integer userId1, Integer userId2) throws UserException;
    public User updateUser(User user, Integer userId) throws UserException;
    public List<User> searchUser(String query);
    public User findUserByJwt(String jwt);
}
