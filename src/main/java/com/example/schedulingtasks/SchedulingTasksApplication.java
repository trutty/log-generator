package com.example.schedulingtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {

    public static final String[] TAGS;

    static {
        final String tags = System.getenv("TAGS");
        if (tags != null) {
            TAGS = Arrays.stream(tags.split(","))
                    .map(String::trim)
                    .distinct()
                    .toArray(String[]::new);
        } else {
            TAGS = new String[]{""};
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingTasksApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Running with LOG_RATE_IN_MILLISECONDS: " + System.getenv("LOG_RATE_IN_MILLISECONDS"));
        LOGGER.info("Running with TAGS: " + Arrays.toString(TAGS));
        SpringApplication.run(SchedulingTasksApplication.class);
    }
}
