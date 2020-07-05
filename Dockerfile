FROM openjdk
EXPOSE 8080
ADD build/libs/varann-0.0.1-SNAPSHOT.jar varann-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/varann-0.0.1-SNAPSHOT.jar" ]
