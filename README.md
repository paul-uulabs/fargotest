# Fargotest 

version 0.0.1

## Overview

This is a fully reactive java multi-module micro service application with a feign client gateway.

- core: libraries used by all micro services
- gateway: service routing
- github: github API metadata scanner
- validation: validates for compliance, store validation results and application data


## Installing and Running

1. Update the 3 application.yml files with the database connection string provided in the PDF. Replace 'dpconnectionstring' with the connection string. The yml files that need to be updated are located:

```
/core/src/main/resources/application.yml
/github/src/main/resources/application.yml
/validation/src/main/resources/application.yml
```

2. Navigate to project root directory in your terminal and build entire project. 

```
$ mvn clean install
```

3. After you have successfully built the project, start the gateway.

```
$ cd gateway
$ java -jar target/gateway-0.0.1-SNAPSHOT.jar
```

4. Open a new terminal window and navigate to the GitHub service and start it.

```
$ cd github
$ java -jar target/github-0.0.1-SNAPSHOT.jar
```

5. Finally, open a new terminal window and navigate to the Validation service and start it.

```
$ cd validation
$ java -jar target/validation-0.0.1-SNAPSHOT.jar
```


## Example Requests


To validate repository with POST request. 
You can change the applicationId to a user/repo for any public GitHub repository:

```
http://localhost:8443/api/validation/validate

{
	"applicationId": "/paul-uulabs/capacitor-brotherprint",
	"applicationType": "github",
	"approvalDate": "2021-05-17T15:02:15Z",
	"authorizedDevelopers": ["pauljohnson@Pauls-MacBook-Air.local"]
}
```


To initialize the rule set with GET request. This is not necessary, but if you want to rebuild the rule set you can:

```
http://localhost:8443/api/validation/rules/init

```

