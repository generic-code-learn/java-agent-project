@echo off

java -javaagent:./agent/target/agent-lib.jar -jar ./service/target/service-0.0.1-SNAPSHOT.jar
