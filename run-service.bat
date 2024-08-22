@echo off

java -javaagent:./java-agent/target/java-agent-jar-with-dependencies.jar -jar ./service/target/service-0.0.1-SNAPSHOT.jar
