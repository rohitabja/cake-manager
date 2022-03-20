FROM openjdk:11.0.4-jre
COPY target/cake-manager-1.0-SNAPSHOT.war /cake-manager.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/cake-manager.war"]