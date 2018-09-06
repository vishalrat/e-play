import { Component, OnInit } from "@angular/core";
import { FormControl,Validators,FormBuilder,FormGroup, FormsModule } from "@angular/forms";
import { TicketedEvent } from "../ticketedEvent";
import { TicketedEventService } from "../ticketed-event.service";


@Component({
  selector: "app-other-ticketed-event",
  templateUrl: "./other-ticketed-event.component.html",
  styleUrls: ["./other-ticketed-event.component.css"]
})

export class OtherTicketedEventComponent implements OnInit {

  public dateTime: Date;
  public types: String[];
  otherTicketedEventForm: FormGroup;

  otherTicketedEventControl = new FormControl();
  ticketedEvent = new TicketedEvent();
 
  constructor(private ticketedEventService: TicketedEventService,private formBuilder: FormBuilder) {}

    submitTicketedEvent() {
    
      this.ticketedEvent.userName=localStorage.getItem('currentUser').replace("\"", "").replace("\"", "");
      console.log(this.ticketedEvent.date);
      console.log(this.ticketedEvent);
    
      this.ticketedEventService
        .saveTicketedEvent(this.ticketedEvent)
        .subscribe(res => console.log("Saved"));
    }

    ngOnInit() {
        
      this.types=[];
      this.types.push("Plays");
      this.types.push("Sports");
      this.types.push("Concert");
      
     
      this.otherTicketedEventForm = this.formBuilder.group({
        name: [
          '',
          [Validators.required],
        ],
        city: [
          '',
          [Validators.required],
        ],
        date: [
          '',
          [Validators.required],
        ],
        cardPoster: [
          '',
          [Validators.required],
        ],
        backGroundPoster: [
          '',
          [Validators.required],
        ],
        capacity: [
          '',
          [Validators.required],
        ],
        description: [
          '',
          [Validators.required],
        ],
        price: [
          '',
          [Validators.required],
        ],
      }); 
    }
    setType(type){
      console.log(type);
     this.ticketedEvent.type=type;
  
    }
}

