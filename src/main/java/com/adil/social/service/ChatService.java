package com.adil.social.service;

import com.adil.social.exceptions.ChatException;
import com.adil.social.models.Chat;
import com.adil.social.models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2) ;

    public Chat findChatById(Integer chatId) throws ChatException;

    public List<Chat> findUsersChats(Integer userId);
}
