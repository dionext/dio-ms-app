FROM bellsoft/liberica-openjdk-alpine-musl:23.0.1
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} java-app.jar
CMD ["java","-jar","java-app.jar"]
