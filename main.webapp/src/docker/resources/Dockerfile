FROM java:openjdk-8-jre-alpine
MAINTAINER Nicholas Ver Voort <talkto@nicholasvv.com>

ARG version

#Add Tools
RUN apk update
RUN apk add --no-cache bash
RUN apk add --no-cache netcat-openbsd
RUN apk add --no-cache iputils
RUN apk add --no-cache sudo

RUN mkdir -p /opt/starchart/river

RUN adduser -D river_user
RUN chown river_user /opt/starchart/river
USER river_user

RUN mkdir -p /opt/starchart/river/bin
RUN mkdir -p /opt/starchart/river/logs

COPY libs/ /opt/starchart/river/libs
COPY entrypoint.sh /opt/starchart/river/bin/entrypoint.sh

EXPOSE 8080

WORKDIR /opt/starchart/river
ENTRYPOINT ["bin/entrypoint.sh"]
