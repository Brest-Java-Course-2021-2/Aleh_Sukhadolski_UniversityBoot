# UniversityBoot

#### _mvn clean install_
#### _cd rest-application_
#### _./mvnw spring-boot::run_



## __TEST APPLICATION IN THE POSTMAN__


### create user

_method post_

http://localhost:8080/lector/new

body:

{
"nameLector": "Mike",
"loginLector" : "mike",
"passwordLector": "1111",
"emailLector" : "mike@tyson.com"
}

Response : 1  (id of the new user)
____________________________________

_method post_

http://localhost:8080/lector/new

body:
{
"nameLector": "John",
"loginLector" : "john",
"passwordLector": "2222",
"emailLector" : "john@bonjovy.com"
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

http://localhost:8080/group/new

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
http://localhost:8080/group/all
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
        "idRequest": 21,
        "group": "e1",
        "numberOfPairs": "2",
        "subjectOfLector": "math",
        "idLector": 20,
        "date": "2022-03-14T17:09:26.913+00:00"
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


