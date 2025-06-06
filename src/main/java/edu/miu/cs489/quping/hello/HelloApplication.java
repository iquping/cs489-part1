package edu.miu.cs489.quping.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/hello")
public class HelloApplication {

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}
