import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router'; // Ensure RouterModule is imported if used in templates

import { ClientRoutingModule } from './client-routing.module';
import { ClientComponent } from './client.component';
import { ClientDashboardComponent } from './pages/client-dashboard/client-dashboard.component';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzWaveModule } from 'ng-zorro-antd/core/wave';
import { DemoNgZorroAntdModule } from '../DemoNgZorroAntModules';
import { AdDetailComponent } from './pages/ad-detail/ad-detail.component';
import { MyBookingsComponent } from './pages/my-bookings/my-bookings.component';
import { ReviewComponent } from './pages/review/review.component';
import { ReclamationComponent } from './pages/reclamation/reclamation.component';

@NgModule({
  declarations: [
    ClientComponent,
    ClientDashboardComponent,
    AdDetailComponent,
    MyBookingsComponent,
    ReviewComponent,
    ReclamationComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    NzButtonModule,
    NzGridModule,
    NzWaveModule,
    DemoNgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule // Add this if you are using routing features like routerLink in your components
  ]
})
export class ClientModule { }
