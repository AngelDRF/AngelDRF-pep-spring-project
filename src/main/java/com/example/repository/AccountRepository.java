package com.example.repository;
import com.example.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    boolean existsByPassword(String password);
    Account findByUsername(String name);
    boolean existsByUsername(String username);

}
