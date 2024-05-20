

import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export class PasswordValidator {
  static strongPassword(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (!value) {
        return null;
      }

      const hasUpperCase = /[A-Z]+/.test(value);
      const hasLowerCase = /[a-z]+/.test(value);
      const hasNumeric = /[0-9]+/.test(value);
      const hasSpecial = /[!@#$%^&*(),.?":{}|<>]+/.test(value);
      const isValid = hasUpperCase && hasLowerCase && hasNumeric && hasSpecial && value.length >= 8;

      return !isValid ? { strongPassword: true } : null;
    };
  }
}
