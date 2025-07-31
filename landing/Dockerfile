FROM openjdk:22 AS builder

WORKDIR /opt/app
COPY . .
RUN sed -i 's/\r$//' gradlew
RUN ./gradlew --no-daemon --warning-mode all buildFatJar


FROM azul/zulu-openjdk-alpine:22-jre

WORKDIR /opt/app
COPY --from=builder /opt/app/build/libs/landing-all.jar ./landing-all.jar

CMD ["java", "-jar", "landing-all.jar"]