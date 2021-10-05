#!/usr/bin/env bash

./gradlew build
./gradlew installDist
docker build -t ktor-rand .
docker run -p 8080:8080 ktor-rand