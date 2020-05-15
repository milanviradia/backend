# BASE IMAGE.
FROM openjdk:8-jre-alpine
# make directory under this path.
RUN mkdir /usr/myapp
# start tomcat server on PORT 8087.
EXPOSE 8087
# copy JAR file into /usr/myapp directory.
COPY target/backend-0.0.1-SNAPSHOT.jar /usr/myapp
# switch to that directory.
WORKDIR /usr/myapp
# run application from JAR file.
CMD ["java","-jar", "backend-0.0.1-SNAPSHOT.jar"]

