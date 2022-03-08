FROM openjdk
WORKDIR usr/lib
EXPOSE 8761
ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar /usr/lib/EurekaServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","EurekaServer-0.0.1-SNAPSHOT.jar"]