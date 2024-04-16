FROM openjdk:17-jdk-alpine3.14

COPY . /opt/app/
WORKDIR /opt/app

RUN chmod +x gradlew
RUN ./gradlew --no-daemon --warning-mode all buildFatJar

WORKDIR /opt/app
CMD java -jar build/libs/maiserver-all.jar
