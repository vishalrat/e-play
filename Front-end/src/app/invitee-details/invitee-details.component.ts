import { Component, OnInit } from "@angular/core";
import { RsvpInvitation } from "../RsvpInvitation";
import { RsvpInvitationService } from "../rsvp-invitation.service";

import { RsvpCreate } from "../RsvpCreate";
import { RsvpCreateService } from "../rsvp-create.service";
import { InputEmailsDetails } from "../InputEmailsDetails";
import { EmailService } from "../email.service";
import { ActivatedRoute, RouterLink, Router, ParamMap } from "@angular/router";


@Component({
  selector: "app-invitee-details",
  templateUrl: "./invitee-details.component.html",
  styleUrls: ["./invitee-details.component.css"]
})
export class InviteeDetailsComponent implements OnInit {
  rsvpInvitationModel = new RsvpInvitation();
  inputEmailsDetails = new InputEmailsDetails();
  public rsvpModel: any;
  message: string;

  id;
  constructor(
    private emailService: EmailService,
    private rsvpCreateService: RsvpCreateService,
    private rsvpInvitationService: RsvpInvitationService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe((param: ParamMap)=>this.id=parseInt(param.get('id')));

    
    
    //  console.log(this.rsvpModel);
    this.rsvpCreateService
      .getRsvpEventById(this.id)
      .subscribe(rsvpCreateService => {
        this.rsvpModel = rsvpCreateService;
        console.log(this.rsvpModel);
      });
    console.log("something", this.rsvpModel);

    for (var i = 0; i < this.rsvpModel.rsvpInvitation.length; i++) 
      {
        this.rsvpModel.rsvpInvitation[i].inviteeEmail
      }

    
  }
  
}
