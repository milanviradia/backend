# BASE IMAGE.
FROM openjdk:8-jre-alpine
# make directory under this path.
RUN mkdir /usr/myapp
# copy JAR file into /usr/myapp directory.
COPY target/backend-0.0.1-SNAPSHOT.jar /usr/myapp
# switch to that directory.
WORKDIR /usr/myapp
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]

