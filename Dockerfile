FROM bellsoft/liberica-openjdk-alpine-musl:21.0.2
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} java-app.jar
CMD ["java","-jar","java-app.jar"]
