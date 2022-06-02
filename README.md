# UniversityBoot with kafka

## RUN application:

#### _mvn clean install_

### Run rest application
#### _cd rest-application_
#### _./mvnw spring-boot::run_

### Run web application
#### _cd web-application_
#### _./mvnw spring-boot::run_
### 
#### http://localhost:8088/lectors


Click button : __Create Schedule__
After you can to update data, insert or delete
After all changes you must to create schedule anymore

_____________________________________________
# __Endpoints__
## lectors
### Get all lectors:
#### GET: http://localhost:8090/lectors/get-all

### Get lector by the nameLector:
#### GET: http://localhost:8090/lectors/lector/get-name

### Get lector by the emailLector:
#### GET: http://localhost:8090/lectors/lector/get-email

### Get lector by the idLector:
#### GET: http://localhost:8090/lectors/lector/{idLector}

### Create new lector :
#### POST: http://localhost:8090/lectors/lector/new
#### for example: @RequestBody {"nameLector": "Mike","loginLector": "mike","passwordLector": "1111","emailLector": "mike@tyson.com"}

### Update lector :
#### PUT: http://localhost:8090/lectors/lector/update
#### for example: @RequestBody {"idLector": "1",""nameLector": "Mike","loginLector": "mike","passwordLector": "1111","emailLector": "mike@tyson.com"}

### Delete lector by idLector :
#### DELETE: http://localhost:8090/lectors/lector/{id}/delete
#### for emample:  http://localhost:8090/lectors/lector/1/delete

### Delete lector by Lector :
#### DELETE: http://localhost:8090/lectors/lector/delete
#### for emample: @RequestBody {"idLector": "1",""nameLector": "Mike","loginLector": "mike","passwordLector": "1111","emailLector": "mike@tyson.com"} 



## Groups
### Get all groups:
#### GET: http://localhost:8090/groups/get-all

### Get all names of the groups:
#### GET: http://localhost:8090/groups/get-all-names

### Get group by the name:
#### GET: http://localhost:8090/groups/group/get-name
#### @RequestParam String name 
#### for example :  http://localhost:8090/groups/group/get-name?name=e1

### Create new group:
#### POST: http://localhost:8090/groups/group/new
#### @RequestBody String newName
#### for example :  e2

### Update group:
#### PUT: http://localhost:8090/groups/group/update
#### @RequestBody List <String> names [{"newName": "?"}, {"oldName": "?""}] 
#### for example :  [{"newName": "q1"}, {"oldName": "e1""}]

### Delete group:
#### DELETE: http://localhost:8090/groups/group/delete
#### @RequestBody String name
#### for example : e1


## ReuestsFromLector
### Get all requests from lector:
#### GET: http://localhost:8090/lectors/lector/{id}/requests/all
#### {id} : idLector
#### for example : http://localhost:8090/lectors/lector/1/requests/all

### Get request from lector:
#### GET: http://localhost:8090/lectors/lector/request/{id}
#### {id} : idRequest
#### for example : http://localhost:8090/lectors/lector/request/12

### Update request from lector:
#### PUT: http://localhost:8090/lectors/lector/request/update
#### @RequestBody:
#### {"idRequest": 6, "group": "e1", "numberOfPairs": "2", "subjectOfLector": "fizo", "idLector": 1, "date": "2022-03-14T17:28:49.263+00:00"}

### Delete request from lector:
#### PUT: http://localhost:8090/lectors/lector/request/delete
#### @RequestBody:
#### {"idRequest": 6, "group": "e1", "numberOfPairs": "2", "subjectOfLector": "fizo", "idLector": 1, "date": "2022-03-14T17:28:49.263+00:00"}

## Schedule  
### Create schedule from the lectors requests
#### GET: http://localhost:8090/schedule/create

### Get schedule for all lectors 
#### GET: http://localhost:8090/schedule/alllectors

### Get schedule for all groups
#### GET: http://localhost:8090/schedule/allgroups

### Get schedule for lector
#### GET: http://localhost:8090/schedule/alllectors
#### @RequestParam String lectorName
#### for example: http://localhost:8090/schedule/alllectors?lectorName=Mike

### Get schedule for group
#### GET: http://localhost:8090/schedule/group
#### @RequestParam String groupName
#### for example: http://localhost:8090/schedule/group?groupName=e1
