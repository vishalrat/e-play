import { Component, OnInit, Input } from '@angular/core';
import {SearchDataService} from "../../search-data.service";

@Component({
  selector: 'app-movie-theatre-list',
  templateUrl: './movie-theatre-list.component.html',
  styleUrls: ['./movie-theatre-list.component.css']
})
export class MovieTheatreListComponent implements OnInit {
  
  public movie;
  public theatreList;
  public movieRecommend;
  public recommendedMovies;
  constructor(private _searchDataService:SearchDataService){
  }
  ngOnInit() {
    this._searchDataService.movieMessage.subscribe(movie => this.movie = movie)
    
    this.recommendedMovies=JSON.parse(localStorage.getItem('recommended'));
    console.log("Movie inside recom and thearlist"+this.recommendedMovies);
    this.movie=JSON.parse(localStorage.getItem('movieInfo'));
    
    console.log("print in child" , this.recommendedMovies);
    
    this._searchDataService.getMovieByCitynId(localStorage.getItem('clickedRecommended')).subscribe(movieRecommend => {this.movieRecommend = movieRecommend
      console.log("recsn a ll",this.movieRecommend)
    })
    this.theatreList= this.movie.theatres;
    //this.theatreList=this.theatreList[0];
  }

  sendShowId(id){
    localStorage.setItem('showId',id);
    localStorage.setItem('duplicateId',"initial");
  }
}