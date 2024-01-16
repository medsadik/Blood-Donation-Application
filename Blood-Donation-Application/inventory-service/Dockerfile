FROM maven:3.8.5-openjdk-17
WORKDIR /inventory-service
COPY . .
RUN mvn clean install -DskipTests
EXPOSE 8082
CMD mvn spring-boot:run

