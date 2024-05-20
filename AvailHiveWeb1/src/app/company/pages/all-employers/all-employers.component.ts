import { Component, OnInit } from '@angular/core';
import {CompanyService} from "../../services/company.service";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {NzModalService} from "ng-zorro-antd/modal";

@Component({
  selector: 'app-all-employers',
  templateUrl: './all-employers.component.html',
  styleUrls: ['./all-employers.component.scss']
})
export class AllEmployersComponent implements OnInit {
  employers:any;
  constructor(private companyService: CompanyService,
              private modal: NzModalService,
              private notification: NzNotificationService
              ) { }

  getAllEmployersByUserId(){
    this.companyService.getAllEmployersByUserId().subscribe(res=>
    {
      this.employers=res;
    })
  }
  updateImg(img) {
    return 'data:image/jpeg;base64,' + img;
  }


  ngOnInit(): void {
    this.getAllEmployersByUserId();
  }



}
