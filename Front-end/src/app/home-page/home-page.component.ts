import { Component, OnInit } from "@angular/core";
import { SearchDataService } from "../search-data.service";
import { Router } from "@angular/router";
import {  RecommendationService } from "../recommendation.service";
import { AuthenticationService } from "../authentication.service";

@Component({
  selector: "app-home-page",
  templateUrl: "./home-page.component.html",
  styleUrls: ["./home-page.component.css"]
})

export class HomePageComponent implements OnInit {
  items: Array<any>=[];
  event$: any;
  movie$: any;
  message: string;
  rec$: any;
  recommendedMovie= null;
  recommendedEvent=null;
  myElement: HTMLElement = document.getElementById('target');

  constructor(private data: SearchDataService, private router: Router, private recommendationService: RecommendationService, private authenticationService:AuthenticationService) {
    this.items=[
      { name :"{{movie.backGroundPoster}}"}
    ];
  }
  scroll(el) {
    el.scrollIntoView();
 }
  ngOnInit() {
    localStorage.setItem('duplicateId','refresh');
    this.data.currentMessage.subscribe(message => (this.message = message));
    this.fetchEvents();
    this.rec$ = JSON.parse(localStorage.getItem("rec"));
  }
  fetchEvents() {
    console.log("Hi fetchMovies is called ", this.message);
    this.data.getMyEvents(this.message).subscribe(data => {
      this.event$ = data;
      console.log(data);
    });
    this.data.getMyMovies(this.message).subscribe(data => {
      this.movie$ = data;
      console.log(data);
    });
    this.getGenreBasedMoviesForUser();
    this.getTypeBasedTicketedEventsForUser();
  }
  goMoviePage(movie) {
    console.log("inside goMoviePage");
    localStorage.setItem("movieInfo",JSON.stringify(movie));
    console.log("Recommended movies",localStorage.getItem('recommended'));
    console.log("Movie info",localStorage.getItem("movieInfo"));
    localStorage.setItem('clickedRecommended',movie.id);
    this.data.changeMovieMessage(movie);
    console.log(movie);
  }
  reset() {
    localStorage.removeItem("rec");
    window.location.reload();
  }

  getGenreBasedMoviesForUser(){
   this.recommendationService.getGenreBasedMoviesForUser(localStorage.getItem('currentUser'))
   .subscribe(res=>{
     this.recommendedMovie=res;
     localStorage.setItem('recommended',JSON.stringify(this.recommendedMovie));
     console.log("storing recommended movies",localStorage.getItem('recommended'));
   });
  }
  getTypeBasedTicketedEventsForUser(){
    console.log("inside recommendedevent")
    this.recommendationService.getTypeBasedTicketedEventsForUser(localStorage.getItem('currentUser'))
    .subscribe(res=>{
      this.recommendedEvent=res;
      localStorage.setItem('recommendedEvent',JSON.stringify(this.recommendedEvent));
      console.log("storing recommended events",localStorage.getItem('recommended1'));
    })
  }

  loggedIn(){
   return this.authenticationService.loggedIn();
  }

  scrollTOTarget(){
    console.log("inside scrollTOTarget()");
    this.myElement.scrollIntoView();
  }
}
