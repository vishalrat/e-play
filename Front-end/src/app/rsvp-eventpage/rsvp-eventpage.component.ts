import { Component, OnInit } from '@angular/core';
import{RsvpCreate} from '../RsvpCreate';
import{RsvpInvitation} from '../RsvpInvitation';
import{RsvpInvitationService} from '../rsvp-invitation.service';
import { ActivatedRoute, RouterLink, Router, ParamMap } from "@angular/router";
import {MatRadioModule} from '@angular/material/radio';


import{RsvpCreateService} from '../rsvp-create.service';
@Component({
  selector: 'app-rsvp-eventpage',
  templateUrl: './rsvp-eventpage.component.html',
  styleUrls: ['./rsvp-eventpage.component.css']
})
export class RsvpEventpageComponent implements OnInit {


  rsvpInvitationModel= new RsvpInvitation();
  rsvpModel:any;
  finalId;

  constructor(
    private rsvpCreateService:RsvpCreateService,
    private rsvpInvitationService:RsvpInvitationService,
    private activatedRoute:ActivatedRoute
  ,private router:Router
  ) { }

 
  
  ngOnInit() {
    //this.id=this.rsvpCreateService.getRsvpEventId;
   this.activatedRoute.paramMap.subscribe((param: ParamMap)=>this.finalId=parseInt(param.get('id')));
    console.log(this.rsvpModel);
   // this.id=localStorage.getItem("RSVPId");
   console.log("final id is",this.finalId);
   
   this.rsvpCreateService.getRsvpEventById(this.finalId).subscribe(p=>{
     this.rsvpModel=p;
   });
   console.log(this.rsvpModel);
  }

  onSubmit(){console.log("to change status");
 
  console.log(this.rsvpInvitationModel);
  this.rsvpInvitationModel.status=true;
   this.rsvpCreateService.updateRsvp(this.rsvpInvitationModel, this.finalId).subscribe(res=>{console.log('saved')
  });
  

  this.router.navigate(["home"]);
  }
  

  changeStatus(status){this.rsvpInvitationModel.status=status;

  }
 // this.rsvpModel=this.rsvpCreateService.getRsvpEventById(id);

//  getRsvpEventById(id:number){
//  this.rsvpCreateService.getRsvpEventById(id).subscribe(rsvpCreateService=>{
//    this.rsvpModel=rsvpCreateService;
//  });
//  }

 
//  onSubmit(){


//   this.rsvpInvitationService.saveRsvpInvitation(this.rsvpInvitationModel).subscribe(res=>console.log('saved'));
// }
}

