@echo off

java -javaagent:./java-agent/target/java-agent-lib.jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar ./service/target/service-0.0.1-SNAPSHOT.jar

