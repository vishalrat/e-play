import { Component, OnInit } from "@angular/core";
import { BlockSeat } from "../models/SeatBlock";
import { PaymentService } from "../payment.service";
import { Observable } from "rxjs";
import { AlertsService } from 'angular-alert-module';
import * as $ from "jquery";
import { Router } from '@angular/router';
import { PlatformLocation } from '@angular/common'

export interface Tile {
  poster: string;
  cols: number;
  rows: number;
  text: string;
  color: string;
  price: number;
  count: number;
  overflow:string;
  amount:number;
  border:string;
  position:string;

}

@Component({
  selector: "app-payment-page",
  templateUrl: "./payment-page.component.html",
  styleUrls: ["./payment-page.component.css"]
})
export class PaymentPageComponent implements OnInit {
  tiles: Tile[] = [
    {
      text: "Large Salted Popcorn",
      cols: 1,
      rows: 3,
      poster:
        "https://in.bmscdn.com/bmsin/v2/Web-v2/d-combo/1020001_13082018125322.jpg",
      color: "white",
      price: 280,
      count: 1,
      overflow:"",
      amount: 0,
      border:"",
      position:""

    },
    {
      text: "Couple Combo",
      cols: 1,
      rows: 3,
      poster:
        "https://in.bmscdn.com/bmsin/v2/Web-v2/d-combo/1020005_17082018144820.jpg",
      color: "white",
      price: 570,
      count: 1,
      overflow:"",
      amount: 0,
      border:"",
      position:""
    },
    {
      text: "Regular Combo",
      cols: 1,
      rows: 3,
      poster:
        "https://in.bmscdn.com/bmsin/v2/Web-v2/d-combo/1020007_16082018153109.jpg",
      color: "white",
      price: 380,
      count: 1,
      overflow:"",
      amount: 0,
      border:"",
      position:""
    },
    { text: "two", cols: 1.8, rows: 7, poster: "", color: "white", price: 0, count: 0,overflow:"scroll", amount: 0,border:"ridge",position:"" },
    //{text: '', cols: 1, rows: 7,poster:'', color: ''},
    {
      text: "Nachos with Salsa",
      cols: 1,
      rows: 3,
      poster:
        "https://in.bmscdn.com/bmsin/v2/Web-v2/d-combo/1020006_06082018135441.jpg",
      color: "white",
      price: 250,
      count: 1,
      overflow:"",
      amount: 0,
      border:"",
      position:""
    },
    {
      text: "Veg Burger",
      cols: 1,
      rows: 3,
      poster:
        "https://in.bmscdn.com/bmsin/v2/Web-v2/d-combo/1020016_17082018120212.jpg",
      color: "white",
      price: 150,
      count: 1,
      overflow:"",
      amount: 0,
      border:"",
      position:""
    },
    {
      text: "Sandwich",
      cols: 1,
      rows: 3,
      poster:
        "https://in.bmscdn.com/bmsin/v2/Web-v2/d-combo/1020446_20082018182121.jpg",
      color: "white",
      price: 150,
      count: 1,
      overflow:"",
      amount: 0,
      border:"",
      position:""
    }
    // {text: 'Four', cols: 2, rows: 1, color: '#DDBDF1'},
  ];
  noSeats = 0;
  user: any;
  emailId: any;
  paymentStatus: BlockSeat;
  price = 0;
  totalAmount = 0;
  status: any;
  items = [];
  tax = 0;
  food = 0;
  subTotal =0;
  tile1 : Tile;
  //itemCount=1;
  flag:any;
  clicked:any;
  intervalId = 0;
  timer = "";
  seconds = 59;
  minutes = 0;
  handler;
  duplicateId;

  constructor(private data: PaymentService, private alerts: AlertsService, private router: Router, location: PlatformLocation) {
    location.onPopState(() => {
      window.location.href="/movieinfo";
      console.log('Back button pressed!');
  });
  }
  


  
  ngOnInit() {
    localStorage.setItem("paymentStatus", "none");
    console.log("id: "+this.duplicateId);
    this.duplicateId = localStorage.getItem("duplicateId");
    console.log("id after: "+this.duplicateId);
    if(this.duplicateId=="refresh") {
      console.log("id: "+this.duplicateId);
      window.location.href="/movieinfo";
    }
    localStorage.setItem('duplicateId','refresh');
    this.start();
    this.paymentStatus = new BlockSeat();
    this.user = localStorage.getItem("currentUser");
    console.log(localStorage.getItem("currentUser"));
    this.data.payMessage.subscribe(message => (this.paymentStatus = message));
    console.log("pay and seats", this.paymentStatus);
    this.noSeats = this.paymentStatus.seats.length;
    this.price = this.noSeats * 200;
    this.tax = ( this.noSeats * 200 * .18);
    this.subTotal = ( this.noSeats * 200 )+ this.tax; 
    this.totalAmount = (this.subTotal)+(this.food); 
    
  }

