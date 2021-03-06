FROM openjdk:8

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    git && \
    rm -rf /var/lib/apt/lists/ && \
    apt-get clean

# Support Gradle
ENV TERM dumb
ENV JAVA_OPTS "-Xms512m -Xmx1536m"
ENV GRADLE_OPTS "-XX:+UseG1GC -XX:MaxGCPauseMillis=1000"


WORKDIR /project

RUN git clone https://github.com/SaschaZ/IpInfo.git . && \
    ./gradlew assemble

CMD "build.sh"
