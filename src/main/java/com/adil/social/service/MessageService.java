package com.adil.social.service;

import com.adil.social.exceptions.MessageException;
import com.adil.social.models.Chat;
import com.adil.social.models.Message;
import com.adil.social.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws MessageException;

    public List<Message> findChatsMessages (Integer chatId) throws MessageException;
}
