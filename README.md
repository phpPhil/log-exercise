This codebase contains a log parser for a coding exercise.

# Task

The task is to parse a log file containing HTTP requests and to report on its contents.
For a given log file we want to know:
* The number of unique IP addresses
* The top 3 most visited URLs
* The top 3 most active IP addresses

# Implementation
This task was implemented via Spring Boot as a basic (Micro)service using REST.
Some assumptions about the domain model were made when it comes to the endpoint mapping. The GET Url used to access this functionality is: `/log/httpRequest/summary/{logId}`

`logId` was provided as a demo for parameterisation.

The underlying log file has been stored as a resource and will only be found if `logId` with value `1` is passed through the endpoint.

# Getting started

## Pre-requisites

* Java SDK 18
* Maven 3
* (Docker)

## Build & Run the tests
There are multiple controller and unit tests available.
```
mvn test
```

## Run in deployable container
Note that this has only partially implemented and is untested:

```
mvn package
docker build
docker run
```

Then access the docker container in your web browser using the container host name with the path of the endpoint:

`<docker-container-host>/log/httpRequest/summary/1`.

# Limitations

Currently, validations result in Runtime errors.

Ideally, these should be custom exceptions mapped to 4xx error codes. This was not implemented due to time constraints.
