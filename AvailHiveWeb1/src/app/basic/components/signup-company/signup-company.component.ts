import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';
import { PasswordValidator } from '../../services/auth/password-validators'; // Import the custom validator

@Component({
  selector: 'app-signup-company',
  templateUrl: './signup-company.component.html',
  styleUrls: ['./signup-company.component.scss']
})
export class SignupCompanyComponent implements OnInit {

  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private notification: NzNotificationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      name: [null, [Validators.required]],
      address: [null, [Validators.required]],
      phone: [null, [Validators.pattern(/^\+?[0-9]{10,15}$/)]], // Example pattern for phone validation
      password: [null, [Validators.required, Validators.minLength(8), PasswordValidator.strongPassword()]],
      checkPassword: [null, [Validators.required, this.matchOtherValidator('password')]]
    });
  }

  matchOtherValidator(otherControlName: string) {
    return (control: any) => {
      if (!control.parent) {
        return null;
      }
      const otherControl = control.parent.get(otherControlName);
      if (!otherControl) {
        return null;
      }
      return otherControl.value === control.value ? null : { matchOther: true };
    };
  }

  submitForm() {
    if (this.validateForm.valid) {
      this.authService.registerCompany(this.validateForm.value).subscribe(res => {
        this.notification.success(
          'SUCCESS',
          'Signup successful',
          { nzDuration: 5000 }
        );
        this.router.navigateByUrl('/login');
      }, error => {
        if (error.status === 409) { // Assuming 409 Conflict for existing user
          this.notification.error(
            'ERROR',
            'Company already exists',
            { nzDuration: 5000 }
          );
        } else {
          this.notification.error(
            'ERROR',
            `${error.error}`,
            { nzDuration: 5000 }
          );
        }
      });
    } else {
      this.notification.error(
        'ERROR',
        'Please complete the form correctly',
        { nzDuration: 5000 }
      );
    }
  }
}
