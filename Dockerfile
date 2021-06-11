FROM adoptopenjdk/openjdk14:latest
ADD . /src
WORKDIR /src
EXPOSE 8393
COPY target/${JAR_FILE} abw-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","target/abw-1.0-SNAPSHOT.jar"]