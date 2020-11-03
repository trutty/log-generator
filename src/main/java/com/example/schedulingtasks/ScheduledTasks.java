package com.example.schedulingtasks;

import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@EnableAsync
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final String[] TAGS = new String[]{"SECURITY", "AUDIT", "INFO"};

    private static final Runnable[] RUNNABLES = new Runnable[]{
            ScheduledTasks::logInfo,
            ScheduledTasks::logErrorWithStacktrace,
            ScheduledTasks::throwExceptionWithoutLogStatement
    };

    @Async
    @Scheduled(fixedRateString = "${LOG_RATE_IN_MILLISECONDS}")
    public void log() {
        RUNNABLES[ThreadLocalRandom.current().nextInt(0, RUNNABLES.length)].run();
    }

    public static void logInfo() {
        LOGGER.info("Info at {}", getCurrentTime(), getStructuredArgument());
    }

    public static void logErrorWithStacktrace() {
        LOGGER.error("Error at {}", getCurrentTime(), new RuntimeException("My test RuntimeException"));
    }

    public static void throwExceptionWithoutLogStatement() {
        throw new ArithmeticException("My test RuntimeException without explicit logging");
    }

    private static String getCurrentTime() {
        return Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
    }

    private static StructuredArgument getStructuredArgument() {
        // We want to append 0..len(TAGS) tag values to the log message
        int numberOfTags = ThreadLocalRandom.current().nextInt(0, TAGS.length + 1);

        // Choose which tags should be appended
        final Set<String> tagsToAppend = IntStream.range(0, numberOfTags)
                .map(unused -> ThreadLocalRandom.current().nextInt(0, TAGS.length))
                .mapToObj(nextInt -> TAGS[nextInt])
                .collect(Collectors.toSet());

        // Log info message with appended structured arguments
        return StructuredArguments.v("tags", tagsToAppend);
    }
}
