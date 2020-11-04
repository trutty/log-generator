package com.example.schedulingtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {

    public static final boolean GENERATE_SECURITY_LOGS = getEnvWithDefault("GENERATE_SECURITY_LOGS", true);

    public static final boolean GENERATE_EXCEPTION_LOGS = getEnvWithDefault("GENERATE_EXCEPTION_LOGS", true);

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingTasksApplication.class);

    private static boolean getEnvWithDefault(final String env, final boolean defaultValue) {
        final String value = System.getenv(env);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static void main(String[] args) {
        LOGGER.info("Running with LOG_RATE_IN_MILLISECONDS: " + System.getenv("LOG_RATE_IN_MILLISECONDS"));
        LOGGER.info("Running with GENERATE_SECURITY_LOGS: " + GENERATE_SECURITY_LOGS);
        LOGGER.info("Running with GENERATE_EXCEPTION_LOGS: " + GENERATE_EXCEPTION_LOGS);
        SpringApplication.run(SchedulingTasksApplication.class);
    }
}
