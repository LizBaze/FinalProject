# Our Final Project
![community united (1)](https://user-images.githubusercontent.com/113270980/218788432-69d7155f-6eee-460b-840c-a4f428c09b5a.png)


# Overview 

# Database Schema
![image](https://user-images.githubusercontent.com/113270980/218789044-2dd311f3-9f6f-4e91-93d8-c0aa0239c10b.png)


# Lesson Learned 

# Technologies Used
- Java
- MySQL / MYSQL workbench
- JPA
- REST API
- JPA
- Gradle
- XML
- JavaScript
- TypeScript
- JSON
- Angular
- MAMP
- Tomcat 
- AWS
- HTML
- CSS
- Bootstrap
- Visual Code Studio
- SpringToolSuite4
- PostMan


# Our Team
## Liz Base 
### Software Developer | GitHub Repository Owner 
### https://github.com/LizBaze
## Casey Froehlich
### Software Developer | Database Administator 
### https://github.com/CaseyFroehlich7
## John Nichols
### Software Developer | Scrum Master
### https://github.com/jtn0113
## Pamela Leon
### Software Developer
### https://github.com/pamelaleon




# REST API Mappings

## Authentication Entity 

| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/authenticate`    |               | Authenticates user email and password when logging in
| POST       | `/api/register` | Request's the new user's information     | Generates a new user when registering into the database |
| PUT       | `/api/user/1` |  Request's a new version of the user | Updated user's information to the database |
| DELETE    | `/api/user/1` |              | Deleted the user from the database|


## Organization Entity

| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/organizations`    |               | Retrieves a collection of all exsisting organizations
| GET       | `/api/organizations/1/users/1` |    | Retrieves a boolean to check whether a user is an admin for that organization|
| GET       | `/api/organizations/1` |  Retreives an organization by id |
| POST    | `/api/organizations` | Request's a new organization while user is logged in            | Generates a new organization into the database|
| POST       | `/api/organizations/1/users` |  Retreives organization by id and user by email | Add's the user as a member to the organization  |
| PUT       | `/api/organizations/1` |  Request's a new version of an organization | Updated organization information to the database
| DELETE       | `/api/organizations/1/users` |   |  Removes user by email from organization id        |


## Volunteer Event Entity

| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/volunteerevents`    |               | Retrieves a collection of all exsisting volunteer events
| GET       | `/api/volunteerevents/1` | | Retrieves a volunteer event by id |
| POST       | `/api/organizations/1/volunteerevents` |  Request's a new version of volunteer events by organization id | Generates a new volunteer event into the database |
| PUT    | `/api/volunteerevents/1` |  Request's a new version of volunteer event           | Updated volunteer event information into the database|

## Participant Entity

| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| POST      | `/api/participants/1`    |  Request's the user by email to be added as a participant   | Generates a new participant for that volunteer event
| DELETE      | `/api/volunteerevents/1/users` | | Removes participant from the volunteer event |


## Group Message Entity 

| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/volunteerevents/1/groupmessages`    |               | Retrieves a collection of all group messages by volunteer event id
| POST       | `/api/volunteerevents/1/groupmessages` | Request's the user by email and the volunteer event by id| | Generates a new group message by user to that volunteer event into the database
| DELETE      | `/api/groupmessages/1` | | Deletes a group message by id |





