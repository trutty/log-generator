package com.example.schedulingtasks;

import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@EnableAsync
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final Runnable[] RUNNABLES;

    static {
        final List<Runnable> runnables = new ArrayList<>();
        runnables.add(ScheduledTasks::log);

        if (SchedulingTasksApplication.GENERATE_EXCEPTION_LOGS) {
            runnables.add(ScheduledTasks::throwException);
        }

        RUNNABLES = runnables.toArray(new Runnable[0]);
    }

    private static final String[] TAGS;

    static {
        final List<String> tags = new ArrayList<>();
        tags.add("INFO");

        if (SchedulingTasksApplication.GENERATE_SECURITY_LOGS) {
            tags.add("SECURITY");
        }

        TAGS = tags.toArray(new String[0]);
    }

    @Async
    @Scheduled(fixedRateString = "${LOG_RATE_IN_MILLISECONDS}")
    public void scheduled() {
        RUNNABLES[ThreadLocalRandom.current().nextInt(0, RUNNABLES.length)].run();
    }

    public static void log() {
        String tag = TAGS[ThreadLocalRandom.current().nextInt(0, TAGS.length)];
        StructuredArgument s = StructuredArguments.v("tags", new String[]{tag});

        LOGGER.info("Just logging some info message with any content to have some data and tag '{}'", s);
    }

    public static void throwException() {
        throw new ArithmeticException("My test RuntimeException without explicit logging");
    }

}
