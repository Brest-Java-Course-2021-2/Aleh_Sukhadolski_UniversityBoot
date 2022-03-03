# UniversityBoot

#### _mvn clean install_
#### _cd rest-application_
#### _./mvnw spring-boot::run_



##База не инициализируется:


### Get all users  Get
http://localhost:8080/user/all

### Get all groupes
http://localhost:8080/groupe/all

### Insert new groupes
http://localhost:8080/groupe/new?newName=e1
http://localhost:8080/groupe/new?newName=e2
http://localhost:8080/groupe/new?newName=e3
http://localhost:8080/groupe/new?newName=e4
http://localhost:8080/groupe/new?newName=e5
http://localhost:8080/groupe/new?newName=e6

### Get groupe By name groupe
http://localhost:8080/groupe/get/name?name=e1

### Get all groupes by Names
localhost:8080/groupe/get/name/all


### Save new user  
http://localhost:8080/user/save?name=MikeTyson&login=mike&password=11111&email=mike@mail.net
http://localhost:8080/user/save?name=MohammadAli&login=mohammad&password=222222&email=mike@mail.net

### Update user with id = 1  
http://localhost:8080/user/update?id=1&name=MichaelJordan&login=michael&password=33333&email=machael@mail.net

### Get user by name = Mohammad Ali   
http://localhost:8080/user/name?name=MohammadAli

### Get user by email = mike@mail.net   
http://localhost:8080/user/email?email=mike@mail.net

### Get user by id = 1 
http://localhost:8080/user/id?id=1


### Delete user by id = 1 
http://localhost:8080/user/delete/id?id=1
http://localhost:8080/user/all

### Delete user by user 
http://localhost:8080/user/delete?id=2&name=MohammadAli&login=mohammad&password=222222&email=mike@mail.net
http://localhost:8080/user/all