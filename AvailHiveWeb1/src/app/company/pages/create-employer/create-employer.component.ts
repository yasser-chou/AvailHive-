import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { NzNotificationService } from "ng-zorro-antd/notification";
import { Router } from "@angular/router";
import { CompanyService } from "../../services/company.service";

// Custom validator to check if email is valid
export function emailValidator(control: AbstractControl): ValidationErrors | null {
  const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  return emailPattern.test(control.value) ? null : { invalidEmail: true };
}

// Custom validator to check if phone number is valid
export function phoneValidator(control: AbstractControl): ValidationErrors | null {
  const phonePattern = /^[0-9]{10,15}$/;
  return phonePattern.test(control.value) ? null : { invalidPhone: true };
}

@Component({
  selector: 'app-create-employer',
  templateUrl: './create-employer.component.html',
  styleUrls: ['./create-employer.component.scss']
})
export class CreateEmployerComponent implements OnInit {

  selectedFile: File | null = null; // Storing the image of the employer, initialized as null
  imagePreview: string | ArrayBuffer | null = null; // Initialized as null
  validateForm!: FormGroup;
  employees: Array<{ email: string; phone: string }> = []; // Store list of employees

  constructor(private fb: FormBuilder,
              private notification: NzNotificationService,
              private router: Router,
              private companyService: CompanyService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email, emailValidator]],
      phone: [null, [Validators.required, phoneValidator]],
      position: [null, [Validators.required]],
      salary: [null, [Validators.required, Validators.min(0)]],
      startDate: [null, [Validators.required]]
    });
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    if (this.selectedFile) {
      reader.readAsDataURL(this.selectedFile);
    }
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  isDuplicateEmployee(email: string, phone: string): boolean {
    return this.employees.some(employee => employee.email === email || employee.phone === phone);
  }

  postEmployer() {
    if (this.validateForm.valid && this.selectedFile) {
      const email = this.validateForm.get('email').value;
      const phone = this.validateForm.get('phone').value;

      if (this.isDuplicateEmployee(email,phone)) {
        if (this.employees.some(employee => employee.email === email))
          this.notification.error(
            'ERROR',
            'Email already exists!',
            {nzDuration: 5000}
          );
      }else if(this.employees.some(employee=>employee.phone===phone)) {
          this.notification.error(
            'ERROR',
            'Phone number already exists!',
            { nzDuration: 5000 }
          );

        }
       else {
        const formData: FormData = new FormData();
        formData.append('img', this.selectedFile);
        formData.append('name', this.validateForm.get('name').value);
        formData.append('email', email);
        formData.append('phone', phone);
        formData.append('position', this.validateForm.get('position').value);
        formData.append('salary', this.validateForm.get('salary').value);
        formData.append('startDate', this.validateForm.get('startDate').value);

        this.companyService.postEmployer(formData).subscribe(
          () => {
            this.employees.push({ email, phone }); // Add the new employee to the list
            this.notification.success(
              'SUCCESS',
              'Employer added successfully',
              { nzDuration: 5000 }
            );
            this.router.navigateByUrl('/company/employers');
          },
          error => {
            this.notification.error(
              'ERROR',
              `${error.error}`,
              { nzDuration: 5000 }
            );
          }
        );
      }
    } else {
      this.notification.error(
        'ERROR',
        'Please fill out all required fields correctly.',
        { nzDuration: 5000 }
      );
    }
  }
}
