package com.example.schedulingtasks;

import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

import static com.example.schedulingtasks.SchedulingTasksApplication.TAGS;

@Component
@EnableAsync
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    // Creates log message which is 1024 bytes long in JSON format
    private static final String LOG_MESSAGE = "Lorem ipsum dolor sit amet consectetur adipiscing elit taciti ultrices, cum potenti ridiculus leo varius porta fermentum. Turpis a habitant vivamus integer sollicitudin nulla torquent, litora ridiculus etiam ullamcorper porta aliquet consequat sed, posuere imperdiet orci varius curabitur montes. Ad molestie et curae felis eu sed mi lacinia euismod, vel consequat inceptos nascetur penatibus ultricies mauris natoque. Cras proin fames blandit gravida consequat tincidunt vivamus malesuada torquent, curabitur ante risus luctus nibh dapibus vestibulum duis senectus, class volutpat integer mus pulvinar euismod pharetra fermentum. Imperdiet ullamcorper cubilia tortor phasellus laoreet augue placerat rhoncus netus, integer congue tempor dapibus eget senectus tempus elementum habitant, etiam aenean auctor snectus tempus";

    @Async
    @Scheduled(fixedRateString = "${LOG_RATE_IN_MILLISECONDS}")
    public void scheduled() {
        String tag = TAGS[ThreadLocalRandom.current().nextInt(0, TAGS.length)];
        StructuredArgument s = StructuredArguments.v("tags", new String[]{tag});
        LOGGER.info("{} {}", LOG_MESSAGE, s);
    }

}
