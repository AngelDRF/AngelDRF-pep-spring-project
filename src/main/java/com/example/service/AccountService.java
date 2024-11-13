package com.example.service;
import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.UnathorizedExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account loginUser(Account account){
        if((accountRepository.existsByUsername(account.getUsername())) == false || 
        (accountRepository.existsByPassword(account.getPassword())) == false){
            throw new UnathorizedExeption("INVALID");
        }
        else{
            Account acc = accountRepository.findByUsername(account.getUsername());
            return acc;
        }
    }

    public Account registerUser(Account account){
        if(accountRepository.existsByUsername(account.getUsername()) == true){
            throw new IllegalArgumentException();
        }
        else{
            Account acc = accountRepository.save(account);
            return acc;
        }
    }

}
