package com.adil.social.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String image;

    @ManyToOne // message will be created by one user, not that one message is made by multiple users
    private User user;

    @JsonIgnore //so we don't run into recursion problem
    @ManyToOne
    private Chat chat;

    private LocalDateTime timestamp;


}
