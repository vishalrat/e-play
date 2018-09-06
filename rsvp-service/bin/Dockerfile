
FROM java:8
VOLUME /tmp
ADD ./target/rsvp-v1.0.0.jar rsvp.jar
EXPOSE 8097
RUN bash -c 'touch /rsvp.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/rsvp.jar"]