#FROM maven:latest
#RUN mkdir /obo
#WORKDIR /obo
#COPY . .
#CMD [ "mvn", "spring-boot:run" ]


FROM adoptopenjdk/openjdk14:alpine
ADD target/pro-management-1.0.war app.war
ENTRYPOINT ["java","-jar","app.war"]