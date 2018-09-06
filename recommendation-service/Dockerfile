FROM java:8
VOLUME /tmp
ADD ./target/recommendation-service-1.0.0.jar recommendation.jar
EXPOSE 8098
RUN bash -c 'touch /recommendation.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/recommendation.jar"]
