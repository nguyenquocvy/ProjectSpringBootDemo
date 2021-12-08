package com.example.apidemo.Springboot.repos;


import com.example.apidemo.Springboot.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepos extends JpaRepository<Account, Integer> {
    List<Account> findByUsername(String username);//viet dung ByUsername
}
