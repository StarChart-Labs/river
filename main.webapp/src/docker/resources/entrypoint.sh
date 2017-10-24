#!/bin/sh

## Allow debugging
if [[ $REMOTEDEBUG ]]; then
  JAVA_OPTS="${JAVA_OPTS} -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n"
fi

java ${JAVA_OPTS} -cp "/opt/starchart/river/libs/*" org.starchartlabs.river.main.webapp.River --spring.config.location=file:///mnt/config/overrides.properties
