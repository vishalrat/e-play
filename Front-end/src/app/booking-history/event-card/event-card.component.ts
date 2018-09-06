import { Component, OnInit,Input } from '@angular/core';

@Component({
 selector: 'app-event-card',
 templateUrl: './event-card.component.html',
 styleUrls: ['./event-card.component.css']
})
export class EventCardComponent implements OnInit{

 constructor() { }
 @Input() name;
 @Input() poster;
 @Input() city;
 @Input() date;
 ngOnInit() {
     console.log("city is",this.city);
 }
}