import { Component, OnInit } from "@angular/core";
import { RsvpCreate } from "../RsvpCreate";
import { RsvpCreateService } from "../rsvp-create.service";
import { Router, ActivatedRoute } from "@angular/router";
import { FormGroup, FormBuilder, Validators,FormControl } from "@angular/forms";

@Component({
  selector: "app-rsvp-event",
  templateUrl: "./rsvp-event.component.html",
  styleUrls: ["./rsvp-event.component.css"]
})
export class RsvpEventComponent implements OnInit {
  rsvpModel = new RsvpCreate();
  id: number;
  public dateTime: Date;
  rsvpForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private rsvpCreateService: RsvpCreateService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  onSubmit() {
    this.rsvpModel.userName=localStorage.getItem('currentUser').replace("\"", "").replace("\"", "");
    this.rsvpCreateService.saveRsvpEvent(this.rsvpModel).subscribe(res => {
      console.log("saved");

    // console.log("id of the saved rsvpEvent is ", res.id-118);
      // this.rsvpCreateService.id = res.id;
      
      // // console.log(localStorage.getItem("eventId"));
      
        this.router.navigate(["/"+res.id+"/rsvpInvitation"]);
      
    });
  }


  ngOnInit() {
    this.rsvpForm = this.formBuilder.group({
      name: [
        '',
        [Validators.required],
      ],
      startDate: [
        '',
        [Validators.required],
      ],
      city: [
        '',
        [Validators.required],
      ],
      poster: [
        '',
        [Validators.required],
      ],
      description: [
        '',
        [Validators.required],
      ]
    });
  }
}
