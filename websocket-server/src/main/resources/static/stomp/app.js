var stompClient;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/messages');
    stompClient = Stomp.over(socket);

    // username and password
    stompClient.connect({}, function (frame) {

        stompClient.subscribe("/topic/replies", function (msg) {
            // alert(msg);
            showGreeting(msg.body);
        });

        stompClient.subscribe("/topic/errors", function (msg) {
            // alert(msg);
            showGreeting(msg.body);
        });

        setConnected(true);
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var from = $("#name").val()
    var content = $("#message").val()
    var data = JSON.stringify({'from': from, 'content': content})
    stompClient.send("/app/message", {}, data);
}

function showGreeting(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});
