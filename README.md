# Our Final Project
![community united (1)](https://user-images.githubusercontent.com/113270980/218788432-69d7155f-6eee-460b-840c-a4f428c09b5a.png)

# Overview 
Community United project is a volunteer site that promotes people from any community to join in volunteering events near them. Volunteering allows the public to connect to their communities and make it a better place. Even helping out with the smallest tasks can mean a real difference to the lives of people and organizations in need. 

The developers on this team were given a 9 day sprint to implement frontend and backend features onto the project in the most efficient way possible. 

### Events
When a user is first entering the site, they are welcomed to browse through all the events that are currently going on. They will be presented by a list of posts that give a title and a quick description of what the event is about. When a user clicks on a post, they are able to see the start and end date of the event, as well as the location that the event will be taking place. A button called join event will appear if the user wishes to join as a participant in the event. Before a user can join an event, they must register an account and sign in. If a user is unable to make it to the event, there is a leave event option for them to choose.

### Organizations
In the organization section, the user is prompted with a list of organizations they can be a part of. If a user is interested in starting an organization that they think would benefit the community, they can register an account and create an organization of their liking. The one that creates an organization will be an admin and will have permission to post volunteering events. 

## Group Messaging
Creating a social aspect in our project was vital as we understand that if the community were able to communicate with each other, then it will bring more awareness for more people to get involved and as a bonus create long lasting friendships within the community. When a user has an account, they will be able to join a volunteering event. Upon joining they will be entered into a group message chat involving all the members that will be attending that volunteering event. So if any members have any ideas or concerns about the event, they will be able to communicate with each other via chat. If a user would like to delete their message they will also be able to do so. 

### User

The user has different permission levels when they register. If a user is just registering as a volunteer participant, then they will not be able to edit or create events. But any user can create an organization. As a participant, the user is able to join as many events as they would like, they can also choose to leave an event if they decide too.

If a user is an admin member of the organization, they may choose to create as many events as they would like and be able to add a photo.

When the user creates an organization, they will automatically become an admin member where they will be allowed to create their own events as well as edit the event. 

When registering an account, the user will have their own account they can view and edit to add a more personalized touch to their account.


# Database Schema
![image](https://user-images.githubusercontent.com/113270980/218789044-2dd311f3-9f6f-4e91-93d8-c0aa0239c10b.png)


# Lesson Learned 
One of the lessons that the team learned early on was json recursion. Learning to utilize @JsonIgnoreProperties appropriately in our entities was very important as we had a lot of recursion happening. @JsonIgnoreProperties is a class-level annotation that marks a property or a list of properties that Json will ignore when read during deserialization. 

 >Example: 
We placed the @JsonIgnoreProperties in User, VolunteerEvent, Participant, Organization and Member Entities. 

Another JPA mapping that our team was no so familiar with was the Compossite Primary Key Mapping. We leanred that when we have an entity table that only consists of having multiple primary keys instead of just one primary key, we would have to join the tables differently. 

### Composite Primary Key Example
 ![image](https://user-images.githubusercontent.com/113270980/218830133-7cd2c2fd-23b5-4439-8c9e-ae10ce826630.png)
 <img width="109" alt="Screen Shot 2023-02-08 at 4 19 00 PM" src="https://user-images.githubusercontent.com/113270980/218830399-d5232b36-b0d8-44d9-9214-61159c922bb3.png">
 ![image](https://user-images.githubusercontent.com/113270980/218830540-8dd8c83d-048c-458a-92b7-a852c8286054.png)
 
 >Example: 
 As a team, we decided that a user will be able to participant in a volunteer event. So our participant entity table needed both a user_id and a event_id. We learned that we needed to as a @MapsId to both user_id and event_id, and each entity table was ampped as a many to many. 
Overall, it was good practice for each of us to understand why the composite key were needed in this instance and how to implement it with other composite keys in our schema.





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





