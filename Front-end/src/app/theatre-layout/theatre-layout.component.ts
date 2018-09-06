import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http"; // to access the get method for accessing the file data
import { HttpErrorResponse } from "@angular/common/http"; // for printing error message
import * as $ from "jquery";
import * as socket from '../../assets/socket.js' ;
import { BlockSeat } from '../models/SeatBlock';
import {PaymentService} from '../payment.service'
import {TheatreLayoutService} from '../theatre-layout.service';
var jquery: any;

@Component({
  selector: "app-theatre-layout",
  templateUrl: "./theatre-layout.component.html",
  styleUrls: ["./theatre-layout.component.css"]
})
export class TheatreLayoutComponent implements OnInit {
  // shownav=false;
  recievedBlockedSeats;
  duplicateId;
  showId;
  blockedSeat = new BlockSeat();
  seatingValue = [];
  totalRow = [];
  totalCol = [];
  jsonRow: any[];
  passage = [];
  rowPassage = [];
  public id: any[];
  public seatNum: any[]; // Final array to be sent to booking api

  // variable for retrieveing the map data from redis
  mapKey = [];
  mapKeyValue = [];
  x;
  showLayout:any;

  constructor(private httpService : HttpClient,private data :PaymentService , private theatrelayout : TheatreLayoutService) {}
  ngOnInit() {
    (window as any).connect();
    
    this.id = [];
    this.seatNum = [];
    this.mapKey=[];
    this.mapKeyValue=[];
    this.x = 0;

    this.id = [];
    this.seatNum = [];
    this.duplicateId = localStorage.getItem("duplicateId");
    if(this.duplicateId=="refresh") {
      window.location.href="/movieinfo";
    }

    this.showId = localStorage.getItem("showId");
    console.log("Show Id from movie-info " + this.showId);

    // function in service to get Show object
    this.theatrelayout.getShowlayout(this.showId).subscribe(data => {
      this.showLayout = data;
      // console.log("This is show Layout",this.showLayout);
      
      console.log(Object.keys(this.showLayout.seats).length);
      for(var i=0;i<Object.keys(this.showLayout.seats).length;i++){
        this.mapKey[i]=i;
        this.mapKeyValue[i]=this.showLayout.seats[i];
        //console.log("Key"+ this.mapKey[i] + " Value " + this.mapKeyValue[i]);
      }
    });
    
    
    this.httpService.get("./assets/layout.json").subscribe(
      data => {
        this.jsonRow = data as string[]; // FILL THE ARRAY WITH DATA.
        this.totalRow.length = this.jsonRow[0].totalRow; 
        this.totalCol.length = this.jsonRow[1].totalCol;
        this.totalRow = this.jsonRow[0].Values;
        this.totalCol = this.jsonRow[1].Values;
        
        // to get the passage columns
        this.passage = this.jsonRow[2].passageCol;
        this.rowPassage = this.jsonRow[3].passageRow;
        //console.log(this.rowPassage);
        
        this.createseating();
      },
      (err: HttpErrorResponse) => {
        console.log(err.message);
      }
    );
  }

  // to create seating layout
  createseating() {
    for (var i = 0; i < this.totalRow.length; i++) {
      for (var j = 0; j < this.totalCol.length; j++) {
        let seatingStyle = "<div class='seat available'></div>";
        this.seatingValue.push(seatingStyle);

        if(j== this.totalCol.length-1){
         console.log("Inside the break line loop");
          '<br/>';
        }
      }
    }

    //Hovering and Clicking effects on seats
    $(function() {
      $(".seat").on("click", function() {
        if ($(this).hasClass("selected")) {
          $(this).removeClass("selected");
          console.log("Seat Id: " + this.id + " is removed");
        } else {
          $(this).addClass("selected");
        }
      });

      // $(".seat").mouseenter(function() {
      //   $(this).addClass("hovering");

      //   $(".seat").mouseleave(function() {
      //     $(this).removeClass("hovering");
      //   });
      // });
    });
  }

  //  For adding a seat Id in an Array on every click
  onclick(x, y) {
    this.id.push(x * 10 + y);
    this.id.sort();
    //console.log(this.id);
   this.seatSelected();
  }

  // To make an array of filtered seats which are to be sent to booking API
  bookticket() {
    console.log("inside booked ticket");
    let map = new Map<String, number>();
    for (var i = 0; i < this.id.length; i++) {
      if (map.get(this.id[i])) {
        map.set(this.id[i], map.get(this.id[i]) + 1);
      } else {
        map.set(this.id[i], 1);
      }
    }
    console.log(map);
    map.forEach((value: number, key: String)=>{
      if(value%2 != 0){
        this.seatNum.push(key);
      }
    });
    this.blockedSeat.showId = this.showId;
    this.blockedSeat.seats = this.seatNum;
    this.blockedSeat.status = "blocked";
    this.data.changePayMessage(this.blockedSeat);
    (window as any).sendBlockedSeats(this.blockedSeat);
    console.log(this.seatNum);

    this.data.payMessage.subscribe(data => {
      this.blockedSeat = data;
      console.log("working sockets and all", data);
    });
   
  }

  // seatStatus()
  seatStatus(r, c) {
    var seatId = r * 10 + c;
    //console.log("Seat ID : " + seatId); 
    for (let i = 0; i < this.mapKey.length; i++) {
     // console.log(this.mapKey[i]);
      if (this.mapKey[i] == seatId) {
      //  console.log((this.mapKeyValue[i]);
        if (this.mapKeyValue[i] == "open") {
          return false;
        } else {
          return true;
        }
      }
    }
  }

  // seatBook()
  seatBook(r,c) {
    var seatId = r * 10 + c;
    for (let i = 0; i < this.mapKey.length; i++) {
      //console.log(this.mapKey[i]);
      if (this.mapKey[i] == seatId) {
        //console.log(this.mapKeyValue[i]);
        if (this.mapKeyValue[i] == "booked") {
          return true;
        } else {
          return false;
        }
      }
    }
  }

  seatSelected()
  {
    var count: number=1;
    var flag : boolean=false; 
    console.log("count"+count)
    console.log("length"+this.id.length);
    //console.log("Inside Seat Selected");
    console.log(this.id);
   for(var i=0;i<this.id.length;){
  
      //console.log("Inside first for loop");
      for(var j=i+1; j<this.id.length; j++){
          if(this.id[i]== this.id[j])
            {
                count++; // should be even
              // console.log("Count inside IF loop: " + this.count);
            }  
            else
            { 
             
              i=j;
              //console.log("Inside else block where i==j: " + i);
              break;
            }
      }

      // When j = length i.e for last sequence of values
      if(j==this.id.length && this.id[i]==this.id[j-1]){
        if(count%2!=0){
          console.log("Inside first count checker block")
          flag=true;
          
        }
        break;
       
      }

      // When j < length i.e for first sequence
      console.log("sankjncdjksackjnsaj :" + count)
      if(count%2!=0 ){
       console.log("jksankcjnsajcas")
        flag=true;
        break;
      }
      count=1; 
      console.log("Outside 2nd for"+ flag);
    }
    //console.log("SeatNum : " + this.seatNum);
    if(flag)
    return true;
    else
    return false;
  }
}
