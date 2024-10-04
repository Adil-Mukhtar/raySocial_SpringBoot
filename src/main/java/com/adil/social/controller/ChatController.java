package com.adil.social.controller;


import com.adil.social.exceptions.ChatException;
import com.adil.social.exceptions.UserException;
import com.adil.social.models.Chat;
import com.adil.social.models.User;
import com.adil.social.request.CreateChatRequest;
import com.adil.social.service.ChatService;
import com.adil.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequest req) throws ChatException, UserException {

        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);

        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {

        User reqUser = userService.findUserByJwt(jwt);
        List<Chat> chats = chatService.findUsersChats(reqUser.getId());
        return chats;
    }

    @GetMapping("/api/chats/{chatId}")
    public Chat findChatByChatId(@PathVariable Integer chatId) throws ChatException {

        Chat chat = chatService.findChatById(chatId);
        return chat;
    }
}
