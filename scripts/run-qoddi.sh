#!/bin/bash
java -Dserver.port=$POST $JAVA_OPTS -Dspring.profiles.active=prod -jar ./build/libs/backend-0.0.1-SNAPSHOT.jar