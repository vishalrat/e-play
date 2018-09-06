FROM java:8
VOLUME /tmp
ADD ./target/ticket-engine-1.0.0.jar ticket-engine.jar
EXPOSE 9001
RUN bash -c 'touch /ticket-engine.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/ticket-engine.jar"]
