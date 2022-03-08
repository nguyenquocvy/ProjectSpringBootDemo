package com.example.apidemo.Springboot.Sevices;

import com.example.apidemo.Springboot.ErrorCode.NotValidException;
import com.example.apidemo.Springboot.ServicesInterface.AccountServicesInterface;
import com.example.apidemo.Springboot.models.Account;
import com.example.apidemo.Springboot.models.AccountDto;
import com.example.apidemo.Springboot.models.Response;
import com.example.apidemo.Springboot.repos.AccountRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.StampedLock;

@Service
@Primary
public class AccountServices implements AccountServicesInterface {
    @Autowired
    private AccountRepos repository;
    @Autowired
    private Validator validator;

    // xem tat ca
    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    // xem theo id
    @Override
    public Response findId(@PathVariable Integer id) {
        Optional<Account> dataAccount = this.repository.findById(id);
        if (dataAccount.isPresent()) {
            return new Response("Ok", "Truy Van Thanh Cong", dataAccount);

        } else {
            return new Response("False", "Accoun Service", "");
        }
    }

    //chua chuan hoa object
//    Optional<Account> findById(@PathVariable Integer id) {
//        return repository.findById(id);
//    }

    //them account moi
    @Override
    public ResponseEntity<Response> createAccount(@RequestBody AccountDto newAccount) {
        //validate(newAccount);
        List<Account> timAccount = this.repository.findByUsername(newAccount.getUsername().trim());// tim ten giong voi ten moi
        if (timAccount.size() > 0) { // (> 0 => co mot cai trung roi) ||
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new Response("False", "Ten Khong Duoc Trung Nhau", "")
            );
        } else {
            Account account = new Account();
            account.setUsername(newAccount.getUsername());
            account.setPassword(newAccount.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("Ok", "Thanh Cong", repository.save(account))
            );
        }
    }

    //edit account
    @Override
    public Response updateAccount(@RequestBody Account newAccount, @PathVariable Integer id) {
        Optional<Account> updateAcc = this.repository.findById(id);
        boolean exists = this.repository.existsById(id);
        if (exists) {
            updateAcc.map(account -> {
                //account.setUsername(newAccount.getUsername());
                account.setPassword(newAccount.getPassword());
                return this.repository.save(account);
            });
            return new Response("Ok", "Updated", updateAcc);
        } else {
            return new Response("Fasle", "Id = " + id + " Khong ton tai", "");
        }
    }

    //xoa id
    @Override
    public Response deleteAccount(@PathVariable Integer id) {
        boolean exists = this.repository.existsById(id);
        if (exists) {
            this.repository.deleteById(id);
            return new Response("OK", "id: " + id + " Deleted", "");
        }
        return new Response("Failed", "id: " + id + " does not exist", "");
    }

    public void validate(Object input) {
        Set<ConstraintViolation<Object>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new NotValidException("payment not valid exception", violations);
        }
    }
}
