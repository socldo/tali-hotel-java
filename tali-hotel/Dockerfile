FROM openjdk:11
EXPOSE 1802
ARG JAR_FILE
COPY target/tali-hotel-2.7.12 tali-hotel-2.7.12.jar
ENTRYPOINT ["java","-jar","/tali-hotel-2.7.12.jar"]