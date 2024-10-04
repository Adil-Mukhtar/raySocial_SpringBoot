package com.adil.social.controller;

import com.adil.social.models.Story;
import com.adil.social.models.User;
import com.adil.social.service.StoryService;
import com.adil.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {

        User reqUser = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story, reqUser);
        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findStoryByUserId(@PathVariable Integer userId) throws Exception {

        List<Story> stories = storyService.findStoryByUserId(userId);
        return stories;
    }
}
