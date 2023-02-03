# Store Microservices

Using Java and Spring Boot is presented 3 microservices and make them communicate with each other.

## Tecnologies

- Java 17
- Spring Boot 2.7.8
- PostgreSQL 15
- FlywayDB
- Maven
- Docker

### Plugin

This project uses the `dockerfile-maven-plugin`. To run it, maybe it will be necessary to add `settings.xml` on `.m2` local folder. See below the content of this file:

```
<?xml version="1.0" encoding="UTF-8"?>
<settings
 xmlns="http://maven.apache.org/SETINGS/1.0.0"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings=1.0.0.xsd"
>
  <pluginGroups>
    <pluginGroup>com.spotify</pluginGroup>
  </pluginGroups>

</settings>
```

## Running the services

All services use a shared dependency called `cart-client`. Before run the docker-compose file it is necessary to build this project. See the steps to accomplish this mission:

```
# inside the cloned folder
cd cart-client
mvn clean install
```

---

Next step is run the docker-compose file:

```
# inside the cloned folder
cd docker
docker-compose up
```

## New features (comming soon!)

Next releases are:
- [ ] Test improvements
- [ ] H2 as the database for test environment
- [ ] Spring Security + JWT
- [ ] Spring Profile
- [ ] OpenAPI
- [ ] API Gateway
- [ ] Builder pattern
- [ ] Scripts for build dependencies
- [ ] Scripts to run all tests before build
- [ ] Kafka
- [ ] Use BigDecimal for monetary values
- [ ] Kubernetes setup
- [ ] CI/CD pipeline
- [ ] Deploy on AWS Cloud
