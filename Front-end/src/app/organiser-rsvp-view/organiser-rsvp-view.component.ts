import { Component, OnInit } from '@angular/core';
import{RsvpCreate} from '../RsvpCreate';
import{RsvpInvitation} from '../RsvpInvitation';
import{RsvpInvitationService} from '../rsvp-invitation.service';
import{RsvpCreateService} from '../rsvp-create.service';
import { InputEmailsDetails } from "../InputEmailsDetails";
import { ActivatedRoute, RouterLink, Router, ParamMap } from "@angular/router";

@Component({
  selector: 'app-organiser-rsvp-view',
  templateUrl: './organiser-rsvp-view.component.html',
  styleUrls: ['./organiser-rsvp-view.component.css']
})
export class OrganiserRsvpViewComponent implements OnInit {

  rsvpInvitationModel= new RsvpInvitation();
  rsvpModel:any;
  inputEmailsDetails = new InputEmailsDetails();
  

  constructor(
    private rsvpCreateService:RsvpCreateService,
    private rsvpInvitationService:RsvpInvitationService,
    private activatedRoute:ActivatedRoute
  ,private router:Router
  ) { }

  id:any;
  

  ngOnInit() {
    // this.id=this.rsvpCreateService.getRsvpEventId();
  //  this.id = this.rsvpCreateService.id;
   // console.log("bhaiiii", this.id);
   this.activatedRoute.paramMap.subscribe((param: ParamMap)=>this.id=parseInt(param.get('id')));

  
    
    
    console.log("id in org rsvp",this.id);
    

    console.log("this.rsvpModel");
    
    //  console.log(this.rsvpModel);
    this.rsvpCreateService
      .getRsvpEventById(this.id)
      .subscribe(rsvpCreateService => {
        this.rsvpModel = rsvpCreateService;
        console.log(this.rsvpModel);
      });
    console.log("something", this.rsvpModel);
  }

  onSubmit1() {
    //this.id=this.rsvpModel.id;
    console.log("vishal ppuuu");
    // this.router.navigate(["/rsvpEvent/" + this.id]);
    this.router.navigate(["/"+this.id+"/InviteeDetails"]);
  }
  onSubmit2() {
    //this.id=this.rsvpModel.id;
    console.log("vishal ppuuu");
    // this.router.navigate(["/rsvpEvent/" + this.id]);

    console.log("this id is",this.id);
    
    this.router.navigate(["/"+this.id+"/rsvpInvitation"]);

  }
}
