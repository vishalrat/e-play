import { Component, OnInit } from "@angular/core";
import { SearchDataService } from "../search-data.service";

@Component({
  selector: "app-landing-page",
  templateUrl: "./landing-page.component.html",
  styleUrls: ["./landing-page.component.css"]
})
export class LandingPageComponent implements OnInit {
  message: string;
  constructor(private data: SearchDataService) {}
  ngOnInit() {}
  onclick1() {
    console.log("Location : Delhi");
    localStorage.setItem("city", "Patna");
    this.data.changeMessage("Patna");
    this.data.changeMessage("delhi");
    localStorage.setItem("city", "Delhi");
    localStorage.removeItem("rec");
  }
  onclick2() {
    console.log("Location : Mumbai");
    localStorage.setItem("city", "Patna");
    this.data.changeMessage("mumbai");
    localStorage.setItem("city", "Mumbai");
    localStorage.removeItem("rec");
  }
  onclick3() {
    console.log("Location : Bangalore");
    localStorage.setItem("city", "Patna");
    this.data.changeMessage("banglore");
    localStorage.setItem("city", "Bangalore");
    localStorage.removeItem("rec");
  }
  onclick4() {
    console.log("Location : Hyderabad");
    localStorage.setItem("city", "Hyderabad");
    this.data.changeMessage("hyderabad");
    localStorage.setItem("city", "Hyderabad");
    localStorage.removeItem("rec");
  }
  onclick5() {
    console.log("Location : Chennai");
    localStorage.setItem("city", "Chennai");
    this.data.changeMessage("chennai");
    localStorage.setItem("city", "Chennai");
    localStorage.removeItem("rec");
  }
  onclick6() {
    console.log("Location : Pune");
    localStorage.setItem("city", "Pune");
    this.data.changeMessage("pune");
    localStorage.setItem("city", "Pune");
    localStorage.removeItem("rec");
  }
}
