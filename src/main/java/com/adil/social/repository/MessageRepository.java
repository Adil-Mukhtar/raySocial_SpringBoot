package com.adil.social.repository;

import com.adil.social.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    //when we will click chat, we want to get all the messages of that chat .. so we use chatId
    public List<Message> findByChatId(Integer chatId);
}
