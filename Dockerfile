FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY target/*.jar quote.service.jar
ENTRYPOINT ["java", "-jar", "/quote.service.jar"]
EXPOSE 9003