package com.moatcrew.restbdd;

import com.moatcrew.restbdd.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * Created by maruku on 7/29/16.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    public void run(String... strings) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        User[] users = restTemplate.getForObject("http://moifi.com:8080/social-expo-server/users", User[].class);
        for (User user : users) {
            log.info(user.toString());
        }
    }
}
