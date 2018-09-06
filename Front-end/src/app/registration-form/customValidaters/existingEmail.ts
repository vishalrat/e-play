import { Directive } from '@angular/core';
import {
  AsyncValidatorFn,
  AsyncValidator,
  NG_ASYNC_VALIDATORS,
  AbstractControl,
  ValidationErrors
} from '@angular/forms';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { RegistrationService } from '../../registration.service';

@Directive({
  selector:
    '[emailExists][formControlName],[emailExists][formControl],[emailExists][ngModel]',
  providers: [
    {
      provide: NG_ASYNC_VALIDATORS,
      useExisting: ExistingEmailValidatorDirective,
      multi: true
    }
  ]
})
export class ExistingEmailValidatorDirective implements AsyncValidator {
  constructor(private registrationService: RegistrationService) {}

  validate(
    control: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return existingEmail(this.registrationService)(control);
  }
}

export function existingEmail(
    registrationService: RegistrationService
): AsyncValidatorFn {
  return (
    control: AbstractControl
  ): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
    return registrationService.checkEmail(control.value).pipe(
      map(users => {
        return users ? { 'emailExists': true } : null;
      }),
      catchError(error => {
        return throwError('Something went wrong!');
      })
    );
  };
}