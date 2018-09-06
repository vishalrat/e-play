import { Component, OnInit } from "@angular/core";
import { SearchDataService } from "../search-data.service";
import { Router } from "@angular/router";
import { RecommendationService } from "../recommendation.service";
import { Movie } from "../movie";
import { AuthenticationService } from "../authentication.service";
import {
  FormBuilder,
  FormGroup,
  FormArray,
  FormControl,
  ValidatorFn
} from "@angular/forms";

@Component({
  selector: "app-search-results",
  templateUrl: "./search-results.component.html",
  styleUrls: ["./search-results.component.css"]
})
export class SearchResultsComponent implements OnInit {
  event$: any;
  movie$: any;
  message: string;
  omdbSearchTitle: string;
  constructor(
    private data: SearchDataService,
    private router: Router,
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private recommendationservice: RecommendationService
  ) {
     // Create a new array with a form control for each order
     const controls = this.genresList.map(c => new FormControl(false));
     // controls[0].setValue(true); // Set the first checkbox to true (checked)
     this.form = this.formBuilder.group({
       genresList: new FormArray(controls)
     });
  }

  ngOnInit() {
    this.data.currentMessage.subscribe(message => (this.message = message));
    console.log("in search result compoment", this.message);
    this.fetchEvents();
  }
  form: FormGroup;
  movies: Movie[];
  selectedGenre: String[];
  finalmovies: Movie[];

  genresList = [
    { id: 1, moviegenre: "Thriller" },
    { id: 2, moviegenre: "Comedy" },
    { id: 3, moviegenre: "Drama" },
    { id: 4, moviegenre: "Romance" },
    { id: 5, moviegenre: "Sports" },
    { id: 6, moviegenre: "Horror" },
    { id: 7, moviegenre: "War" },
    { id: 8, moviegenre: "Action" }
  ];

  onSearch() {
    console.log("Hi on searchevents for search is called ", this.message);
    localStorage.setItem('searchKey',this.omdbSearchTitle);
    this.data.getSearchedMovies(this.omdbSearchTitle).subscribe(data => {
      this.movie$ = data;
      console.log(data);
    });
    this.data.getSearchedEvents(this.omdbSearchTitle).subscribe(data => {
      this.event$ = data;
      console.log(data);
    });
  }
  fetchEvents() {
    console.log("Hi fetchevents for search is called ", this.message);
    this.data.getSearchedEvents(localStorage.getItem('searchKey')).subscribe(data => {
      this.event$ = data;
      console.log("event$", data);
    });
    this.data.getSearchedMovies(localStorage.getItem('searchKey')).subscribe(data => {
      this.movie$ = data;
      console.log("movie$", data);
    });
    // this.data.getSearchedMovies(this.message).subscribe(data => {
    //   this.movie$ = data;
    //   console.log(data);
    // });
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
  gocheck() {
    console.log("something just like this");
  }
  submit() {
    console.log("inside submit")
    const selectedGenres = this.form.value.genresList
      .map((v, i) => (v ? this.genresList[i].moviegenre : null))
      .filter(v => v !== null);
      console.log(selectedGenres);
      this.recommendationservice
      .getMoviesByGenre(localStorage.getItem("city"), selectedGenres)
      .subscribe(res => {
        this.movies = res;
        console.log(this.movies);
        localStorage.setItem("rec", JSON.stringify(this.movies));
        window.location.reload();
      });
  }
  loggedIn(){
    return this.authenticationService.loggedIn();
   }
}
