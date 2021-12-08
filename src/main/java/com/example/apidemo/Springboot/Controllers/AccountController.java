package com.example.apidemo.Springboot.Controllers;

import com.example.apidemo.Springboot.models.Account;
import com.example.apidemo.Springboot.models.Response;
import com.example.apidemo.Springboot.repos.AccountRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    @Autowired
    private AccountRepos repository;

    // hien thi tat ca account
    @GetMapping("")
    List<Account> getAllAccounts() {

        return repository.findAll();
    }

    //get detail by id
    @GetMapping("/{id}")
    Response findByIdd(@PathVariable Integer id) {
        Optional<Account> dataAccount = repository.findById(id);
        if (dataAccount.isPresent()) {
            return new Response("Ok", "Truy Van Thanh Cong", dataAccount);

        } else {
            return new Response("False", "Truy Van That Bai", "");
        }
    }
    ////chua chuan hoa object
//    Optional<Account> findById(@PathVariable Integer id) {
//        return repository.findById(id);
//    }

    //insert
    @PostMapping("/insert")
    ResponseEntity<Response> createAccount(@RequestBody Account newAccount) {
        List<Account> acc = repository.findByUsername(newAccount.getUsername().trim());
        if (acc.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new Response("False", "Ten Khong Duoc Trung Nhau", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("Ok", "Thanh Cong", repository.save(newAccount))
            );
        }
    }
//    //CREATE
//    Account insertAccount (@RequestBody Account newAccount){
//        return repository.save(newAccount);
//    }

    //Update
    @PutMapping("/{id}")
    Response updateAccount(@RequestBody Account newAccount, @PathVariable Integer id) {
        Optional<Account> updateAcc = repository.findById(id);
        boolean exists = repository.existsById(id);
        if (exists) {
            updateAcc.map(account -> {
                //account.setUsername(newAccount.getUsername());
                account.setPassword(newAccount.getPassword());
                return repository.save(account);
            });
            return new Response("Ok", "Updated", updateAcc);
        } else {
            return new Response("Fasle", "Id = " + id + " Khong ton tai", "");
        }
    }

    //Delete
    @DeleteMapping("/{id}")
    Response deleteAccount(@PathVariable Integer id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return new Response("OK", "id: " + id + " Deleted", "");
        }
        return new Response("Failed", "id: " + id + " does not exist", "");
    }
}
