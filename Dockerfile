FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} springboottest.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/springboottest.jar"]