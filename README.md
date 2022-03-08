# UniversityBoot

#### _mvn clean install_
#### _cd rest-application_
#### _./mvnw spring-boot::run_



## __TEST APPLICATION IN THE POSTMAN__


### create user

_method post_

http://localhost:8080/user/create

body:

{
"name": "Mike",
"login" : "mike",
"password": "1111",
"email" : "mike@tyson.com"
}

Response : 1  (id of the new user)
____________________________________

_method post_

http://localhost:8080/user/create

body:
{
"name": "John",
"login" : "john",
"password": "2222",
"email" : "john@bonjovy.com"
}

Response : 2  (id of the new user)
______________________________________

###  Test requests for user id = 1
__method GET__
http://localhost:8080/request/all/1

Response : []    __(The requests don't exists)__
______________________________________

### Create groupes

_method post_

http://localhost:8080/groupe/create

#### body:
e1
#### Response :
{
"idG": 5,
"groupe": "e1"
}
#### body:
e2
#### Response :
{
"idG": 8,
"groupe": "e2"
}
#### body:
e3
#### Response :
{
"idG": 11,
"groupe": "e3"
}
#### body:
e4
#### Response :
{
"idG": 14,
"groupe": "e4"
}

### __Test all groupes__
method GET
http://localhost:8080/groupe/all
Response :
[
{
"idG": 5,
"groupe": "e1"
},
{
"idG": 8,
"groupe": "e2"
},
{
"idG": 11,
"groupe": "e3"
},
{
"idG": 14,
"groupe": "e4"
}
]

### __Test requests for user id = 1__
__method GET__
http://localhost:8080/request/all/1
Response :
[
{
"idR": 3,
"groupe": "e1",
"pairs": "0",
"subject": "0000",
"date": "2022-03-08T12:36:42.569+00:00",
"id": 1
},
{
"idR": 6,
"groupe": "e2",
"pairs": "0",
"subject": "0000",
"date": "2022-03-08T12:38:49.242+00:00",
"id": 1
},
{
"idR": 9,
"groupe": "e3",
"pairs": "0",
"subject": "0000",
"date": "2022-03-08T12:39:17.339+00:00",
"id": 1
},
{
"idR": 12,
"groupe": "e4",
"pairs": "0",
"subject": "0000",
"date": "2022-03-08T12:39:41.581+00:00",
"id": 1
}
]


### Update request
#### method PUT

http://localhost:8080/request/update

#### body:
{
"idR": 12,
"groupe": "e4",
"pairs": "2",
"subject": "math",
"date": "2022-03-08T12:39:41.581+00:00",
"id": 1
}

#### response :
{
"idR": 12,
"groupe": "e4",
"pairs": "2",
"subject": "math",
"date": "2022-03-08T12:39:41.581+00:00",
"id": 1
}

#### test :
method GET
http://localhost:8080/request/12

#### response :
{
"idR": 12,
"groupe": "e4",
"pairs": "2",
"subject": "math",
"date": "2022-03-08T12:39:41.581+00:00",
"id": 1
}




# Requests to test REST application at console:


### Get all users  Get
curl --request GET 'http://localhost:8080/user/all'

### Get all groupes
curl --request GET 'http://localhost:8080/groupe/all'



### Insert new groupes

curl --request POST 'http://localhost:8080/groupe/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw 'e1'

curl --request POST 'http://localhost:8080/groupe/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw 'e2}'

curl --request POST 'http://localhost:8080/groupe/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw 'e3'

curl --request POST 'http://localhost:8080/groupe/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw 'e4'

curl --request POST 'http://localhost:8080/groupe/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw 'e5'

curl --request POST 'http://localhost:8080/groupe/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw 'e6'




### Get groupe By name groupe
curl --request GET 'http://localhost:8080/groupe/get/name?name=e1'


### Get all groupes by Names
curl --request GET 'http://localhost:8080/groupe/get/name/all'



### Create new user  
curl --request POST 'http://localhost:8080/user/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw '{"name": "MikeTyson","login": "mike","password": "1111","email": "mike@tyson.com"}'

curl --request POST 'http://localhost:8080/user/create' \--header 'Accept: application/json' \--header 'Content-Type: application/json' \--data-raw '{"name": "GeorgeForeman","login": "george","password": "2222","email": "george@foreman.com"}'


### Update user
curl --request PUT 'http://localhost:8080/user/update' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": "1"
"name": "DennyDeVito",
"login": "denny",
"password": "1111",
"email": "mike@tyson.com"
}'

### Get user by name = Mohammad Ali   
curl --request GET 'http://localhost:8080/user/name?name=MohammadAli'

### Get user by email = mike@mail.net   
curl --request GET 'http://localhost:8080/user/email?email=mike@mail.net'

### Get user by id = 1 
curl --request GET 'http://localhost:8080/user/id?id=1'


### Delete user by id = 1 

curl --request DELETE 'http://localhost:8080/user/delete/id?id=1'

curl --request GET 'http://localhost:8080/user/all'

### Delete user by user 

curl --request DELETE 'http://localhost:8080/user/delete' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": "1"
"name": "DennyDeVito",
"login": "denny",
"password": "1111",
"email": "mike@tyson.com"
}'

curl --request GET 'http://localhost:8080/user/all'
