import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompanyService } from '../../services/company.service';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ChangeDetectorRef } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-employer-profile',
  templateUrl: './employer-profile.component.html',
  styleUrls: ['./employer-profile.component.scss']
})
export class EmployerProfileComponent implements OnInit {
  employer: any;
  employers: any;
  employerId: any = this.activatedroute.snapshot.params['id'];

  constructor(
    private activatedroute: ActivatedRoute,
    private companyService: CompanyService,
    private cdr: ChangeDetectorRef,
    private modal: NzModalService,
    private notification: NzNotificationService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getAllEmployersByUserId();
    this.getEmployerById();
  }

  getEmployerById() {
    this.companyService.getEmployerById(this.employerId).subscribe(res => {
      this.employer = res;
    });
  }

  updateImg(img) {
    return 'data:image/jpeg;base64,' + img;
  }

  getAllEmployersByUserId() {
    this.companyService.getAllEmployersByUserId().subscribe(res => {
      this.employers = res;
    });
  }

  deleteEmployer(employerId: any) {
    this.modal.confirm({
      nzTitle: 'Are you sure you want to delete this employer?',
      nzContent: 'This action cannot be undone.',
      nzOkText: 'Yes, Delete It',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => this.confirmDeleteEmployer(employerId),
      nzCancelText: 'No',
      nzOnCancel: () => console.log('Cancel delete')
    });
  }

  confirmDeleteEmployer(employerId: any) {
    this.companyService.deleteEmployer(employerId).subscribe(
      res => {
        this.notification.success(
          'SUCCESS',
          'Employer Has Been Deleted Successfully',
          { nzDuration: 5000 }
        );
        this.getAllEmployersByUserId();  // Refresh the list
        this.location.back();  // Navigate back to the previous page
      },
      error => {
        console.error('Error deleting employer:', error);
        this.notification.error(
          'ERROR',
          'Failed to delete employer',
          { nzDuration: 5000 }
        );
      }
    );
  }
}
