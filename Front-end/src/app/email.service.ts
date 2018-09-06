import { Injectable } from '@angular/core';
import{InputEmailsDetails} from "./InputEmailsDetails";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EmailService {

   url= "http://13.232.40.6:8092/email-service/api/v1/email/sendEmail";
   url1= "http://localhost:9000/api/v1/email/sendEmail";
   // inputEmailsDetails:InputEmailsDetails;
  constructor(private http: HttpClient) {}
    sendInvitations(inputEmailsDetails:InputEmailsDetails):Observable<string>
  {
    return this.http.post<string>(this.url,inputEmailsDetails);
  }
}
