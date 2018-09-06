FROM java:8
VOLUME /tmp
ADD ./target/show-scheduler-1.0.0.jar show-scheduler.jar
EXPOSE 8099
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/show-scheduler.jar"]
