package com.example.repository;
import com.example.entity.Message;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    public Message findByMessageId(Integer messageId);
    public boolean existsByMessageId(Integer messageId);
    public List<Message> findAll();
    public List<Message> findByPostedBy(Integer accountId);
    public boolean existsByPostedBy(Integer postedBy);
    public void deleteByMessageId(Integer messageId);

}
