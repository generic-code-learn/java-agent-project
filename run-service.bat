@echo off

java -javaagent:./java-agent/target/java-agent-lib.jar -jar ./service/target/service-0.0.1-SNAPSHOT.jar
