FROM openjdk:8
ADD target/demo-workshop-1.0.1.jar demo-workshop.jar
ENTRYPOINT ["java", "-jar", "demo-workshop.jar"]
# Commit Test1
# Commit Test2

