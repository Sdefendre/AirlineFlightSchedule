#!/bin/bash

# Attempt to find JUnit and Hamcrest JARs
JUNIT_JAR=$(find /usr/share/java /usr/share/gradle* -name "junit-4*.jar" 2>/dev/null | head -n 1)
HAMCREST_JAR=$(find /usr/share/java /usr/share/gradle* -name "hamcrest-core*.jar" 2>/dev/null | head -n 1)

if [ -z "$JUNIT_JAR" ] || [ -z "$HAMCREST_JAR" ]; then
    echo "Error: JUnit or Hamcrest JAR not found."
    echo "Please ensure they are installed and in the search path."
    exit 1
fi

CLASSPATH=.:$JUNIT_JAR:$HAMCREST_JAR

echo "Cleaning..."
rm -f AirlineFlightSchedule/*.class
rm -f test/AirlineFlightSchedule/*.class

echo "Compiling..."
javac AirlineFlightSchedule/*.java
if [ $? -ne 0 ]; then echo "Main compilation failed"; exit 1; fi

javac -cp "$CLASSPATH" test/AirlineFlightSchedule/*.java
if [ $? -ne 0 ]; then echo "Test compilation failed"; exit 1; fi

echo "Running tests..."
java -cp "$CLASSPATH:test" org.junit.runner.JUnitCore AirlineFlightSchedule.FlightScheduleTest
