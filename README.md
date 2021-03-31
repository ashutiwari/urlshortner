# URL Shortner

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

```
clone the repo
cd urlshortner
mvn clean install
java -jar target/urlshortner.jar

```

## Running docker image locally

```
docker pull ashu6136/urlshortner:docker-urlshortner
docker run -p 8085:8085 docker-urlshortner

```

## APIs swagger documentation 

```
After running Application locally visit on below link to open swagger UI.
http://localhost:8085/swagger-ui.html#!/

```