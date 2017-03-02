FROM java:8-jre
MAINTAINER Joaquin Guerra <joaquinguenu@gmail.com>

ADD ./target/poll-0.0.1-SNAPSHOT.jar /app/
CMD java -agentlib:jdwp=transport=dt_socket,address=8080,suspend=n,server=y -jar /app/poll-0.0.1-SNAPSHOT.jar
EXPOSE 8080

