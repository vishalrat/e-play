
FROM java:8
VOLUME /tmp
ADD ./target/user-registration-v1.0.0.jar user.jar
EXPOSE 8091
RUN bash -c 'touch /user.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/user.jar"]