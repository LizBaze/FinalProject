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
| POST      | `/api/dogprofiles`    | JSON of a new `DogProfile` | JSON of created `DogProfile` |
| PUT       | `/api/dogprofiles/2` | JSON of a new version of `DogProfile` 2 | JSON of updated `DogProfile` |
| DELETE    | `/api/dogprofiles/2` |              | |


