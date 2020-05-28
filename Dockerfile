# BASE IMAGE.
FROM openjdk:8-jre-alpine
# make directory under this path.
RUN mkdir /usr/myapp
# Tomcat will run on 8087.
EXPOSE 8087
# copy JAR file into /usr/myapp directory.
COPY target/backend-0.0.1-SNAPSHOT.jar /usr/myapp
# switch to that directory.
WORKDIR /usr/myapp
# RUN application.
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]

