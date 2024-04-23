package com.storage.springbootquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootQuizApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootQuizApplication.class, args);

        dummyGenerator dummyDataGenerator = context.getBean(dummyGenerator.class);

        // Call the generateDummyData method with the desired parameters
        dummyDataGenerator.generateDummyData(10, 5, 5, 3); // Example: 10 sales, 5 products, 5 clients, 3 sellers

    }

}
