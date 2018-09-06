import { Component, OnInit } from '@angular/core';
import { LoginModel } from '../models/login.model';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AuthenticationService } from '../authentication.service';



@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  user: LoginModel = new LoginModel();
  loginForm: FormGroup;
  hide = true;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';





  constructor(private formBuilder: FormBuilder , private route: ActivatedRoute,
    private router: Router, private authenticationService: AuthenticationService){}
  
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'username': [this.user.username, [
        Validators.required
      ]],
      'password': [this.user.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]]
      
    });
  }

  onSubmit() {

    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }

    this.loading = true;
    this.authenticationService.login(this.user.username, this.user.password)
        .subscribe(
            data => {
                this.router.navigate(['/home']);
            },
            error => {
                this.error = error;
                this.loading = false;
            });
}
}
