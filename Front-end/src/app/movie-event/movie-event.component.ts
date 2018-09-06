import { Component, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";
import { MovieEvent } from "../movie-event";
import { MovieEventService } from "../movie-event.service";
import { SearchDataService } from "../search-data.service";
import { Movie } from "../models/movie";
import { UserRegistration } from "../models/user-registration";
import { AlertsService } from 'angular-alert-module';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: "app-movie-event",
  templateUrl: "./movie-event.component.html",
  styleUrls: ["./movie-event.component.css"]
})
export class MovieEventComponent implements OnInit {
  movieEventModel = new MovieEvent();
  movieEventControl = new FormControl();
  movies:Movie[];
  user;
  theatres: Theatre[];
  movieForm: FormGroup;

  movieId; // ID corresponding to selected movie Name from Dropdown
 
  constructor(private movieEventService :MovieEventService,private formBuilder: FormBuilder,private _searchDataService:SearchDataService, private alerts: AlertsService) {}
  
  ngOnInit() {
    this.movieForm = this.formBuilder.group({
      week : [
        '',
        [Validators.required],
      ],
      showTimes: [
        '',
        [Validators.required],
      ],
    });
    this._searchDataService.getAllMovies().subscribe(data => 
      {
        this.movies=data
        console.log("mymovies",this.movies);
      });
    console.log(localStorage.getItem('currentUser'));
    this._searchDataService.getUserByUserName(localStorage.getItem('currentUser')).subscribe(data => 
      {
        this.user=data;
        console.log(data);
        console.log(this.user);
        this.theatres=this.user.theatres;
      });
  }

  

  findMovieId(movie){
    console.log(movie.id);
  this.movieEventModel.movieId=movie.id;

  }
  findTheatreId(theatre){
    this.movieEventModel.city=theatre.city;
    console.log(theatre);
    this.movieEventModel.theatreId=theatre.theatreId;
    this.movieEventModel.city=theatre.city;
  }
  onSubmit() {
    // this.movieEventModel.city=localStorage.getItem('city');
    this.movieEventModel.userName=localStorage.getItem('currentUser').replace("\"", "").replace("\"", "");
    console.log(this.movieEventModel);
    this.movieEventService
      .saveMovieEvent(this.movieEventModel)
      .subscribe(res => console.log("Saved Movie Event"))
    this.alerts.setMessage('Movie Event Created','success');
     
  }
}

export interface Theatre {
  value: string;
  viewValue: string;
}

export interface TheatreGroup {
  disabled?: boolean;
  name: string;
  theatre: Theatre[];
}
