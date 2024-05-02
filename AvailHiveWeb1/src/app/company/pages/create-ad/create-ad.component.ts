import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzNotificationService } from "ng-zorro-antd/notification";
import { Router } from "@angular/router";
import { CompanyService } from "../../services/company.service";

@Component({
  selector: 'app-create-ad',
  templateUrl: './create-ad.component.html',
  styleUrls: ['./create-ad.component.scss']
})
export class CreateAdComponent implements OnInit {

  selectedFile: File | null; // Storing the image of the ad, initialized as null
  imagePreview: string | ArrayBuffer | null; // Initialized as null
  validateForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router,
    private companyService: CompanyService
  ) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      serviceName: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required]]
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
  }

  postAd() {
    if (this.validateForm.valid && this.selectedFile) {
      const formData: FormData = new FormData();
      formData.append('img', this.selectedFile);
      formData.append('serviceName', this.validateForm.get('serviceName').value);
      formData.append('description', this.validateForm.get('description').value);
      formData.append('price', this.validateForm.get('price').value);

      this.companyService.postAd(formData).subscribe(
        () => {
          this.notification.success(
            'SUCCESS',
            'Ad Posted Successfully',
            { nzDuration: 5000 }
          );
          this.router.navigateByUrl('/company/ads');
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
        "ERROR",
        "Please fill out all required fields correctly.",
        { nzDuration: 5000 }
      );
    }
  }
}
