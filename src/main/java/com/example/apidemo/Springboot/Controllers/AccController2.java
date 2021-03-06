package com.example.apidemo.Springboot.Controllers;

import com.example.apidemo.Springboot.ServicesInterface.AccountServicesInterface;
import com.example.apidemo.Springboot.models.Account;
import com.example.apidemo.Springboot.models.AccountDto;
import com.example.apidemo.Springboot.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api2/account")
public class AccController2{


    @Autowired
    @Qualifier("test1")
    private AccountServicesInterface accInter;

    // hien thi tat ca account
    @GetMapping("")
    protected List<Account> getAllAccounts() {
        return this.accInter.getAllAccounts();
    }

    //get detail by id
    @GetMapping("/{id}")
    protected Response findId(@PathVariable Integer id) {
        return this.accInter.findId(id);
    }

    //CREATE
    @PostMapping("/insert")
    protected ResponseEntity<Response> createAccount(@RequestBody @Valid AccountDto newAccount) {
        return this.accInter.createAccount(newAccount);
    }

    //Update
    @PutMapping("/{id}")
    protected Response updateAccount(@RequestBody Account newAccount, @PathVariable Integer id) {
        return this.accInter.updateAccount(newAccount, id);
    }

    //Delete
    @DeleteMapping("/{id}")
    protected Response deleteAccount(@PathVariable Integer id) {
        return this.accInter.deleteAccount(id);
    }
}

