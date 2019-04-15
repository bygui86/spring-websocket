
# Spring WebSocket

## Run

1. Start websocket server
	```shell
	cd websocket-server/
	mvnw clean spring-boot:run
	```

2. Open browser and visit `http://localhost:8080/`

---

## Endpoints

* spring-based
	* /echo
	* /multi
	* /broadcast
* javax-based
	* /reverse
	* /chat
* stomp-based
	* /greeting

---

## Features

- [x] text message
- [x] binary message
- [x] broadcasting
- [x] stomp message broker (server)
- [x] stomp-based client
- [x] javax-based client
- [ ] testing

---

## Links

### Guides
* https://spring.io/guides/gs/messaging-stomp-websocket/
#### Spring Session WebSocket
* https://docs.spring.io/spring-session/docs/current/reference/html5/guides/boot-websocket.html

### Tutorials
#### Spring based
* https://www.baeldung.com/rest-vs-websockets
#### Javax based
* https://www.baeldung.com/java-websockets
#### Text + Binary
* https://www.nexmo.com/blog/2018/10/08/create-websocket-server-spring-boot-dr/
#### Without STOMP
* https://www.devglan.com/spring-boot/spring-websocket-integration-example-without-stomp
#### With STOMP message broker
* https://www.devglan.com/spring-boot/spring-boot-websocket-integration-example
* https://www.devglan.com/spring-boot/spring-session-stomp-websocket
* https://www.baeldung.com/websockets-spring
* https://www.baeldung.com/websockets-api-java-spring-client
* https://stackoverflow.com/questions/30413380/websocketstompclient-wont-connect-to-sockjs-endpoint
#### @SendToUser
* https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket-stomp-user-destination

### Samples
* https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-websocket-jetty
