import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyComponent } from './company.component';
import {CompanyDashboardComponent} from "./pages/company-dashboard/company-dashboard.component";
import {CreateAdComponent} from "./pages/create-ad/create-ad.component";
import {AllAdsComponent} from "./pages/all-ads/all-ads.component";
import {UpdateAdComponent} from "./pages/update-ad/update-ad.component";
import {CreateEmployerComponent} from "./pages/create-employer/create-employer.component";
import {AllEmployersComponent} from "./pages/all-employers/all-employers.component";
import {EmployerProfileComponent} from "./pages/employer-profile/employer-profile.component";
import {UpdateEmployerComponent} from "./pages/update-employer/update-employer.component";

const routes: Routes = [
  { path: '', component: CompanyComponent },
  { path: 'dashboard', component: CompanyDashboardComponent },
  { path: 'ad', component: CreateAdComponent },
  { path: 'employer', component: CreateEmployerComponent },
  { path: 'employers', component: AllEmployersComponent },
  { path: 'empprofile/:id', component: EmployerProfileComponent },

  { path: 'ads', component: AllAdsComponent },
  { path: 'update-ad/:id', component: UpdateAdComponent },
  { path: 'update-employer/:id', component: UpdateEmployerComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyRoutingModule { }
