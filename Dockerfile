FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

ENV MARIADB_NAME=safeOrder\
    MARIADB_PASSWORD=413dq5mYNarLePC1GhvHk3JBWobs107DjplXIVxwBkMg1LbRyB\
    MARIADB_URL=192.168.0.104:3306\
    MARIADB_USERNAME=safeOrder\