#!/bin/sh
javac -d bin src/*.java src/board/*.java
java -cp bin Main
