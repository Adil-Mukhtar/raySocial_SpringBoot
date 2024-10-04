package com.adil.social.service;

import com.adil.social.exceptions.ReelsException;
import com.adil.social.exceptions.UserException;
import com.adil.social.models.Reels;
import com.adil.social.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReels(Integer userId) throws ReelsException, UserException;
}
