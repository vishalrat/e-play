import { Component, OnInit, Inject } from '@angular/core';
import { SearchDataService } from "../search-data.service";
import { RecommendationService } from "../recommendation.service";
import { Movie } from "../movie";
import { AuthenticationService } from '../authentication.service';
import { FormBuilder,FormGroup,FormArray,FormControl,ValidatorFn} from "@angular/forms";
import { HomePageComponent } from "../home-page/home-page.component";
import { Router } from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})

export class NavBarComponent implements OnInit {
  
  omdbSearchTitle: string;
  User: string;
 // @Inject(HomePageComponent) homePage: HomePageComponent;
  // constructor(
  //   private data: SearchDataService,
  //   private authenticationService:AuthenticationService
  // ) { }

  ngOnInit() {this.User=localStorage.getItem('currentUser');
  console.log("user name",this.User);
  }
  onSearch(){
    console.log(this.omdbSearchTitle);
    this.data.changeMessage(this.omdbSearchTitle);
    localStorage.setItem('searchKey',this.omdbSearchTitle);

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
    { id: 7, moviegenre: "War"},
    { id: 8, moviegenre: "Action"}
  ];

  constructor(
    private recommendationservice: RecommendationService,
    private formBuilder: FormBuilder,
    private data: SearchDataService,
    private authenticationService:AuthenticationService,
    private router: Router,
  ) {
    // Create a new array with a form control for each order
    const controls = this.genresList.map(c => new FormControl(false));
    // controls[0].setValue(true); // Set the first checkbox to true (checked)
    this.form = this.formBuilder.group({
      genresList: new FormArray(controls)
    });
  }
  //constructor(private data: SearchDataService, private router: Router, private recommendationService: RecommendationService, private authenticationService:AuthenticationService) {
   
  
 
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
  reset(){
    localStorage.removeItem('rec');
    window.location.reload();
  }
  loggedIn(){
    return this.authenticationService.loggedIn();
   }

   scroll(){
   console.log("inside scroll()");
   let homePage = new HomePageComponent(this.data,this.router,this.recommendationservice,this.authenticationService);
   homePage.scrollTOTarget();
   }
}
