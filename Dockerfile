# Build Stage
FROM maven:3.9.16-eclipse-temurin-17 AS build

WORKDIR /src

COPY pom.xml .
COPY src ./src

RUN mvn -B package -DskipTests

#Stage 2 Runtime Image
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /src/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]