package com.adil.social.service;


import com.adil.social.models.Story;
import com.adil.social.models.User;
import com.adil.social.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) {

        Story createdStory = new Story();
        createdStory.setUser(user);

        createdStory.setImage(story.getImage());
        createdStory.setCaption(story.getCaption());
        createdStory.setTimestamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }


    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(userId);
    }
}
