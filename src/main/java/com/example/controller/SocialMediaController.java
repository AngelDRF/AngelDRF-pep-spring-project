package com.example.controller;
import com.example.entity.Message;
import com.example.entity.Account;
import com.example.service.MessageService;
import com.example.service.AccountService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    MessageService messageService;
    @Autowired
    AccountService accountService;

    @PostMapping("/messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message message){
        Message mess = messageService.addMessage(message);
        return ResponseEntity.ok(mess);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        Account acc = accountService.registerUser(account);
        return ResponseEntity.ok(acc);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account){
        Account acc = accountService.loginUser(account);
        return ResponseEntity.ok(acc);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId){
        Message mess = messageService.findMessageById(messageId);
        return ResponseEntity.ok(mess);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessageById(@PathVariable("messageId") Integer messageId, @RequestBody Message message){
        Integer rInt = messageService.updateMessageById(messageId, message);
        return ResponseEntity.ok(rInt);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> allMess = messageService.findAllMessages();
        return ResponseEntity.ok(allMess);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUserAccountId(@PathVariable("accountId") Integer accountId){
        List<Message> listOfMess = messageService.getListOfMessagesForIndividualUser(accountId);
        return ResponseEntity.ok(listOfMess);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessageById(@PathVariable("messageId") Integer messageId){
        Integer i = messageService.deleteMessageById(messageId);
        if(i == 1){
            return ResponseEntity.ok(i);
        }
        else{
            return ResponseEntity.ok().build();
        }
    }

}
