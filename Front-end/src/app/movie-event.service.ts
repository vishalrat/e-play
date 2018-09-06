import { Injectable } from '@angular/core';
import { MovieEvent } from "./movie-event";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class MovieEventService {
 // private _url: string = "http://172.23.238.213:8090/";
 private _url: string = "http://13.232.40.6:8092/upstream";
  constructor(private http: HttpClient) {}
  
  saveMovieEvent(movieEvent: MovieEvent): Observable<MovieEvent> {
   console.log(movieEvent);
 //   return this.http.post<MovieEvent>(this._url+"ticket-service/api/v1/saveMovieEvent", movieEvent);
 return this.http.post<MovieEvent>(this._url+"/api/v1/upstream/movieEvent", movieEvent);
  }
}