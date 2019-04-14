
# Spring WebSocket

## Run

1. Start websocket server
	```shell
	cd websocket-server/
	mvnw clean spring-boot:run
	```

2. Open browser and visit `http://localhost:8080/`

---

## Features

- [x] text message
- [x] binary message
- [ ] pong message
- [x] broadcasting
- [ ] message broker (spring session ?)
- [ ] java client (@ClientEndpoint ? see https://www.baeldung.com/java-websockets but no examples)
- [ ] testing

---

## Links

### Guides
* https://spring.io/guides/gs/messaging-stomp-websocket/
#### Spring Session WebSocket
* https://docs.spring.io/spring-session/docs/current/reference/html5/guides/boot-websocket.html

### Tutorials
* https://www.baeldung.com/websockets-spring
* https://www.baeldung.com/websockets-api-java-spring-client
* https://www.baeldung.com/rest-vs-websockets
#### Java APIs (No Spring)
* https://www.baeldung.com/java-websockets
#### Text + Binary
* https://www.nexmo.com/blog/2018/10/08/create-websocket-server-spring-boot-dr/
#### Broadcasting
* https://www.devglan.com/spring-boot/spring-websocket-integration-example-without-stomp

### Samples
* https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-websocket-jetty
