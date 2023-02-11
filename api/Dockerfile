FROM maven:alpine as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn package

FROM adoptopenjdk:11
EXPOSE 8080
COPY --from=build /usr/app/target/simpleapi-impl-0.0.1-SNAPSHOT.jar /app/simpleapi-impl.jar
ENTRYPOINT ["java","-jar","/app/simpleapi-impl.jar"]
