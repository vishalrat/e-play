FROM java:8
VOLUME /tmp
ADD ./target/email-service-1.0.0.jar email.jar
EXPOSE 9000
RUN bash -c 'touch /email.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/email.jar"]
