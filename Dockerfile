FROM openjdk:21
EXPOSE 8081
ADD target/demo.war demo.war
ENRTYPOINT["java","-jar","/demo.war"]