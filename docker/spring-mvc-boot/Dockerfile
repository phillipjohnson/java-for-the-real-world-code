FROM azul/zulu-openjdk:10
ADD build/libs/iscream-web-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
