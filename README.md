# match

A dummy spring boot app that offers basic APIs for matches.

## Consuming APIs - cUrl examples

#### Get all matches :

`curl --location 'http://localhost:8080/api/v1/matches' \
--header 'Accept: application/json'`

#### Get match by id :

`curl --location 'http://localhost:8080/api/v1/matches/2' \
--header 'Accept: application/json'`

#### Create new match with odds. Please note that API client does not have to provide identifiers :

`curl --location 'http://localhost:8080/api/v1/matches' \
--header 'Content-Type: application/json' \
--data '{
    "description": "Greek Derby Basketball 15",
    "matchDate": "30/08/2025",
    "matchTime": "20:34",
    "teamA": "PAO BC 2",
    "teamB": "AEK BC 2",
    "sport": "BASKETBALL",
    "odds": [
        {
            "specifier": "1",
            "odd": 1.87
        },
        {
            "specifier": "2",
            "odd": 1.53
        },
        {
            "specifier": "X",
            "odd": 2.0
        }
    ]
}'`

#### Update existing match by id :

`curl --location --request PUT 'http://localhost:8080/api/v1/matches/2' \
--header 'Content-Type: application/json' \
--data '{
    "id": 2,
    "description": "Greek Derby Basketball 2",
    "matchDate": "30/07/2025",
    "matchTime": "22:30",
    "teamA": "PAO BC",
    "teamB": "OSFP BC",
    "sport": "BASKETBALL",
    "odds": [
        {
            "id": 4,
            "specifier": "1",
            "odd": 1.67
        },
        {
            "id": 5,
            "specifier": "2",
            "odd": 1.83
        }
    ]
}'`

#### Delete single match by id :

`curl --location --request DELETE 'http://localhost:8080/api/v1/matches/3'
`

## Run App

You can simply run both the app and the required DB dockerized by executing:
`docker compose -f src/main/docker/docker-compose-full.yml up
`

In case you want to run the app separately as a spring boot app you can first run the db only by executing the :
`docker compose -f src/main/docker/docker-compose-db.yml up
`

and then run the app with maven using:
`mvn spring-boot:run
`

Either way, DB is set up using Flyway, based on the init script located in resources.