FROM openjdk:25

COPY . /opt/app/
WORKDIR /opt/app

RUN apt-get update && apt-get install -y curl

RUN chmod +x gradlew
RUN ./gradlew --no-daemon --warning-mode all buildFatJar

WORKDIR /opt/app
CMD java -jar build/libs/maiserver-all.jar
