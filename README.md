# Purpose
Very basic Spring Boot application for testing purposes. Generates log statements in Logstash format in the form of:
```json
{
   "@timestamp":"2020-10-08T20:42:24.361Z",
   "@version":"1",
   "message":"The time is now 2020-10-08T20:42:24.361052Z",
   "logger_name":"com.example.schedulingtasks.ScheduledTasks",
   "thread_name":"scheduling-1",
   "level":"INFO",
   "level_value":20000,
   "tags":[
      "SECURITY"
   ]
}
``` 
**Note**: Pretty printed here but actually logged as one line.

Does nothing else.

# Build
Run `maven clean package` to build `target/log-generator.jar` file. Run spring boot application with `java -jar target/log-generator.jar`

# Configuration
Set the environment variable `LOG_RATE_IN_MILLISECONDS` to a string value of your choice (default "10000").
