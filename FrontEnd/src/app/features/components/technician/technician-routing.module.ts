import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from 'src/app/core/components/dashboard/dashboard.component';
import { TechnicianLayoutComponent } from 'src/app/core/components/technician/technician-layout/technician-layout.component';
import { ListFailuresComponent } from './list-failures/list-failures.component';
import { CreateReportComponent } from './create-report/create-report.component';
import { UpdateReportComponent } from './update-report/update-report.component';

const routes: Routes = [
  { path: "", component: TechnicianLayoutComponent, children: [{ path: "", component: DashboardComponent, outlet: "center" }] },
  {
    path: "failures",
    children: [
      { path: "",component:TechnicianLayoutComponent,children:[{path:"",component: ListFailuresComponent, outlet: "center" }]
      },
      {path:"report",
        children:[
          {path:":failureId",component:TechnicianLayoutComponent,children:[{path:"",component: CreateReportComponent, outlet: "center" }]},
          {path:"update",component:TechnicianLayoutComponent,children:[{path:"",component: UpdateReportComponent, outlet: "center" }]}
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TechnicianRoutingModule { }
