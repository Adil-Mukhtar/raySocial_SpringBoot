package com.adil.social.controller;

import com.adil.social.exceptions.MessageException;
import com.adil.social.models.Message;
import com.adil.social.models.User;
import com.adil.social.service.MessageService;
import com.adil.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;




    @PostMapping("/api/message/{chatId}")
    public Message createMessage(@RequestBody Message message,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws MessageException {

        User reqUser = userService.findUserByJwt(jwt);
        Message createdMessage = messageService.createMessage(reqUser, chatId, message);
        return createdMessage;
    }

    @GetMapping("/api/message/{chatId}")
    public List<Message> findChatMessages(@PathVariable Integer chatId) throws MessageException {

        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }
}
