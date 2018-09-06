import { Component } from "@angular/core";
import { AuthenticationService } from "./authentication.service";

declare var test: any;
@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent {
  // title = "E-Play";
  items: Array<any>=[];
  constructor(private authenticationService: AuthenticationService){
    this.items=[
      { name :'assets/images/thumb1.jpg'},
      { name :'assets/images/thumb2.jpg'}

    ];
  }
  f(){
    new test();
  }
}
