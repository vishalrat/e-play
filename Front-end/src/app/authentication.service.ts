import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username, password) {
        console.log(username,password);    
        return this.http.post<any>(`http://13.232.40.6:8092/user-registration/api/v1/login`, { username, password })
//return this.http.post<any>(`http://localhost:8091/api/v1/login`, { username, password }) 
           
          .pipe(map(user => {
                // login successful if there's a jwt token in the response
                if (user) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                     localStorage.setItem('currentUser', user.username);
                     localStorage.setItem('currentUserToken', user.token);

                }

                return user;
            }));
    }
     loggedIn(){
      return localStorage.getItem('currentUser');
     }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        localStorage.removeItem('currentUserToken');
    }
}
