package com.moatcrew.restbdd;

import com.moatcrew.restbdd.model.User;
import com.moatcrew.restbdd.rest.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        RestService<User[]> userService = new RestService<User[]>(User[].class);
        User[] users = userService.getObjectFromUrl("http://moifi.com:8080/social-expo-server/users");
        for (User user : users) {
            log.info(user.toString());
        }
    }
}
