# Solution: neshant

**Date:** 8th August 2019

## Summary

The solution leverages already available solutions built for the purpose.
 
 Tools and languages used -
 1) Kotlin 
 2) SpringBoot Framework for REST API
 3) Spring data JPA for CRUD Repository
 4) Gradle as a build tool
 5) Postgres for persistence
 6) Liquibase for table creation and data migration
 7) Docker for Postgres
 
The csv file is loaded automatically in Postgres as a one off task by Liquibase which takes couple of minutes on first start of application.

## Requirements

 - Java 8 
 - Gradle (included)

## Usage

1) Execute the following script present in solution folder

```./gradlew clean build``` 

2) Run the application

```java -server -jar ./build/libs/ufosighting.jar```

## Answers

For each answer, copy/paste the execution of a simple `curl` command against your HTTP service and with its output
and time execution.

#### Example

An example section for Question #1 could be imagined as:

---

1) How many different ships will be attacking?
```bash
⇒  time curl http://localhost:5000/ufo/sightings/count
{
  "count": 80332
}
curl http://localhost:5000/ufo/sightings/count  0.01s user 0.01s system 14% cpu 0.120 total
```

---

1) How many sightings are there?

```bash
time curl -X GET 'http://localhost:8080/ufo-sightings/count' | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    15    0    15    0     0    522      0 --:--:-- --:--:-- --:--:--   535
{
  "count": 80332
}
curl -X GET 'http://localhost:8080/ufo-sightings/count'  0.01s user 0.03s system 35% cpu 0.101 total
jq .  0.06s user 0.01s system 60% cpu 0.102 total
```

2) How many different ships will be attacking?

```bash
time curl -X GET 'http://localhost:8080/ufo-sightings/unique-shape' | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    12    0    12    0     0    114      0 --:--:-- --:--:-- --:--:--   115
{
  "count": 29
}
curl -X GET 'http://localhost:8080/ufo-sightings/unique-shape'  0.01s user 0.01s system 15% cpu 0.133 total
jq .  0.06s user 0.00s system 51% cpu 0.132 total
```

3) What are the Top-10 cities in the United States that should be evacuated first?

```bash
time curl -X GET 'http://localhost:8080/ufo-sightings/vulnerable-city?count=10' | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   331    0   331    0     0   2850      0 --:--:-- --:--:-- --:--:--  2829
{
  "sightings": [
    {
      "count": 525,
      "city": "seattle"
    },
    {
      "count": 454,
      "city": "phoenix"
    },
    {
      "count": 374,
      "city": "portland"
    },
    {
      "count": 368,
      "city": "las vegas"
    },
    {
      "count": 353,
      "city": "los angeles"
    },
    {
      "count": 338,
      "city": "san diego"
    },
    {
      "count": 297,
      "city": "houston"
    },
    {
      "count": 265,
      "city": "chicago"
    },
    {
      "count": 241,
      "city": "tucson"
    },
    {
      "count": 239,
      "city": "miami"
    }
  ]
}
curl -X GET 'http://localhost:8080/ufo-sightings/vulnerable-city?count=10'  0.01s user 0.02s system 15% cpu 0.172 total
jq .  0.04s user 0.01s system 28% cpu 0.171 total
```

4) If our secret Area-52 base was to be attacked, where would it come from?

```bash
time curl -X GET 'http://localhost:8080/ufo-sightings?latitude=46.5476&longitude=%20-87.3956&count=3' | jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1197    0  1197    0     0    815      0 --:--:--  0:00:01 --:--:--   815
{
  "sightings": [
    {
      "id": 10267,
      "state": "mi",
      "description": "Light object in sky dims then moves away towards downtown after hovering for several minutes.",
      "shape": "light",
      "country": "us",
      "occurred_at": "11/29/2011 03:35",
      "city": "marquette",
      "duration_seconds": "240",
      "duration_text": "4 min",
      "reported_on": "12/12/2011",
      "latitude": 46.5436111,
      "longitude": -87.3952778,
      "distance": 444.7292
    },
    {
      "id": 14024,
      "state": "mi",
      "description": "Something awakened me. When I looked out of the deck window at around 4:00 a.m.&#44 I was surprised to see a red flashing light. I live",
      "shape": "sphere",
      "country": "us",
      "occurred_at": "7/31/2008 16:00",
      "city": "marquette",
      "duration_seconds": "900",
      "duration_text": "15 minutes",
      "reported_on": "8/12/2008",
      "latitude": 46.5436111,
      "longitude": -87.3952778,
      "distance": 444.7292
    },
    {
      "id": 24652,
      "state": "mi",
      "description": "I was on the second floor deck&#44 looked up for stars&#44 craft went right over me&#44 just above tree tops&#44 no lights&#44 covered mile to power c",
      "shape": "egg",
      "country": "us",
      "occurred_at": "8/15/1991 22:45",
      "city": "marquette",
      "duration_seconds": "2",
      "duration_text": "seconds",
      "reported_on": "11/1/1998",
      "latitude": 46.5436111,
      "longitude": -87.3952778,
      "distance": 444.7292
    }
  ]
}
curl -X GET   0.01s user 0.01s system 1% cpu 1.486 total
jq .  0.08s user 0.00s system 5% cpu 1.487 total
```


5) successful potential hits within 120 Kms and greater than 42 seconds?

```
bash-3.2$ curl -X GET 'http://localhost:8080/ufo-sightings/successful-hits' |jq .
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   455    0   455    0     0    277      0 --:--:--  0:00:01 --:--:--   277
{
  "targets": [
    {
      "count": 19,
      "shape": "light"
    },
    {
      "count": 7,
      "shape": "triangle"
    },
    {
      "count": 5,
      "shape": "circle"
    },
    {
      "count": 5,
      "shape": "disk"
    },
    {
      "count": 4,
      "shape": "other"
    },
    {
      "count": 3,
      "shape": "fireball"
    },
    {
      "count": 3,
      "shape": "diamond"
    },
    {
      "count": 3,
      "shape": "unknown"
    },
    {
      "count": 2,
      "shape": "sphere"
    },
    {
      "count": 2,
      "shape": "formation"
    },
    {
      "count": 1,
      "shape": "cigar"
    },
    {
      "count": 1,
      "shape": "oval"
    },
    {
      "count": 1,
      "shape": "rectangle"
    },
    {
      "count": 1,
      "shape": "flash"
    },
    {
      "count": 1,
      "shape": "changing"
    }
  ]
}
```
## Ambiguity Notes

{Add notes here if you were unsure about any questions and what specific implementation choices were made.}
