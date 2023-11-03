# Basic User Management Server
This ZIP File contains the source code for the Basic User Management Server. The server provided two main Role of User, namely USER and ADMIN to control and manage their account. Examples will be provided in the following. By reading this README file, you will be able to:

* set up the docker image as well as container
* running the server in docker
* how to use the application.

# Prerequisites
Before running the server, please ensure you have installed the Docker application:

Installing Docker
For macOS User
```
  brew install docker --cask
```

For WindowOS User
```
  https://www.docker.com/products/docker-desktop
```

# Getting Start
To Run the Server, 

Step 1: Navigate to the documentary
```
  cd basic-usermanagement
```

Step 2: Build the docker image and Run the server
```
  docker-compose up -d
```

After entering the command, you will see:
![Diagram](./diagram.png?raw=true "Diagram")

The server starts when you see:
![Diagram](./diagram.png?raw=true "Diagram")

Check the docker application to see if the docker container starts successfully
![Diagram](./diagram.png?raw=true "Diagram")

Once you see all of the three container should contains the status of Running, server starts.

# Structure of the application server
This is only the backend system of the server. Therefore, before using the server to get the response, please download Postman to check the Request and Response of the REST APIs.

# USER
Role: Regular User (USER)
if you are a regular user, you are able to:

- 1: SignUp
(POST) If you are new to the application, please signUp for a registration.
URL: http://localhost:8181/v1/user/signUp
RequestBody: User (username, password, FullName, Gender, Date of Birth(Dob), Email, Address, Contact)

After signing up successfully, you will gain a token with a time limit of 15 minutes

- 2: Authentication (Login)
(POST) If your token has been expired, for safty issue, you are required to Login again for getting a new token.
URL: http://localhost:8181/v1/user/authenticate
RequestBody: UserSignUp Details (Username, Password)

After signing up successfully, you will gain a token with a time limit of 15 minutes

- 3: View My Own Profile
(GET) If you token is NOT expired, you can view your own profile
URL: http://localhost:8181/v1/user/profile?username={username}
RequestParam: username

After using the GET API, you will see your own Personal Information

- 4: Adjust My Own Profile
(PUT) If your token is NOT expired, you can adjust your personal information by this PUT API
URL: http://localhost:8181/v1/user/profile
RequestBody: User (username, password, FullName, Gender, Date of Birth(Dob), Email, Address, Contact)

After adjusting the personal information successfully, you will see you own Adjusted User Information

- 5: Delete My Account
(DELETE) If your token is NOT expired, you can delete your own account if needed. However, before deleting the account, you are required to validate your account by entering username and password for confirming deleting your account.
URL: http://localhost:8181/v1/user/delete
RequestBody: UserLogin(username, password)

- 6: Adjust My Password
(PATCH) If your token is NOT expired, you can change your password by this PATCH API
URL: http://localhost:8181/v1/user/changePassword
RequestBody: ChangePassword(Old Password, New password, New Password for confirmation)

- 7: Adjust My Email
(PATCH) If your token is NOT expired, you can change your email by this PATCH API
URL: http://localhost:8181/v1/user/adjust/user/{username}/email/{newEmail}
RequestBody: username, Email

- 8: Adjust My Address
(PATCH) If your token is NOT expired, you can change your address by this PATCH API
URL: http://localhost:8181/v1/user/adjust/user/{username}/address/{newAddress}
RequestBody: username, Address

# ADMIN
Role: Admin
Before the server starts, an admin account is installed in the server, you can manipulate the data through that account

Admin is built on top of User. Therefore, ADMIN is allowed to use all User API. The main functionality of ADMIN User will be listed as followed:

- 1: View All Users
(GET) If the token is NOT expired, Admin can view all user with this API
URL: http://localhost:8181/admin/users

After using this API, Admin can view all users as well as their authorities that what they are allowed to do

- 2: Authorize a User
(POST) If the token is NOT expired and Admin would like to upgrade or downgrade an account, Admin can adjust the user account by entering the username of the User and the role he/she is going to be.
URL: http://localhost:8181/v1/admin/authorize?username={username}&role={ADMIN}
RequestParam: username, role

After using this API, you can view the User Informaiton as well as the authorities that he contains after upgrading or downgrading.

- 3: Delete User
(DELETE) If the token is NOT expired, ADMIN is allowed to delete User account in their consideration
URL: http://localhost:8181/v1/admin/del?username={username}
RequestParam: username






