import { Component, OnInit } from '@angular/core';
import {CompanyService} from "../../services/company.service";
import {NzNotificationService} from "ng-zorro-antd/notification";

@Component({
  selector: 'app-company-dashboard',
  templateUrl: './company-dashboard.component.html',
  styleUrls: ['./company-dashboard.component.scss']
})
export class CompanyDashboardComponent implements OnInit {

  bookings :any

  constructor(private companyService:CompanyService,
              private nzNotification: NzNotificationService,) { }

  getAllAdBookings(){
    this.companyService.getAllAdsBookings().subscribe(res=>{
      console.log(res);
      this.bookings=res;
    })
  }

  changeBookingStatus(bookingId: number,status:string){
    this.companyService.changeBookingStatus(bookingId,status).subscribe(res=>{
      this.nzNotification
        .success(
          'SUCCESS',
              'booking Status changed successfully',
          {nzDuration:5000}
        );
      this.getAllAdBookings();
    },error =>{
      this.nzNotification
        .error(
          'ERROR',
            `${error.message}`,
          {nzDuration:5000}
        )

    })
  }

  ngOnInit(): void {
    this.getAllAdBookings();
  }

}
