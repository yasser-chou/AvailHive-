import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzNotificationService } from "ng-zorro-antd/notification";
import { ActivatedRoute, Router } from "@angular/router";
import { CompanyService } from "../../services/company.service";
import { emailValidator, phoneValidator } from "../create-employer/create-employer.component";

@Component({
  selector: 'app-update-employer',
  templateUrl: './update-employer.component.html',
  styleUrls: ['./update-employer.component.scss']
})
export class UpdateEmployerComponent implements OnInit {

  employerId!: string;
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;
  validateForm!: FormGroup;
  existingImage: string | null = null;
  imgChanged = false;

  constructor(
    private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router,
    private companyService: CompanyService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Subscribe to route parameters
    this.activatedRoute.params.subscribe(params => {
      this.employerId = params['id'];
      this.getEmployerById(); // Ensure getEmployerById is called after employerId is set
    });

    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email, emailValidator]],
      phone: [null, [Validators.required, phoneValidator]],
      position: [null, [Validators.required]],
      salary: [null, [Validators.required, Validators.min(0)]],
      startDate: [null, [Validators.required]]
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
    this.imgChanged = true;
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile as Blob);
  }

  updateEmployer() {
    if (this.validateForm.valid) {
      const formData: FormData = new FormData();
      if (this.imgChanged && this.selectedFile) {
        formData.append('img', this.selectedFile);
      }
      formData.append('name', this.validateForm.get('name')!.value);
      formData.append('email', this.validateForm.get('email')!.value);
      formData.append('phone', this.validateForm.get('phone')!.value);
      formData.append('position', this.validateForm.get('position')!.value);
      formData.append('salary', this.validateForm.get('salary')!.value);
      formData.append('startDate', this.validateForm.get('startDate')!.value);

      this.companyService.updateEmployer(this.employerId, formData).subscribe(
        () => {
          this.notification.success(
            'SUCCESS',
            'Employer Updated Successfully',
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
    } else {
      this.notification.error(
        'ERROR',
        'Please fill out all required fields correctly.',
        { nzDuration: 5000 }
      );
    }
  }

  getEmployerById() {
    this.companyService.getEmployerById(this.employerId).subscribe(res => {
      console.log(res);
      this.validateForm.patchValue(res);
      this.existingImage = 'data:image/jpeg;base64,' + res.returnedImg;
    });
  }
}
