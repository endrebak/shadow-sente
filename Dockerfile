FROM openjdk:8-alpine

COPY target/uberjar/sente-example.jar /sente-example/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/sente-example/app.jar"]
