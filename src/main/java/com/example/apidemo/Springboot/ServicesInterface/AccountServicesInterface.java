package com.example.apidemo.Springboot.ServicesInterface;

import com.example.apidemo.Springboot.models.Account;
import com.example.apidemo.Springboot.models.AccountDto;
import com.example.apidemo.Springboot.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AccountServicesInterface {

    //view
    public abstract List<Account> getAllAccounts();

    //find by id
    public abstract Response findId(@PathVariable Integer id);

    // create new account
    public  abstract ResponseEntity<Response> createAccount(@RequestBody AccountDto newAccount);

    //update password account
    public abstract Response updateAccount(@RequestBody Account newAccount, @PathVariable Integer id);

    //delete account by id
    public abstract Response deleteAccount(@PathVariable Integer id);
}
