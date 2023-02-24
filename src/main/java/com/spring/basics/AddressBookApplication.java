package com.spring.basics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AddressBookApplication
{
    public static void main(String[] args)
    {
        log.info("HELLO LOGGER...!!! N.VIGNESHWARAN");
        log.info("GOOD TO HAVE U HERE..,HAVE A NICE DAY....................");
        SpringApplication.run(AddressBookApplication.class, args);
        log.info("SPRING-BOOT PROGRAM RUNNING SUCCESSFULLY.....!!!!!!!!!!!!!!!!!!!!");
    }

}
