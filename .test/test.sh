#!/usr/bin/env bash
set -e

JUNIT_JAR=".test/junit-platform-console-standalone-6.0.3.jar"

#Remove class files
rm -f ./*.class ./.test/*.class

# Compile Java files
javac -encoding utf8 -classpath ".:${JUNIT_JAR}" .test/*.java *.java

#Run Unit Tests
java -Dfile.encoding=UTF8 -jar "${JUNIT_JAR}" execute --class-path ".:.test" --scan-class-path
