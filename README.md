# Our Final Project
![community united (1)](https://user-images.githubusercontent.com/113270980/218788432-69d7155f-6eee-460b-840c-a4f428c09b5a.png)


# Overview 

# Database Schema
![image](https://user-images.githubusercontent.com/113270980/218789044-2dd311f3-9f6f-4e91-93d8-c0aa0239c10b.png)


# Lesson Learned 

# Technologies Used

# Our Team

# REST API Mappings

## Authentication Entity 

| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/authenticate`    |               | Authenticates user email and password when logging in
| POST       | `/api/register` | Request the new user's information     | Generates a new user when registering into the database |
| PUT       | `/api/user/1` |  Request's a new version of the user | Updated user's information to the database |
| DELETE    | `/api/user/1` |              | Deleted the user from the database|

## Organization Entity

| HTTP Verb | URI                  | Request Body | Response Body |
|-----------|----------------------|--------------|---------------|
| GET       | `/api/organizations`    |               | Retrieves a collection of all exsisting organizations
| GET       | `/api/organizations/1/users/1` |    | Retrieves a boolean to check whether a user is an admin for that organization|
| GET       | `/api/organizations/1` |  Retreives an organization by id |
| POST    | `/api/organizations` | Request of a new organization while user is logged in            | Generates a new organization into the database|
| POST       | `/api/organizations/1/users` |  Retreives organization by id and user by email | Add's the user as a member to the organization  |
| PUT       | `/api/organizations/1` |  Request of a new version of an organization | Updated organization information to the database
| DELETE       | `/api/organizations/1/users` |   |  Removes user by email from organization id        |



