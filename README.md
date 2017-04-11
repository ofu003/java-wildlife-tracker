## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Setup and Installation Requirements

Clone this [repository](https://github.com/ofu997/java-wildlife-tracker) and navigate to it in a terminal window.

In a second terminal window, run $postgres.

In a third tab, run $psql, then #CREATE DATABASE wildlife_tracker.

In the first tab, type $psql wildlife_tracker < wildlife_tracker.sql

In the third tab type #\c wildlife_tracker to connect to the database, then # CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker.

In the first tab enter $ gradle run.

Go to "localhost:4567" in a browser.




### License

Copyright (c) 2017 **_MIT License_**
