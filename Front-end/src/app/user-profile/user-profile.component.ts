import { Component, OnInit } from "@angular/core";
import { SearchDataService } from "../search-data.service";
import { RegistrationService } from "../registration.service";
import { Router } from "@angular/router";

import { Theatre } from "../movie-event/movie-event.component";
@Component({
  selector: "app-user-profile",
  templateUrl: "./user-profile.component.html",
  styleUrls: ["./user-profile.component.css"]
})
export class UserProfileComponent implements OnInit {
  yourData: any;
  movie: any;
  movieList: any[];
  cityList: any[];
  theatreList: Theatre[];
  
  constructor(
    private data: SearchDataService,
    private router: Router,
    private userData: RegistrationService
  ) {}

  ngOnInit() {
    this.theatreList = [];
    this.movieList = [];
    this.cityList = [];
    console.log(localStorage.getItem("currentUser"));
    this.userData
      .getUser(localStorage.getItem("currentUser"))
      .subscribe(data => {
        this.yourData = data;
        console.log("your data", data);
        for (var i = 0; i < this.yourData.movieEvent.length; i++) {
          console.log("top i is", i);
          console.log("city is", this.yourData.movieEvent[i].city);
          if (this.yourData.movieEvent[i].city != null) {
            this.cityList.push(this.yourData.movieEvent[i].city);
          }
          this.data
            .getMovieByCitynIduser(
              this.yourData.movieEvent[i].movieId,
              this.yourData.movieEvent[i].city
            )
            .subscribe(data => {
              this.movie = data;
             
              this.movieList.push(this.movie);
              
            });
          for (var j = 0; j < this.yourData.theatres.length; j++) {
            // console.log("the I is", i);
            // console.log(
            //   "theatre id of present event",
            //   this.yourData.movieEvent[i]
            // );
            if (
              this.yourData.theatres[j].theatreId ==
              this.yourData.movieEvent[i].theatreId
            ) {
              this.theatreList.push(this.yourData.theatres[j]);
            }
          }
        }
        

        console.log("movie List is", this.movieList);
        console.log("city list is", this.cityList);
        console.log("thatre list is", this.theatreList);
      });
    
  }

  onClick(id){
    console.log("in the on click");
   // localStorage.setItem('RSVPId',(id));
    console.log("rsvp id is",id," this id is generated in local",localStorage.getItem("RSVPId"));
    this.router.navigate(["/"+id+"/organiserRsvpView"]);
    


  }
  
}
