# Cake Manager App
Cake Manager App to build cakes

## Tech stack
> Spring boot
> 
> OAuth2
> 
> React

## Build & Run app in local
### Build the app
> mvn clean install 

(This will build and build both front end and back end)
(Please note the java, node and yarn version in pom.xml, change the version to match your local config)

### Run the app in bundled mode (spring boot + web)
> mvn spring-boot:run

### Web page to serve the frontend
> http://localhost:8080

### Run the front end app and back end app separately

####  Run the back-end
Run CakeManagerApplication as Spring Boot App to run the backend (port: 8080)

####  Run the front-end
From cake-manager-web dir run below command to start the web app
>npm start 

Access the app
> http://localhost:3000

### OAuth 2 setup
Please note that the app is authorized with OAuth2 setup against github server.

Bundled mode:
- While running in bundled mode it would automatically re-directed to github login page (if not already logged in)
- Once login is successful, it would re-directed back to the app

Running front-end and back-end separately
- Please go to http://localhost:8080 (which would redirect to github for authorization)
- Once logged in to github, app can be accesses via http://localhost:3000

### Features
- The home (root) page would display the lists of cakes in the system
- The top nav bar would display the github logo (leftmost) when user is logged in
- There is also a Download Cakes button to download the available cakes in system is json file
- Add Cake nav bar would allow user to input cake details and add to the system
- Once the cake is added, user can see the new cake in the home page

### Endpoints and Routes

###### Api Endpoints

- Get all cakes from system
> GET /api/v1/cakes
- Download cakes
> GET /api/v1/download-cakes
- Get cake by id
> GET /api/v1/cake/{id}
- Create cakes
> POST /api/v1/cakes
- Create cake
> POST /api/v1/cake
 
###### Routes

- List of cakes and download cakes
> /
- Add cake
> /cakes