package com.example.schedulingtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingTasksApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Running with LOG_RATE_IN_MILLISECONDS: " + System.getenv("LOG_RATE_IN_MILLISECONDS"));
        SpringApplication.run(SchedulingTasksApplication.class);
    }
}
