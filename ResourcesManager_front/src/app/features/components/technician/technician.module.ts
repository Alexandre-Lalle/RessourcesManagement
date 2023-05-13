import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { TechnicianRoutingModule } from './technician-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ListFailuresComponent } from './list-failures/list-failures.component';
import { CreateReportComponent } from './create-report/create-report.component';
import { UpdateReportComponent } from './update-report/update-report.component';


@NgModule({
  declarations: [
    ListFailuresComponent,
    CreateReportComponent,
    UpdateReportComponent,
  ],
  providers:[DatePipe],
  imports: [
    CommonModule,
    TechnicianRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ]
})
export class TechnicianModule { }
