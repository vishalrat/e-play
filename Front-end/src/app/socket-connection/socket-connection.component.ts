import { Component } from "@angular/core";

@Component({
  selector: "app-socket-connection",
  templateUrl: "./socket-connection.component.html",
  styleUrls: ["./socket-connection.component.css"]
})
export class SocketConnectionComponent {
  greetings: string[] = [];
  showConversation: boolean = false;
  ws: any;
  name: string;
  disabled: boolean;

  constructor() {}
}