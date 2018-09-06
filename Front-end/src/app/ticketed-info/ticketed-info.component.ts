import { Component, OnInit } from '@angular/core';
import { Ticket } from '../models/Ticket';
import { AlertsService } from 'angular-alert-module';
import { TicketedInfoService} from '../ticketed-info.service';
import {TicketedEvent} from '../ticketedEvent';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-ticketed-info',
  templateUrl: './ticketed-info.component.html',
  styleUrls: ['./ticketed-info.component.css']
})
export class TicketedInfoComponent implements OnInit {

  ticketInfo: Ticket;
  ticketInfo1: Ticket;
  event: any;
  User: any;
  url: any;
  result: any;
  updatedEvent :TicketedEvent;
  constructor(private ticketedInfoObject: TicketedInfoService, private alerts: AlertsService) { }

  ngOnInit() {
    this.ticketInfo=new Ticket();
    this.event= JSON.parse(localStorage.getItem('movieInfo'));
    console.log("Inside ticketed info "+JSON.stringify(this.event));
    console.log(this.event.id);
    this.User=localStorage.getItem('currentUser');
    this.ticketedInfoObject.getEventInfo(this.event.id).subscribe(r=>{
      this.updatedEvent=r;
      // console.log("Inside onInit of ticketed-info");
       console.log(this.updatedEvent + "Is the updated seats");
    })
  }

   eventDescription(){
    console.log("inside eventDescription()");
    if(this.updatedEvent.description!=null)
    return true;
    else
    return false;
  }

  bookEvent(){
    this.ticketInfo.ticketedEventId = this.event.id;
    this.ticketInfo.userName= this.User;
    this.ticketInfo.noOfSeats= 1;
    this.alerts.setMessage('Booked successfully','success');
    this.ticketedInfoObject.sendTicketedInfo(this.ticketInfo).subscribe(r=>{
      console.log("inside service of sendTicketedInfo")
    })
  }
}
