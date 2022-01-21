package com.example.apidemo.Springboot.database;

import com.example.apidemo.Springboot.Sevices.AccountServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Demo {

    @Bean
    @Qualifier("test1")
    AccountServices accountServices3(){
        return new AccountServices();
    }
}
