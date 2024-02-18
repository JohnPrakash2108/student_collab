package com.student.collabration.StudentCollabration.service;

import com.student.collabration.StudentCollabration.dto.ChatDto;
import com.student.collabration.StudentCollabration.modal.Chat;
import com.student.collabration.StudentCollabration.modal.Users;
import com.student.collabration.StudentCollabration.repositary.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepo chatRepo;

    @Autowired
    public ChatService(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    public List<ChatDto> getAllChatsBySenderIdAndReceiverId(long senderId, long receiverId) {
        List<Chat> chats = chatRepo.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByCreatedAtDesc(senderId, receiverId,receiverId,senderId);
        return chats.stream()
                .map(chat -> ChatDto.builder()
                        .id(chat.getId())
                        .message(chat.getMessage())
                        .createdAt(chat.getCreatedAt())
                        .senderId(chat.getSender().getId())
                        .receiverId(chat.getReceiver().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public ChatDto saveChat(ChatDto chatDto) {
        Chat chat = Chat.builder()
                .message(chatDto.getMessage())
                .createdAt(LocalDateTime.now())
                .sender(Users.builder().id(chatDto.getSenderId()).build())
                .receiver(Users.builder().id(chatDto.getReceiverId()).build())
                .build();
        Chat savedChat = chatRepo.save(chat);
        return ChatDto.builder()
                .id(savedChat.getId())
                .message(savedChat.getMessage())
                .createdAt(LocalDateTime.now())
                .senderId(savedChat.getSender().getId())
                .receiverId(savedChat.getReceiver().getId())
                .build();
    }

}
