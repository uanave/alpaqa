FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD target/alpaqa-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
# ENTRYPOINT ["java","-Dserver.port=$PORT","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
# Fire up our Spring Boot app by default
CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
