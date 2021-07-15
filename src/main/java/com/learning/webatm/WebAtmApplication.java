package com.learning.webatm;

import com.learning.webatm.repository.UserRepo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class WebAtmApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebAtmApplication.class, args);

    }

}
