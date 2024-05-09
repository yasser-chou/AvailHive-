import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { NzNotificationService } from "ng-zorro-antd/notification";
import { NzModalService } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrls: ['./all-ads.component.scss']
})
export class AllAdsComponent implements OnInit {
  ads: any;

  constructor(private companyService: CompanyService,
              private notification: NzNotificationService,
              private modal: NzModalService) { }  // Inject NzModalService

  ngOnInit(): void {
    this.getAllAdsByUserId();
  }

  getAllAdsByUserId() {
    this.companyService.getAllAdsByUserId().subscribe(res => {
      this.ads = res;
    });
  }

  updateImg(img) {
    return 'data:image/jpeg;base64,' + img;
  }

  deleteAd(adId: any) {
    this.modal.confirm({
      nzTitle: 'Are you sure you want to delete this ad?',
      nzContent: 'This action cannot be undone.',
      nzOkText: 'Yes, Delete It',
      nzOkType: 'primary',
      nzOkDanger:true,
      nzOnOk: () => this.confirmDeleteAd(adId),
      nzCancelText: 'No',
      nzOnCancel: () => console.log('Cancel delete')
    });
  }

  confirmDeleteAd(adId: any) {
    this.companyService.deleteAd(adId).subscribe(res => {
      this.notification.success(
        'SUCCESS',
        'Ad Has Been Deleted',
        { nzDuration: 5000 }
      );
      this.getAllAdsByUserId();  // Refresh the list
    });
  }
}
