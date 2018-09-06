var stompClient = null;
var bookedSeatsTemp = null;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    console.log("inside connect")
    var socket = new SockJS("http://13.232.40.6:9001/socket");
    console.log("after connect")
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat', function (blockedSeats) {
            console.log("Response: "+blockedSeats.body);
            var seats = JSON.parse(blockedSeats.body);
            var elementClass=seats.status;
            bookedSeatsTemp = seats;
            if(localStorage.getItem("showId")==seats.showId){
                seats.seats.forEach(element => {
                    id = element;
                    if(parseInt(element, 10)<10){
                        id = "0"+element;
                    }
                    if(elementClass=="open"){
                        $("#"+id).removeClass("blocked");
                        $("#"+id).removeClass("booked");
                    } else{
                        $("#"+id).removeClass("blocked");
                        $("#"+id).addClass(elementClass);
                        console.log("Hi i ma here: "+elementClass+" "+id);
                    }
                });
            }
        });
    });
}




function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    //bookedSeatsTemp = null;
    setConnected(false);
    console.log("Disconnected");
}

function sendBlockedSeats(blockedSeat) {
    if(blockedSeat.status!="blocked")
        blockedSeat.id = bookedSeatsTemp.id;
    console.log("Blocked Seats: " +  JSON.stringify(blockedSeat));
    stompClient.send("/app/send/message", {}, JSON.stringify(blockedSeat));
}

function sendBookedSeats() {
    blockedSeat = bookedSeatsTemp;
    blockedSeat.guestUserEmailId = localStorage.getItem("guestEmail");
    blockedSeat.status="open";
    blockedSeat.userName = localStorage.getItem("currentUser");
    console.log("Blocked Seats: " +  JSON.stringify(blockedSeat));
    stompClient.send("/app/send/message", {}, JSON.stringify(blockedSeat));
}

