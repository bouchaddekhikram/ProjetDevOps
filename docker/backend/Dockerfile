#Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src 
RUN mvn clean package -DskipTests

#Runtime stage
FROM amazoncorretto:17
ARG PROFILE=dev 
ARG APP_VERSION=0.0.1-SNAPSHOT



#define few things 

WORKDIR /app
COPY --from=build /build/target/spring-boot-security-jwt-${APP_VERSION}.jar /app/

EXPOSE 8085

ENV DB_URL=jdbc:mysql://mysql:3306/stage?useSSL=false
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} spring-boot-security-jwt-${JAR_VERSION}.jar
