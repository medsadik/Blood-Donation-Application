FROM maven:3.8.5-openjdk-17
WORKDIR /donation-service
COPY . .
RUN mvn clean install -DskipTests
EXPOSE 8081
CMD mvn spring-boot:run

