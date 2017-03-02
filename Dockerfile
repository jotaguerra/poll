FROM ubuntu

RUN  apt-get -y update && \
     apt-get install && \
     apt-get install -y openjdk-8-jdk && \
     mkdir -p /usr/src/pollrest

WORKDIR /usr/src/pollrest
COPY . /usr/src/pollrest/

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN java -jar target/poll-0.0.1-SNAPSHOT.jar
EXPOSE 8080 -p 8080:8080


