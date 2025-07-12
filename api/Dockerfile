FROM openjdk:22 AS builder

WORKDIR /opt/app
COPY . .
RUN sed -i 's/\r$//' gradlew
RUN ./gradlew --no-daemon --warning-mode all buildFatJar

FROM azul/zulu-openjdk-alpine:22-jre

WORKDIR /opt/app
COPY openapi/ ./openapi/
COPY --from=builder /opt/app/build/libs/maiserver-all.jar ./maiserver-all.jar

CMD ["java", "-jar", "maiserver-all.jar"]