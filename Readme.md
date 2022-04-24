## Not ready

---

## TO DO
make datasource properties autoconfigurable from application.yml

### in progress
* make user and events full immutable
* add spring framework:
  - should we always do new rowmappers or we can instance it in class
  - public List<Event> getCurrentEvents(LocalDateTime currentTime) without datetime formatter
  - take out password from returns and rowmappers
* change H2 database to PostgreSQL oe some NoSQL
* add tests
* integrate in docker container


### rework and reorganize frontend: 
* make frontend adaptive & responsive
* change to some JS framework
* change buttons in profile while logged in

### add functionality:
* logout 
* encrypt passwords
* check legit sign up data
* spring security 
* email password restore
* delete events
* subscribe on events
* set likes on events
* comments
* visit others profiles
* load pictures on profile and event
* Message with short videos
* load videos on events and prfiles
* open event as full page
* chatting between users

### to fix
* sign up
* profile -> sign in -> profile

### try devops
* add monitoring spring or grafana
* rabbit

## Voluntarius

Simple website to create and publish events

## Run

mvn clean install  
docker build . -t [name]  
docker run [name]

