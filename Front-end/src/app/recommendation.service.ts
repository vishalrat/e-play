import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Movie } from "./movie";
import { TicketedEvent } from './ticketedEvent';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {
  private url :string ="http://13.232.40.6:8092/recommendation-service/api/v1";
  private urlCityGenre; 
  private urlCityType;
  //private citySelected;
  genreNames;
  typeNames;
  
  constructor(private http: HttpClient) {
    //this.citySelected = localStorage.getItem('city');
   }

  getMoviesByGenre(name:string, genreNames):Observable<Movie[]>{
    
    console.log("inside getmovies by genre");
    console.log(genreNames);
   
    this.urlCityGenre=this.url+"/getMoviesByCityAndGenres?name="+localStorage.getItem('city'); 

    for(let genreName in genreNames){
      console.log(genreNames[genreName]);
      this.urlCityGenre = this.urlCityGenre+"&genreNames="+genreNames[genreName];
    }

    console.log(this.urlCityGenre);
     localStorage.removeItem('rec');
    return this.http.get<Movie[]>(this.urlCityGenre);
  }

  getTicketedEventByType(name:string, typeNames):Observable<TicketedEvent[]>{
    
    console.log("inside getTicketedEvent by type");
    console.log(typeNames);
   
    this.urlCityType=this.url+"/getTicketedEventByType?name="+localStorage.getItem('city'); 

    for(let typeName in typeNames){
      console.log(typeNames[typeName]);
      this.urlCityType = this.urlCityType+"&typeNames="+typeNames[typeName];
    }

    console.log(this.urlCityType);
     localStorage.removeItem('rec');
    return this.http.get<TicketedEvent[]>(this.urlCityType);
  }

  getGenreBasedMoviesForUser(userName:String):Observable<Movie[]>{
    console.log("inside getGenreBasedMoviesForUser");
    let citySelected = localStorage.getItem('city');
    return this.http.get<Movie[]>(this.url+"/getGenreBasedMoviesForUser?userName="+userName+"&cityName="+citySelected);
  }
  getTypeBasedTicketedEventsForUser(userName:String):Observable<TicketedEvent[]>{
    console.log("inside getTypeBasedTicketedEventsForUser");
    let citySelected = localStorage.getItem('city');
    return this.http.get<TicketedEvent[]>(this.url+"/getTypeBasedTicketedEventsForUser?&userName="+userName+"&cityName="+citySelected);
  }
}
