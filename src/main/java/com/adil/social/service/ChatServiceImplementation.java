package com.adil.social.service;

import com.adil.social.exceptions.ChatException;
import com.adil.social.models.Chat;
import com.adil.social.models.User;
import com.adil.social.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserService userService;

    @Override
    public Chat createChat(User reqUser, User user2)  {

        Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);

        if(isExist != null) { //if new chat exists, don't create new chat
            return isExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }


    @Override
    public Chat findChatById(Integer chatId) throws ChatException {

        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isEmpty()){
            throw new ChatException("chat not found with id: "+ chatId);
        }

        return opt.get();
    }


    @Override
    public List<Chat> findUsersChats(Integer userId) {

        return chatRepository.findByUsersId(userId);
    }
}
