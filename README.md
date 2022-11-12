# Fargotest 

version 0.0.1

## Overview

This is a reactive java multi-module micro service application with a feign client gateway.
Assumes a mongoDB connection, but the DB connection string needs to be provided.

- core: libraries used by all micro services
- gateway: service routing
- github: github API metadata scanner
- validation: validates for compliance, store validation results and application data


## Multimodule Install and Run From Command Line

To build entire project, install from root. 

```
$ mvn clean install
```

To build an individual microservice and run it, execute from the appropriate subdirectory.
You will need to open a terminal window for each service you wish to run locally. To test against the frontend, the gateway service, which routes API calls, must be running... as well as any service you are testing. For a fully functional frontend, all services must be running.

```
$ cd gateway
$ mvn clean install
$ java -jar target/gateway-0.0.1-SNAPSHOT.jar
```

Core is a library used by all microservices. If you make changes to Core, recompile the library to make changes available to all microservices.


## Example Requests

To initialize the rule set (necessary for first run) with GET request:

```
http://localhost:8443/api/validation/rules/init

```

To validate repository with POST request:

```
http://localhost:8443/api/validation/validate

{
	"applicationId": "/paul-uulabs/capacitor-brotherprint",
	"applicationType": "github",
	"approvalDate": "2021-05-17T15:02:15Z",
	"authorizedDevelopers": ["pauljohnson@Pauls-MacBook-Air.local"]
}
```

