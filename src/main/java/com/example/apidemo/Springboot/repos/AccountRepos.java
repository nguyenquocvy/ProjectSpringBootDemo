package com.example.apidemo.Springboot.repos;


import com.example.apidemo.Springboot.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepos extends JpaRepository<Account, Integer> {
    //List<Account> findByUsername(String username);//viet dung ByUsername
    //hoacsu dung
    @Query(value = "select u.* from username as u where u.name = :username", nativeQuery = true)
    List<Account> findByUsername(@Param("username") String username);

}
