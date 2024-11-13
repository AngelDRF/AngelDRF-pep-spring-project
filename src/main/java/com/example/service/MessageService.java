package com.example.service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import java.util.List;
import com.example.exception.ClientErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    MessageRepository messageRepository;

    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }

    public Message findMessageById(Integer messageId){
        return messageRepository.findByMessageId(messageId);
    }

    public Message addMessage(Message message){
        if(message.getMessageText() == ""){
            throw new ClientErrorException("There is no message");
        }
        if(message.getMessageText().length() > 255){
            throw new ClientErrorException("The message cant not pass over 255 characters");
        }
        if(accountRepository.existsById(message.getPostedBy()) == true){
            Message mess = messageRepository.save(message);
            return mess;
        }
        else{
            throw new ClientErrorException("Could not find postBy Id");
        }
    }

    public List<Message> getListOfMessagesForIndividualUser(Integer accountId){
        List<Message> listOfMess = messageRepository.findByPostedBy(accountId);
        return listOfMess;
    }

    @Transactional
    public Integer updateMessageById(Integer messageId, Message message){
        if(message.getMessageText() == ""){
            throw new ClientErrorException("There is no message");
        }
        if(message.getMessageText().length() > 255){
            throw new ClientErrorException("The message cant not pass over 255 characters");
        }
        if(!messageRepository.existsById(messageId)){
            throw new ClientErrorException("Could not find postBy Id");
        }
        else{
            Message mess = messageRepository.findById(messageId).
            orElseThrow(() -> new ClientErrorException("Couldnt find the ID"));
            mess.setMessageText(message.getMessageText());
            return 1;
        }
    }

    @Transactional
    public Integer deleteMessageById(Integer messageId){
        if(messageRepository.existsByMessageId(messageId)){
            messageRepository.deleteByMessageId(messageId);
            return 1;
        }
        else{
            return 0;
        }
    }

}
