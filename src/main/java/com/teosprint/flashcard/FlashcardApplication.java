package com.teosprint.flashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlashcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashcardApplication.class, args);


//        SpringApplication springApplication = new SpringApplication(FlashcardApplication.class);
//        //swagger listener filtering
//        springApplication.addListeners(new SwaggerListener());
//        springApplication.run(args);
    }

}
