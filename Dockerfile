FROM openjdk
EXPOSE 8080
ADD build/libs/varann-0.0.1-SNAPSHOT.jar varann-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["postgres_db:5432", "--", "java", "-jar", "auth-0.0.1-SNAPSHOT.jar", "--spring.datasource.url=jdbc:postgresql://postgres_db:5432/varann"]
