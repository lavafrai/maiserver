FROM openjdk:17-jdk-alpine3.14

COPY . /opt/app/
WORKDIR /opt/app

# RUN apk update
# RUN apk upgrade
# RUN apk add --no-cache ffmpeg
# RUN apk add build-base linux-headers

RUN chmod +x gradlew
RUN ./gradlew --no-daemon --warning-mode all buildFatJar

# RUN rm -r /opt/tmp
WORKDIR /opt/app
CMD java -jar build/libs/maiserver-all.jar
