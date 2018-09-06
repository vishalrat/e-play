import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import{Router} from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { Movie } from "./models/movie";

@Injectable({
 providedIn: 'root'
})

export class SearchDataService {
 
  currentUser;
  url= "http://13.232.40.6:8092/user-registration/api/v1/user/";
 // url= "http://172.23.238.213:8091/api/v1/user/";
  
 private messageSource = new BehaviorSubject('default message');
 currentMessage = this.messageSource.asObservable();
 
 changeMessage(message: string) {
  this.messageSource.next(message)
}

 private movie = new BehaviorSubject(new Movie());
 movieMessage = this.movie.asObservable();

 changeMovieMessage(message: Movie) {
  this.movie.next(message)
}

 
 constructor(private http: HttpClient, private router: Router) { }

 getAllMovies(): Observable<Movie[]>{
  return this.http.get<Movie[]>('http://13.232.40.6:8092/ticket-service/api/v1/getAllMovie')
}

getUserByUserName(name){
  this.currentUser=localStorage.getItem('currentUser').replace("\"", "").replace("\"", "");

  console.log(name)
  console.log(this.currentUser);
  return this.http.get(this.url+this.currentUser);
}



getMyMovies(city) {
  console.log("in getMyMovies",localStorage.getItem('city'));
  return this.http.get('http://13.232.40.6:8092/search-service/api/v1/city/'+localStorage.getItem('city')+'/movies')
}
getMyEvents(city) {
  console.log("in getMyEvents",localStorage.getItem('city'));
 return this.http.get('http://13.232.40.6:8092/search-service/api/v1/city/'+localStorage.getItem('city')+'/events')
}

getSearchedEvents(name) {
  console.log(name);
 return this.http.get('http://13.232.40.6:8092/search-service/api/v1/events/'+name)
}
getSearchedMovies(name) {
 console.log(name);
return this.http.get('http://13.232.40.6:8092/search-service/api/v1/movies/'+name)
}

getMovieByCitynId(movieId) {
 let city=localStorage.getItem('city');
  return this.http.get('http://13.232.40.6:8092/search-service/api/v1/movie/'+movieId+'/city/'+localStorage.getItem('city'))
//  return this.http.get('http://13.232.40.6:8092/search-service/api/v1/movie/'+movieId+'/city/'+city);

}
getMovieByCitynIduser(movieId,city){
 return this.http.get('http://13.232.40.6:8092/ticket-service/api/v1/getByID/'+movieId);
}
getTicketEventById(ticketId,city){
 return this.http.get('http://13.232.40.6:8092/ticket-service/api/v1/getTicketedEventById/'+ticketId);
}
}