# Demo Project for Online Stream #8 - Async Microservices Collaboration with Kafka

Demo project for online stream #8 about asynchronous collaboration between microservices using Kafka messages

## Access to Online Stream on Youtube

To get a link to online stream on Youtube please do the following:

- :moneybag: Make any donation to support my volunteering initiative to help Ukrainian Armed Forces by means described on [my website](https://www.yuriytkach.com/volunteer)
- :email: Write me an [email](mailto:me@yuriytkach.com) indicating donation amount and time
- :tv: I will reply with the link to the stream on Youtube.

Thank you in advance for your support! Слава Україні! :ukraine: 

## Demo Apps Structure

There are two projects that communicate with each other using Kafka messages. Each project is implemented with different technologies to showcase.

To start up Kafka broker please use the provided docker-compose file.

- `docker-compose.yml` - Docker-compose file with Kafka broker and Zookeeper. Use `docker-compose up .` command to start it.
- `spring-boot-app` - Application written with Spring Boot that listens to Kafka messages, process them and sends result to another topic.
- `quarkus-app` - Application written with Quarkus that has REST endpoint to send messages to Kafka, listen to them, process and send to another topic.


