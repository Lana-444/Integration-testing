FROM openjdk:21-jdk
EXPOSE 8081
ADD target/application-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]