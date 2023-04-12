FROM gcr.io/distroless/java17
WORKDIR /app
COPY target/distroless-0.0.1.jar app.jar
ENTRYPOINT java -jar app.jar