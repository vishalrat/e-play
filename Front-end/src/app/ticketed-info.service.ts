import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Ticket} from './models/Ticket';
import { Observable } from "rxjs";
import {TicketedEvent} from './ticketedEvent';

@Injectable({
  providedIn: 'root'
})
export class TicketedInfoService {

  private url: string = "http://13.232.40.6:8092//ticket-service/api/v1/bookTicketedEvent";
  private getUrl : string ="http://13.232.40.6:8092/ticket-service/api/v1/getTicketedEventById/";
  constructor(private http: HttpClient) {}

  getEventInfo(id){
    return this.http.get<TicketedEvent>(this.getUrl+id);
  }
  sendTicketedInfo(ticketInfo: Ticket): Observable<Ticket>{
    console.log(ticketInfo);
    return this.http.put<Ticket>(this.url,ticketInfo);
  }

}
