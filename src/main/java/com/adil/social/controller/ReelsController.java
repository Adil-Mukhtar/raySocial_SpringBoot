package com.adil.social.controller;


import com.adil.social.exceptions.ReelsException;
import com.adil.social.exceptions.UserException;
import com.adil.social.models.Reels;
import com.adil.social.models.User;
import com.adil.social.service.ReelsService;
import com.adil.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {

        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reels, reqUser);
        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels() {

        List<Reels> reels = reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws ReelsException, UserException {

        List<Reels> reels = reelsService.findUsersReels(userId);
        return reels;
    }
}
