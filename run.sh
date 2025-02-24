#!/bin/sh
javac -d bin src/*.java src/game/*.java src/IO/*.java
java -cp bin Main
