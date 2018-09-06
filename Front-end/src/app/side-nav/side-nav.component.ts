import { Component, OnInit } from "@angular/core";
import { Movie } from "../movie";
import { RecommendationService } from "../recommendation.service";
import {
  FormBuilder,
  FormGroup,
  FormArray,
  FormControl,
  ValidatorFn
} from "@angular/forms";

@Component({
  selector: "app-side-nav",
  templateUrl: "./side-nav.component.html",
  styleUrls: ["./side-nav.component.css"]
})
export class SideNavComponent implements OnInit {
  form: FormGroup;
  movies: Movie[];
  selectedGenre: String[];
  finalmovies: Movie[];

  genresList = [
    { id: 1, moviegenre: "Thriller" },
    { id: 2, moviegenre: "comedy" },
    { id: 3, moviegenre: "Drama" },
    { id: 4, moviegenre: "Romance" },
    { id: 5, moviegenre: "Sports" },
    { id: 6, moviegenre: "Horror" }
  ];

  constructor(
    private recommendationservice: RecommendationService,
    private formBuilder: FormBuilder
  ) {
    // Create a new array with a form control for each order
    const controls = this.genresList.map(c => new FormControl(false));
    // controls[0].setValue(true); // Set the first checkbox to true (checked)
    this.form = this.formBuilder.group({
      genresList: new FormArray(controls)
    });
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
  
  onChange(genre: string) {
    // console.log("inside onchange start");
    // this.recommendationservice.getMoviesByGenre(genre)
    //   .subscribe(res => {
    //     this.moviegenre= res;
    //   });
    //   console.log(genre);
    //   console.log(this.moviegenre);
    //  this.movies.push(this.moviegenre);
    // //  console.log("inside onchange");
    //  console.log(this.movies);
  }

  // selectedPreferenceList(list) {
  //   console.log(list);
  //   this.selectedGenre = list;
  //   //  console.log(this.selectedGenre[0]);
  //   this.recommendationservice
  //     .getMoviesByGenre(localStorage.getItem("city"), list)
  //     .subscribe(res => {
  //       this.movies = res;
  //       console.log(this.movies);
  //       localStorage.setItem("rec", JSON.stringify(this.movies));
  //       window.location.reload();
  //     });
  // }
  ngOnInit() {}
}
