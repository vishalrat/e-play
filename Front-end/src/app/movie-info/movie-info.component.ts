import { Component, OnInit } from '@angular/core';
import { SearchDataService } from '../search-data.service';
@Component({
  selector: 'app-movie-info',
  templateUrl: './movie-info.component.html',
  styleUrls: ['./movie-info.component.css']
})
export class MovieInfoComponent implements OnInit {
   movie;
   movies: any;
   movieRecommend: any;
   recommendedMovies:any
  constructor(private _searchDataService:SearchDataService) {
    this.movies=JSON.parse(localStorage.getItem('movieInfo'));
    this.recommendedMovies = JSON.parse(localStorage.getItem('recommended'));
    console.log("Recommended :"+ this.recommendedMovies.description);

  }

  ngOnInit() {
    this.movies=JSON.parse(localStorage.getItem('movieInfo'));
    console.log("movies are " + JSON.stringify(this.movies));
    this.recommendedMovies = JSON.parse(localStorage.getItem('recommended'))
    console.log(" some recommended given movies",this.recommendedMovies);
    
    this._searchDataService.movieMessage.subscribe(movieSearch =>{ this.movie = movieSearch
    });

    this._searchDataService.getMovieByCitynId(localStorage.getItem('clickedRecommended')).subscribe(movieRecommend => {this.movieRecommend = movieRecommend
      console.log("recsn a ll",this.movieRecommend)
    })
  }

  movieDescription(){
    // console.log("inside movie description");
    // console.log(this.movies);
    if(this.movieRecommend.description!=null)
    return true;
    else
    return false;
  }
}