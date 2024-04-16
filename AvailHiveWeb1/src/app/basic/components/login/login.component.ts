import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth/auth.service";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private notification: NzNotificationService,
              private router:Router,
  ) { }

  ngOnInit(): void {
    //fb = form builder
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],

    })
  }

  submitForm() {
    if (this.validateForm.valid) {
      console.log('Form Values:', this.validateForm.value);
      this.authService.login(this.validateForm.get(['userName'])!.value, this.validateForm.get(['password'])!.value)
        .subscribe(
          res => {
            console.log(res);
          },
          error => {
            console.log(error)
            this.notification.error('ERROR', 'Bad credentials', { nzDuration: 5000 });
          }
        );
    } else {
      this.notification.error('ERROR', 'Invalid form', { nzDuration: 5000 });
    }
  }

}
