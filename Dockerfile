FROM openjdk:8
COPY  ./target/Note-It-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java", "-jar", "Note-It-0.0.1-SNAPSHOT.jar"]