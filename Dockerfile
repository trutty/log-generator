FROM openjdk:8-jre
COPY target/*.jar log-generator.jar
ENV LOG_RATE_IN_MILLISECONDS "10000"
ENTRYPOINT ["java","-jar","/log-generator.jar"]
