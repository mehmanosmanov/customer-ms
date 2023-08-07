FROM openjdk
WORKDIR /home
COPY build/libs/customer-ms-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/home/customer-ms-0.0.1-SNAPSHOT.jar"]