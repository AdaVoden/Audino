# Audino

## Run instructions

Extract the release to a directory, then go into ```bin``` and run ```Audino``` for *Nix systems or ```Audino.bat``` for windows systems.

To run the textUI, open a terminal, go into the extracted or built bin directory and then run ```Audino --text-ui```

## Build Instructions

Clone into desired folder with

```git clone https://github.com/Gihoveg/Audino.git```

run

```./gradlew build``` 

The resulting output will be in your chosen folder /build

Distributable will be in /build/distributions

### Requirements
    Needs Java 1.8 and JavaFX 1.8 to run and build.
    
## Testing

To run tests, navigate into the cloned folder and run

```./gradlew test```

Which will run all tests and generate a test report in

```/build/reports/tests/test```

as well it will generate a coverage report in

```/build/reports/jacoco/test/html```

Test coverage in Player.java exceptionally poor due to difficulty testing callbacks

## Contributions
Written by Harlan:
App.java
Library.java
MetadataParser.java
Track.java
All state functions

Written by Aidan:
Playlist.java
Second iteration of GUI


Written by Ryan:
Player.java and first iteration of GUI

Written by Julian:
AudioController.java and assistance for all GUI elements

## Style Guide
Currently using the Twitter Java styleguide located here:
https://github.com/twitter-archive/commons/blob/master/src/java/com/twitter/common/styleguide.md
