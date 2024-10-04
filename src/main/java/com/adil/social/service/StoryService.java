package com.adil.social.service;

import com.adil.social.models.Story;
import com.adil.social.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, User user);
    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
