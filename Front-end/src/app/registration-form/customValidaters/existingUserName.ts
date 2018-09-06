import { Directive } from '@angular/core';
import { AsyncValidatorFn, AsyncValidator, NG_ASYNC_VALIDATORS, AbstractControl, ValidationErrors } from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { RegistrationService } from '../../registration.service';


@Directive({
    selector: '[userNameExists][formControlName],[userNameExists][formControl],[userNameExists][ngModel]',
    providers: [{provide: NG_ASYNC_VALIDATORS, useExisting: ExistingUsernameValidatorDirective, multi: true}]
  })

  export class ExistingUsernameValidatorDirective implements AsyncValidator {
    constructor(private registrationService: RegistrationService) {  }

    validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
      return existingUserName(this.registrationService)(control);
    }
  }

  export function existingUserName(registrationService: RegistrationService): AsyncValidatorFn {
    return (control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
        return registrationService.checkUserName(control.value)
       .pipe(
            map(
          users => {
            return users ? {'userNameExists': true} : null;
          }
        ), catchError( error => {
             return throwError( 'Something went wrong!' );
           })
        );
    };
  }