FROM openjdk:17
WORKDIR /usr/src/myapp
COPY target/gateway-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]