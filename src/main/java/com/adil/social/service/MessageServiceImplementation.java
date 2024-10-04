package com.adil.social.service;


import com.adil.social.models.Chat;
import com.adil.social.models.Message;
import com.adil.social.models.User;
import com.adil.social.repository.ChatRepository;
import com.adil.social.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;



    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception{



        Chat chat = chatService.findChatById(chatId);
        Message message = new Message();

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setUser(user);
        message.setImage(req.getImage());
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        //this method will create new chat and also update chat if chat already exists if not it creates a new chat
        chat.getMessages().add(savedMessage); // to add messages to the chat
        chatRepository.save(chat); // to save the chats
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception{

        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
