# UniversityBoot

#### _mvn clean install_
#### _cd rest-application_
#### _./mvnw spring-boot::run_


# __Endpoints__
## lectors
### Get all lectors:
#### GET: http://localhost:8080/lectors/get-all

### Get lector by the nameLector:
#### GET: http://localhost:8080/lectors/lector/get-name

### Get lector by the emailLector:
#### GET: http://localhost:8080/lectors/lector/get-email

### Get lector by the idLector:
#### GET: http://localhost:8080/lectors/lector/{idLector}

### Create new lector :
#### POST: http://localhost:8080/lectors/lector/new
#### for example: @RequestBody {"nameLector": "Mike","loginLector": "mike","passwordLector": "1111","emailLector": "mike@tyson.com"}

### Update lector :
#### PUT: http://localhost:8080/lectors/lector/update
#### for example: @RequestBody {"idLector": "1",""nameLector": "Mike","loginLector": "mike","passwordLector": "1111","emailLector": "mike@tyson.com"}

### Delete lector by idLector :
#### DELETE: http://localhost:8080/lectors/lector/{id}/delete
#### for emample:  http://localhost:8080/lectors/lector/1/delete

### Delete lector by Lector :
#### DELETE: http://localhost:8080/lectors/lector/delete
#### for emample: @RequestBody {"idLector": "1",""nameLector": "Mike","loginLector": "mike","passwordLector": "1111","emailLector": "mike@tyson.com"} 




## __TEST APPLICATION IN THE POSTMAN__

### get all lectors
GET
http://localhost:8080/lectors/get-all


### create lector
POST
http://localhost:8080/lectors/lector/new
body:

{
"nameLector": "Mike",
"loginLector": "mike",
"passwordLector": "1111",
"emailLector": "mike@tyson.com"
}

Response : 1  (id of the new lector)
____________________________________
POST
http://localhost:8080/lectors/lector/new
body:

{
"nameLector": "John",
"loginLector" : "john",
"passwordLector": "2222",
"emailLector" : "john@bonjovy.com"
}

Response : 2  (id of the new lector)
______________________________________

###  Test requests for lector by id lector 
GET
http://localhost:8080/lectors/lector/{idLector}/requests/all

Response : []    __(The requests don't exists)__
______________________________________

### Create groupes

POST
http://localhost:8080/groups/group/new

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
GET
http://localhost:8080/groups/get-all
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

### __Test requests for lector =  id lector __
GET
http://localhost:8080/lectors/lector/{id}/requests/all
Response :
[
{
"idR": 3,
"groupe": "e1",
"pairs": "0",
"subject": "    ",
"date": "2022-03-08T12:36:42.569+00:00",
"id": 1
},
{
"idR": 6,
"groupe": "e2",
"pairs": "0",
"subject": "    ",
"date": "2022-03-08T12:38:49.242+00:00",
"id": 1
},
{
"idR": 9,
"groupe": "e3",
"pairs": "0",
"subject": "    ",
"date": "2022-03-08T12:39:17.339+00:00",
"id": 1
},
{
"idR": 12,
"groupe": "e4",
"pairs": "0",
"subject": "    ",
"date": "2022-03-08T12:39:41.581+00:00",
"id": 1
}
]


### Update request
#### method PUT

http://localhost:8080/lectors/lector/request/update

#### body:
{
        "idRequest": 12,
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


