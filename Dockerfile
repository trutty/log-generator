FROM openjdk:8-jre
ENV LOG_RATE_IN_MILLISECONDS "5"
COPY target/*.jar log-generator.jar
ENTRYPOINT ["java","-jar","/log-generator.jar"]
