FROM openjdk:8-alpine

RUN apk add git

WORKDIR /project

RUN git clone https://github.com/SaschaZ/IpInfo.git . && \
    ./gradlew assemble

CMD git pull && ./gradlew run