  openCheckout() {
    var amount=this.totalAmount*100;
    localStorage.setItem("guestEmail", this.emailId);
    this.handler = (<any>window).StripeCheckout.configure({
      key: 'pk_test_K2C3smobEhEs1C5VDNs9iXn6',
      locale: 'auto',
      currency: 'INR',
      token: function (token: any) {
        // You can access the token ID with `token.id`.
        // Get the token ID to your server-side code for use.
        console.log(token)
        $.ajax({
            url: "http://13.232.40.6:9001/api/v1/charge",
            type: 'post',
            data: {id:token.id,amount:amount},
            success: function() {
              localStorage.setItem("paymentStatus", "success");
            },
            error: function() {
              localStorage.setItem("paymentStatus", "fail");
            }
          }); // end ajax call
      }
    });
    
    this.handler.open({
      name: 'Payment Gateway',
      amount: (this.totalAmount*100)
    });
    
  }

  onClickFail() {
    this.clicked=true;
    this.flag= true; 
    this.data.payMessage.subscribe(message => (this.paymentStatus = message));
    console.log("earlier payMessage", this.paymentStatus);
    this.paymentStatus.userName = localStorage.getItem("currentUser");
    this.paymentStatus.guestUserEmailId = this.emailId;
    this.paymentStatus.status = "open";
    console.log("payment staus", this.paymentStatus);
    if((window as any).bookedSeatsTemp!=null)
      this.data.sendStatus(this.paymentStatus);
    (window as any).disconnect();
    this.alerts.setMessage('Payment failed. Your card is not supported.','error');
  }

  onClickSuccess() {
    this.clicked=true;
    this.flag= true;
    this.alerts.setMessage('Seat No: '+JSON.stringify(this.paymentStatus.seats)+' booked successfully','success');
    this.data.payMessage.subscribe(message => (this.paymentStatus = message));
    console.log("earlier payMessage", this.paymentStatus);
    this.paymentStatus.userName = localStorage.getItem("currentUser");
    this.paymentStatus.guestUserEmailId = this.emailId;
    this.paymentStatus.status = "booked";
    console.log("payment status", this.paymentStatus);
    if((window as any).bookedSeatsTemp!=null)
      this.data.sendStatus(this.paymentStatus);
    (window as any).disconnect();
    
  }

  itemsAdded(it) {
    //this.items=items;
    if (this.items.length < 6 && !(this.items.indexOf(it)>-1)) {
      this.items.push(it);
      it.amount = (it.count)*(it.price);
      this.foodPrice();
      console.log(JSON.stringify(this.items));
    }
    if (this.items.length > 6) {
      console.log("Max 6 items allowed");
    }
  }

  minusItem(i,itemCount) {
    
    // if(this.items[i].count < 1) {
    //   this.items.splice(this.items.indexOf(this.items),1);
    //   this.items[i].count=1;
    // }
    // items.count -= 1;
    // console.log(items.count);
    this.items[i].count= itemCount - 1;
    this.items[i].amount = (this.items[i].count)*(this.items[i].price);
    this.foodPrice();
    if(this.items[i].count < 1) {
      this.items[i].count=1;
      this.items.splice(this.items.indexOf(this.items),1);
    }
  }

  addItem(i,itemCount) {
    console.log("Index" + i);
    this.items[i].count = itemCount + 1;
    console.log(this.items[i]);
    console.log(this.items);
    this.items[i].amount = (this.items[i].count)*(this.items[i].price);
    this.foodPrice();
  }

  deleteItem(i,items) {
    this.items[i].amount=0;
    this.items[i].count=1;
    this.foodPrice();
    this.items.splice(this.items.indexOf(items),1);
  }

  foodPrice() {
    this.food=0;
    for(var i=0;i<this.items.length;i++) {
      this.food = (this.food) + (this.items[i].amount);
    }
    this.totalAmount = (this.subTotal)+(this.food);
    console.log("total amount"+this.totalAmount)
    console.log("Total food price: " + this.food);
  }

  clearTimer() {
    clearInterval(this.intervalId);
  }
  ngOnDestroy() {
    this.clearTimer();
    (window as any).sendBookedSeats();
    (window as any).disconnect();
  }

  start() {
    this.countDown();
  }
  stop() {
    this.clearTimer();
  }

  private countDown() {
    this.clearTimer();
    this.intervalId = window.setInterval(() => {
      if(localStorage.getItem("paymentStatus")=="success"){
        localStorage.setItem("paymentStatus", "none");
        this.onClickSuccess();
      } else if(localStorage.getItem("paymentStatus")=="fail"){
        localStorage.setItem("paymentStatus", "none");
        this.onClickFail();
      }
      this.seconds -= 1;
      if (this.minutes < 0) {
        this.timer = "Time Lapsed!";
        if(this.handler!=null)
          this.handler.close();

        this.flag= true;
        console.log(this.timer);
        this.stop();
      } else if (this.seconds < 10) {
        this.timer = `0${this.minutes}:0${this.seconds}`;

        if (this.seconds == 0) {
          this.seconds = 60;
          this.minutes -= 1;
        }
      } else {
        this.timer = `0${this.minutes}:${this.seconds}`;
        //console.log("Time Left: " + this.timer);
      }
    }, 1000);
  }
}
