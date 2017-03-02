# Poll Rest Backend


## Introduction

First approach to a rest backend to create and search Polls.

Build with Spring Boot and the modules WEB, JPA and H2.

## Starting the app

Move into project folder and type in a shell:

```
mvn spring-boot:run
```

In less than a minute your rest backend is ready. You can find on http://localhost:8080/v1/polls


## Endpoints

### Get a Poll By ID

url: http://localhost:8080/v1/polls/{id}
method: GET
example:
```
curl -X GET http://localhost:8080/v1/polls/1
```


### Get a Poll by site and pollName

url: http://localhost:8080/v1/polls?site?{sitevalue}&name={nameValue} 
method: GET
example:
```
curl -X GET http://localhost:8080/v1/polls?site=site2&name=secondsite
```

### Create a Poll with three attributes (name, site, xmldata)

url: http://localhost:8080/v1/polls 
method: POST
example: 
```
curl -X POST -H 'Content-Type: application/json' -d '{"site":"site2","name":"secondsite", "xmldata":"xml data 2..."}' http://localhost:8080/v1/polls
```

### Update a Poll with either pollName + site and it’s appropriate xmldata payload

url: http://localhost:8080/v1/polls 
method: PUT
example: 
```
curl -X PUT -H 'Content-Type: application/json' -d '{"id":"2", "site":"site2change","name":"secondsitechange", "xmldata":"xml data 2..."}' http://localhost:8080/v1/polls?site=site2&name=secondsite
```



### Update a Poll with ID and it’s appropriate xmldata payload

url: http://localhost:8080/v1/polls 
method: PUT
example: 
```
curl -X PUT -H 'Content-Type: application/json' -d '{"id":"2", site":"site2","name":"secondsite", "xmldata":"xml data 2..."}' http://localhost:8080/v1/polls/2
```


