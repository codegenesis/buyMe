package com.couchbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.couchbase")
public class CouchbaseTutorialApplication
{
    public static void main(String[] args)
    {
        System.out.println("hey 1");
        SpringApplication.run(CouchbaseTutorialApplication.class, args);
        System.out.println("hey 2");
    }
}
