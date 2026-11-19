FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY cart/pom.xml .
RUN mvn -q dependency:go-offline
COPY cart/src ./src
RUN mvn -q clean package -DskipTests

FROM tomcat:10.1-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/cart.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]

