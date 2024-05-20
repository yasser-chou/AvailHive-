import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './company.component';
import { CompanyDashboardComponent } from './pages/company-dashboard/company-dashboard.component';
import { CreateAdComponent } from './pages/create-ad/create-ad.component';
import {DemoNgZorroAntdModule} from "../DemoNgZorroAntModules";
import {ReactiveFormsModule} from "@angular/forms";
import {NzButtonModule} from "ng-zorro-antd/button";
import { AllAdsComponent } from './pages/all-ads/all-ads.component';
import { UpdateAdComponent } from './pages/update-ad/update-ad.component';
import { CreateEmployerComponent } from './pages/create-employer/create-employer.component';
import { AllEmployersComponent } from './pages/all-employers/all-employers.component';
import { EmployerProfileComponent } from './pages/employer-profile/employer-profile.component';
import { UpdateEmployerComponent } from './pages/update-employer/update-employer.component';


@NgModule({
  declarations: [
    CompanyComponent,
    CompanyDashboardComponent,
    CreateAdComponent,
    AllAdsComponent,
    UpdateAdComponent,
    CreateEmployerComponent,
    AllEmployersComponent,
    EmployerProfileComponent,
    UpdateEmployerComponent
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule

  ]
})
export class CompanyModule { }
