import { Component, OnInit } from "@angular/core";
import { UserRegistration } from "../models/user-registration";
import { RegistrationService } from "../registration.service";
import {MatSnackBar} from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { existingUserName } from './customValidaters/existingUserName';
import { existingEmail } from './customValidaters/existingEmail';
@Component({
  selector: "app-registration-form",
  templateUrl: "./registration-form.component.html",
  styleUrls: ["./registration-form.component.css"]
})
export class RegistrationFormComponent implements OnInit {
  user= new UserRegistration();
  registerForm: FormGroup;
  hide = true;
  ;

  constructor(public snackBar: MatSnackBar,private formBuilder: FormBuilder,private registrationService: RegistrationService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userName: [
        '',
        [Validators.required],
        [existingUserName(this.registrationService)]
      ],
      email: [
        '',
        [Validators.required, Validators.email],
        [existingEmail(this.registrationService)]
      ],
      'password': ["", [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]]
    });
    
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open("Thanks for registration " + message, action, {
      duration: 2000,
    });
  }
  // onRegisterSubmit() {
  //   alert(this.user.userName + ' ' + this.user.email + ' ' + this.user.password);
  //  }
  onSubmit() {
     this.user.city=localStorage.getItem('city');
   console.log(this.user);
    this.registrationService
      .addUser(this.user)
      .subscribe(res => console.log("Saved"));
  }
  get userName() {
    return this.registerForm.get('userName');
  }
  get email() {
    return this.registerForm.get('email');
  }
}