FROM openjdk
WORKDIR usr/lib
EXPOSE 9000
ADD ./target/APIGatewayService-0.0.1-SNAPSHOT.jar /usr/lib/APIGatewayService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","APIGatewayService-0.0.1-SNAPSHOT.jar"]