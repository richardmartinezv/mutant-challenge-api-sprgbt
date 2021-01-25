# Step 1. Build the Image from an already compiled jar
FROM openjdk:11.0.6-jdk
LABEL maintainer="richardmartinezv@gmail.com"
RUN mkdir /home/config
COPY src/main/resources/bussiness.properties /home/config/bussiness.properties
WORKDIR /workspace
COPY  mutant-challenge-api-sprgbt-1.0.0.jar mutant-challenge-api-sprgbt.jar
RUN ls /workspace
ENTRYPOINT exec java -jar /workspace/mutant-challenge-api-sprgbt.jar