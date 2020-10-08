FROM openjdk:8-jre
COPY target/*.jar log-generator.jar
ENTRYPOINT ["java","-jar","/log-generator.jar"]
