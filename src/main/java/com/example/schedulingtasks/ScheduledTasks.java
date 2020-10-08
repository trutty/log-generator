/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.schedulingtasks;

import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final String[] TAGS = new String[]{
            "SECURITY",
            "AUDIT",
            "INFO"
    };

    private static String getCurrentTime() {
        return Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Scheduled(fixedRate = 3000)
    public void log() {

        // We want to append 0..len(TAGS) tag values to the log message
        int numberOfTags = ThreadLocalRandom.current().nextInt(0, TAGS.length + 1);

        // Choose which tags should be appended
        final Set<String> tagsToAppend = IntStream.range(0, numberOfTags)
                .map(unused -> ThreadLocalRandom.current().nextInt(0, TAGS.length))
                .mapToObj(nextInt -> TAGS[nextInt])
                .collect(Collectors.toSet());

        // Log info message with appended structured arguments
        StructuredArgument structuredArgument = StructuredArguments.v("tags", tagsToAppend);
        log.info("The time is now {}", getCurrentTime(), structuredArgument);
    }

}
