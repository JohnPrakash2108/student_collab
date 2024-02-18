package com.student.collabration.StudentCollabration.controller;

import com.student.collabration.StudentCollabration.dto.ChatDto;
import com.student.collabration.StudentCollabration.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatDto>> getAllChatsBySenderIdAndReceiverId(
            @PathVariable long senderId,
            @PathVariable long receiverId) {
        List<ChatDto> chatDtos = chatService.getAllChatsBySenderIdAndReceiverId(senderId, receiverId);
        return new ResponseEntity<>(chatDtos, HttpStatus.OK);
    }

    @PostMapping("/save-chat")
    public ResponseEntity<ChatDto> saveChat(@RequestBody ChatDto chatDto) {
        ChatDto savedChatDto = chatService.saveChat(chatDto);
        return new ResponseEntity<>(savedChatDto, HttpStatus.CREATED);
    }
}
