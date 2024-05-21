import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzNotificationService } from "ng-zorro-antd/notification";
import { ActivatedRoute, Router } from "@angular/router";
import { UserStorageService } from "../../../basic/services/storage/user-storage.service";
import { ClientService } from "../../services/client.service";
import { CompanyService } from "../../../company/services/company.service";

@Component({
  selector: 'app-reclamation',
  templateUrl: './reclamation.component.html',
  styleUrls: ['./reclamation.component.scss']
})
export class ReclamationComponent implements OnInit {

  reservationId: number;
  employerId: number;
  reclamationForm!: FormGroup;
  employers: any[]; // Define the type explicitly

  constructor(
    private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router,
    private activatedroute: ActivatedRoute,
    private userStorageService: UserStorageService,
    private companyService: CompanyService,
    private clientService: ClientService
  ) {
    const id = this.activatedroute.snapshot.params['id'];
    this.reservationId = id ? parseInt(id) : null;
  }

  ngOnInit(): void {
    this.reclamationForm = this.fb.group({
      description: [null, Validators.required], // Fixed the typo
      employerId: [null, Validators.required],
      reservationId: [null, Validators.required]
    });
    this.getAllEmployersByUserId();
  }

  getAllEmployersByUserId() {
    this.companyService.getAllEmployers().subscribe(res => {
      this.employers = res;
      console.log(res);
    });
  }

  updateImg(img: string): string {
    return 'data:image/jpeg;base64,' + img;
  }

  setSelectedEmployer(id: number) {
    this.employerId = id;
  }

  save() {
    this.reclamationForm.get('employerId').setValue(this.employerId);
    this.reclamationForm.get('reservationId').setValue(this.reservationId);
    if (this.reclamationForm.valid) {
      this.clientService.saveReclamation(this.reclamationForm.value).subscribe(
        res => {
          this.notification.success(
            'SUCCESS',
            'Reclamation added successfully',
            { nzDuration: 5000 }
          );
          this.router.navigateByUrl('/client/bookings');
        },
        error => {
          this.notification.error(
            'ERROR',
            `${error.message}`,
            { nzDuration: 5000 }
          );
        }
      );
    } else {
      this.notification.error(
        'ERROR',
        `form invalid`,
        { nzDuration: 5000 }
      );
    }
  }
}
