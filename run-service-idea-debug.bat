@echo off

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 -javaagent:./agent/target/agent-lib.jar -jar ./service/target/service-0.0.1-SNAPSHOT.jar