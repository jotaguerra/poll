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

## Testing the app

To test the running app we can use:

```
mvn install
```

If we do not have the app running, we can test inside our favourite IDE. 

Open the class *PollApplicationTests* and launch the test.


## Endpoints

### Create a Poll with three attributes (name, site, xmldata)

url: http://localhost:8080/v1/polls 

method: POST

example: 
```
curl -X POST -H 'Content-Type: application/json' -d '{"site":"site1","name":"firstsite", "xmldata":"xml data 1..."}' http://localhost:8080/v1/polls
```



### Get a Poll By ID

url: http://localhost:8080/v1/polls/{id}

method: GET

example:
```
curl -X GET http://localhost:8080/v1/polls/1
```


### Get a Poll by site and pollName

url: http://localhost:8080/v1/polls?site={sitevalue}&name={nameValue} 

method: GET

example:
```
curl -X GET http://localhost:8080/v1/polls?site=site1&name=firstsite
```


### Update a Poll with either pollName + site and it’s appropriate xmldata payload

url: http://localhost:8080/v1/polls 

method: PUT

example: 
```
curl -X PUT -H 'Content-Type: application/json' -d '{"id":"1", "site":"site1change","name":"firstsite1change", "xmldata":"new XML Data!..."}' http://localhost:8080/v1/polls?site=site1&name=firstsite
```



### Update a Poll with ID and it’s appropriate xmldata payload

url: http://localhost:8080/v1/polls 

method: PUT

example: 
```
curl -X PUT -H 'Content-Type: application/json' -d '{"id":"1", "site":"site1change2","name":"firstsite1change2", "xmldata":"new XML Data 2!..."}' http://localhost:8080/v1/polls/1
```


