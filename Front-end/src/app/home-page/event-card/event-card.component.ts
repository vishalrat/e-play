import { Component, OnInit,Input } from '@angular/core';

@Component({
 selector: 'app-event-card',
 templateUrl: './event-card.component.html',
 styleUrls: ['./event-card.component.css']
})
export class EventCardComponent {

 constructor() { }
 @Input() name;
 @Input() poster;
 @Input() city;

}