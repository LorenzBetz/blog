# Blog Service
Blog Service implemented with the following Tech-Stack: 
* Java Spring Boot
* GraphQL
* MongoDB
## Prerequisites
### Database (MongoDB)
To run this service you need to specify the connection string of your MongoDB instance.
Go to src/main/resources/application.properties an update spring.data.mongodb.uri to fit your environment.  

If you want to find out more on how to set up a MongoDB instance check out the MongoDB documentation on [DockerHub](https://hub.docker.com/_/mongo).

### Profiles
There are two run profiles: 
* production  
* dev

You can specify the used profile as folows: 
```
bootRun --args='--spring.profiles.active={dev|production}
```
